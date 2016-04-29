package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;
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
		Collection<Rule> rules = ruleRepository.findByViewId(viewId);
		rules = rules.stream().filter(p -> p.getSecurityGroupRefTo().equals(sg)).collect(Collectors.toList());
		return rules;
	}
	
	@Override
	public boolean satisfies(Rule rule, FWConstraint fWConstraint) {
		assert(fWConstraint.isPos());
		return covers(rule, fWConstraint);
	}
	
	@Override
	public boolean contradicts(Rule rule, FWConstraint fWConstraint) {
		assert(!fWConstraint.isPos());
		return !covers(rule, fWConstraint);
	}

	@Override
	public Rule createRule(FWConstraint violation, View view) {
		Rule rule = new Rule();
		rule.setPort(violation.getPort());
		rule.setProtocol(violation.getProtocol());
		rule.setSecurityGroupRefFrom(violation.getSgFrom());
		rule.setSecurityGroupRefTo(violation.getSgTo());
		rule.setRuleID(UUID.randomUUID().toString());
		rule.setView(view);
		System.out.println(String.format("New rule: %s", rule.toString()));
		save(rule);
		return rule;
	}
	
	@Override
	public void delete(Rule rule) {
		ruleRepository.delete(rule);
	}
	
	@Override
	public void delete(Collection<Rule> rules) {
		ruleRepository.delete(rules);
	}
	
	private boolean covers(Rule rule, FWConstraint fWConstraint) {
		if (fWConstraint.getSgFrom().equals(rule.getSecurityGroupRefFrom())
				&& fWConstraint.getSgTo().equals(rule.getSecurityGroupRefTo())
				&& fWConstraint.getPort().equals(rule.getPort())
				&& fWConstraint.getProtocol().equals(rule.getProtocol()))
			return true;
		// else
		return false;
	}
}
