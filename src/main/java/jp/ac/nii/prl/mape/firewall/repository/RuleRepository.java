package jp.ac.nii.prl.mape.firewall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.firewall.model.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long> {

	public Optional<Rule> findById(Long ruleId);
	
	public List<Rule> findByViewId(Long viewId);

}
