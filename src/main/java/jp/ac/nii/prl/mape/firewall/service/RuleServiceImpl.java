package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.RuleRepository;

@Service("ruleService")
public class RuleServiceImpl implements RuleService {
	
	private final RuleRepository ruleRepository;
	
	@Autowired
	public RuleServiceImpl(RuleRepository ruleRepository) {
		this.ruleRepository = ruleRepository;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.RuleService#save(jp.ac.nii.prl.mape.firewall.model.Rule)
	 */
	@Override
	public Rule save(Rule rule) {
		return ruleRepository.save(rule);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.RuleService#findById(java.lang.Long)
	 */
	@Override
	public Optional<Rule> findById(Long ruleId) {
		return ruleRepository.findById(ruleId);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.RuleService#findByViewId(java.lang.Long)
	 */
	@Override
	public List<Rule> findByViewId(Long viewId) {
		return ruleRepository.findByViewId(viewId);
	}

	@Override
	public Collection<Rule> findPortsByViewIdAndSecurityGroupTo(Long viewId, String sg) {
		return ruleRepository.findByViewIdAndSecurityGroupTo(viewId, sg);
	}
	
	@Override
	public boolean satisfies(Rule rule, Constraint constraint) {
		assert(constraint.isPositive());
		return covers(rule, constraint);
	}
	
	@Override
	public boolean contradicts(Rule rule, Constraint constraint) {
		assert(!constraint.isPositive());
		return !covers(rule, constraint);
	}

	@Override
	public Rule createRule(Constraint violation, View view) {
		Rule rule = new Rule();
		rule.setPort(violation.getPort());
		rule.setProtocol(violation.getProtocol());
		rule.setSecurityGroupFrom(violation.getFrom());
		rule.setSecurityGroupTo(violation.getTo());
		rule.setRuleId(UUID.randomUUID().toString());
		rule.setView(view);
		return rule;
	}
	
	private boolean covers(Rule rule, Constraint constraint) {
		if (constraint.getFrom().equals(rule.getSecurityGroupFrom())
				&& constraint.getTo().equals(rule.getSecurityGroupTo())
				&& constraint.getPort().equals(rule.getPort())
				&& constraint.getProtocol().equals(rule.getProtocol()))
			return true;
		// else
		return false;
	}
}
