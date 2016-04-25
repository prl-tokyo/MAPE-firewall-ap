package jp.ac.nii.prl.mape.firewall.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.ConstraintRepository;

@Service("constraintRepository")
public class ConstraintServiceImpl implements ConstraintService {

	private final ConstraintRepository constraintRepository;
	
	private final RuleService ruleService;
	
	@Autowired
	public ConstraintServiceImpl(ConstraintRepository constraintRepository, 
			RuleService ruleService) {
		this.constraintRepository = constraintRepository;
		this.ruleService = ruleService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#save(jp.ac.nii.prl.mape.firewall.model.Constraint)
	 */
	@Override
	public Constraint save(Constraint constraint) {
		return constraintRepository.save(constraint);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findById(java.lang.Long)
	 */
	@Override
	public Optional<Constraint> findById(Long constraintId) {
		return constraintRepository.findById(constraintId);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findAll()
	 */
	@Override
	public Collection<Constraint> findAll() {
		return constraintRepository.findAll();
	}
	
	@Override
	public boolean validateConstraint(Constraint constraint, View view) {
		for (Rule rule:view.getRules())
			if (ruleService.satisfies(rule, constraint))
				return true;
		// no rule validates the constraint -> fail
		return false;
	}
	
	@Override
	public boolean validateAllConstraints(View view) {
		Collection<Constraint> constraints = constraintRepository.findAll();
		for (Constraint constraint:constraints) {
			if (!validateConstraint(constraint, view))
				return false; // one constraint not valid -> fail
		}
		return true;
	}
}
