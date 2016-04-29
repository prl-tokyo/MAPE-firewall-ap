package jp.ac.nii.prl.mape.firewall.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rule {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@NotEmpty
	private String ruleID;
	
	@NotEmpty
	private String securityGroupRefFrom;

	@NotEmpty
	private String securityGroupRefTo;

	@NotEmpty
	private String port;

	@NotEmpty
	private String protocol;
	
	@JsonIgnore
	@ManyToOne
	private View view;

	public Long getId() {
		return id;
	}

	public String getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRuleID() {
		return ruleID;
	}

	public String getSecurityGroupRefFrom() {
		return securityGroupRefFrom;
	}

	public String getSecurityGroupRefTo() {
		return securityGroupRefTo;
	}

	public View getView() {
		return view;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}
	
	public void setSecurityGroupRefFrom(String securityGroupRefFrom) {
		this.securityGroupRefFrom = securityGroupRefFrom;
	}
	
	public void setSecurityGroupRefTo(String securityGroupRefTo) {
		this.securityGroupRefTo = securityGroupRefTo;
	}
	
	public void setView(View view) {
		this.view = view;
	}
}
