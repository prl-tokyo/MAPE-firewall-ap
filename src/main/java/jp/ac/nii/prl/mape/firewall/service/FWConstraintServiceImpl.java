package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.FWConstraintRepository;

@Service("fWConstraintService")
public class FWConstraintServiceImpl implements FWConstraintService {

	private final FWConstraintRepository fWConstraintRepository;
	
	private final RuleService ruleService;
	
	@Autowired
	public FWConstraintServiceImpl(FWConstraintRepository fWConstraintRepository, 
			RuleService ruleService) {
		this.fWConstraintRepository = fWConstraintRepository;
		this.ruleService = ruleService;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#save(jp.ac.nii.prl.mape.firewall.model.Constraint)
	 */
	@Override
	public FWConstraint save(FWConstraint fWConstraint) {
		return fWConstraintRepository.save(fWConstraint);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findById(java.lang.Long)
	 */
	@Override
	public Optional<FWConstraint> findById(Long constraintId) {
		return fWConstraintRepository.findById(constraintId);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findAll()
	 */
	@Override
	public Collection<FWConstraint> findAll() {
		return fWConstraintRepository.findAll();
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#validateConstraint(jp.ac.nii.prl.mape.firewall.model.Constraint, jp.ac.nii.prl.mape.firewall.model.View)
	 */
	@Override
	public boolean validateConstraint(FWConstraint fWConstraint, View view) {
		if (fWConstraint.isPos()) {
			for (Rule rule:view.getRules())
				if (ruleService.satisfies(rule, fWConstraint))
					return true;
			// no rule validates the constraint -> fail
			return false;
		} else {
			for (Rule rule:view.getRules()) {
				if (ruleService.contradicts(rule, fWConstraint))
					return false;
			}
			return true;
		}
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#validateAllConstraints(jp.ac.nii.prl.mape.firewall.model.View)
	 */
	@Override
	public boolean validateAllConstraints(View view) {
		Collection<FWConstraint> fWConstraints = fWConstraintRepository.findAll();
		for (FWConstraint fWConstraint:fWConstraints) {
			if (!validateConstraint(fWConstraint, view))
				return false; // one constraint not valid -> fail
		}
		return true;
	}
}
