package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import jp.ac.nii.prl.mape.firewall.model.Rule;

public interface RuleService {

	Rule save(Rule rule);

	Optional<Rule> findById(Long ruleId);

	List<Rule> findByViewId(Long viewId);

	Collection<Rule> findPortsByViewIdAndSecurityGroupTo(Long viewId, String sg);

}