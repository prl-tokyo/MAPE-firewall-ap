package jp.ac.nii.prl.mape.firewall.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Rule {
	
	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	@NotEmpty
	private String ruleId;
	
	@NotEmpty
	private String securityGroupFrom;

	@NotEmpty
	private String securityGroupTo;

	@NotEmpty
	private String port;

	@NotEmpty
	private String protocol;

	public Long getId() {
		return id;
	}

	public String getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getRuleId() {
		return ruleId;
	}

	public String getSecurityGroupFrom() {
		return securityGroupFrom;
	}

	public String getSecurityGroupTo() {
		return securityGroupTo;
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
	
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	
	public void setSecurityGroupFrom(String securityGroupFrom) {
		this.securityGroupFrom = securityGroupFrom;
	}
	
	public void setSecurityGroupTo(String securityGroupTo) {
		this.securityGroupTo = securityGroupTo;
	}
}
