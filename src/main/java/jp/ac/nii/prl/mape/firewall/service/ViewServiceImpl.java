package jp.ac.nii.prl.mape.firewall.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.ViewRepository;

@Service("viewService")
public class ViewServiceImpl implements ViewService {

	private final ViewRepository viewRepository;
	
	private final ConstraintService constraintService;
	private final RuleService ruleService;
	
	@Autowired
	public ViewServiceImpl(ViewRepository viewRepository, 
			RuleService ruleService,
			ConstraintService constraintService) {
		this.viewRepository = viewRepository;
		this.ruleService = ruleService;
		this.constraintService = constraintService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ViewService#save(jp.ac.nii.prl.mape.firewall.model.View)
	 */
	@Override
	public View save(View view) {
		return viewRepository.save(view);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ViewService#findById(java.lang.Long)
	 */
	@Override
	public Optional<View> findById(Long viewId) {
		return viewRepository.findById(viewId);
	}
	
	public Collection<String> findPortsByViewIdAndSecurityGroupTo(Long viewId, String sg) {
		Collection<Rule> rules = ruleService.findPortsByViewIdAndSecurityGroupTo(viewId, sg);
		Collection<String> ports = new ArrayList<>();
		for (Rule rule:rules)
			ports.add(rule.getPort());
		return ports;
	}
	
	@Override
	public Collection<Constraint> analyse(View view) {
		Collection<Constraint> violations = new ArrayList<>();
		Collection<Constraint> constraints = constraintService.findAll();
		for (Constraint constraint:constraints) {
			if (!constraintService.validateConstraint(constraint, view))
				violations.add(constraint);
		}
		return violations;
	}
	
	@Override
	public View plan(View view, Collection<Constraint> violations) {
		return view;
	}
}
