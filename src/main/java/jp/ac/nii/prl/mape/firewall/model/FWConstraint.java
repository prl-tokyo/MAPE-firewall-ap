package jp.ac.nii.prl.mape.firewall.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FWConstraint {

	@GeneratedValue
	@Id
	@JsonIgnore
	private Long id;
	
	private String name;
	
	private String port;
	
	private String protocol;
	
	private String from;
	
	private String to;
	
	private boolean positive;

	public String getFrom() {
		return from;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getTo() {
		return to;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
