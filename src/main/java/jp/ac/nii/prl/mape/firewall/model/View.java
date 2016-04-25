package jp.ac.nii.prl.mape.firewall.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
