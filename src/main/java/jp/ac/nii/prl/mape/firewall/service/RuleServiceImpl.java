package jp.ac.nii.prl.mape.firewall.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.repository.RuleRepository;

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
}
