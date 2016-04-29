package jp.ac.nii.prl.mape.firewall.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.ViewRepository;

@Service("viewService")
public class ViewServiceImpl implements ViewService {

	private final ViewRepository viewRepository;
	
	private final FWConstraintService fWConstraintService;
	private final RuleService ruleService;
	
	@Autowired
	public ViewServiceImpl(ViewRepository viewRepository, 
			RuleService ruleService,
			FWConstraintService fWConstraintService) {
		this.viewRepository = viewRepository;
		this.ruleService = ruleService;
		this.fWConstraintService = fWConstraintService;
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
	public Collection<FWConstraint> analyse(View view) {
		Collection<FWConstraint> violations = new ArrayList<>();
		Collection<FWConstraint> fWConstraints = fWConstraintService.findAll();
		for (FWConstraint fWConstraint:fWConstraints) {
			if (!fWConstraintService.validateConstraint(fWConstraint, view))
				violations.add(fWConstraint);
		}
		return violations;
	}
	
	@Override
	public View plan(View view, Collection<FWConstraint> violations) {
		for (FWConstraint violation:violations) {
			if (violation.isPos())
				view.addRule(ruleService.createRule(violation, view));
			else
				removeRule(view, violation);
		}
		return view;
	}
	
	private void removeRule(View view, FWConstraint fWConstraint) {
		Collection<Rule> remove = findViolatingRules(view, fWConstraint);
		view.removeRules(remove);
		ruleService.delete(remove);
	}
	
	private Collection<Rule> findViolatingRules(View view, FWConstraint fWConstraint) {
		assert(!fWConstraint.isPos());
		Collection<Rule> rules = new ArrayList<>();
		for (Rule rule:view.getRules())
			if (ruleService.contradicts(rule, fWConstraint))
				rules.add(rule);
		return rules;
	}
}
