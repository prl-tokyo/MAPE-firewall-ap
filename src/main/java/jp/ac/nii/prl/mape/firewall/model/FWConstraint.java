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
	
	private String sgFrom;
	
	private String sgTo;
	
	private boolean pos;

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

	public String getSgFrom() {
		return sgFrom;
	}

	public String getSgTo() {
		return sgTo;
	}
	
	public boolean isPos() {
		return pos;
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

	public void setPos(boolean pos) {
		this.pos = pos;
	}
	
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setSgFrom(String from) {
		this.sgFrom = from;
	}

	public void setSgTo(String to) {
		this.sgTo = to;
	}
}
