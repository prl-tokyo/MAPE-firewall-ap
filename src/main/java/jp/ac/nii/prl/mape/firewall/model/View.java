package jp.ac.nii.prl.mape.firewall.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class View {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@OneToMany(mappedBy="view")
	private List<Rule> rules;

	public Long getId() {
		return id;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}
	
	public void removeRule(Rule rule) {
		this.rules.remove(rule);
	}

	public void removeRules(Collection<Rule> rules) {
		rules.removeAll(rules);
	}
}
