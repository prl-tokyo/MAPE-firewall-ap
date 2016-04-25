package jp.ac.nii.prl.mape.firewall.controller;

import org.springframework.web.client.RestClientException;

public class ViewNotFoundException extends RestClientException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2399002942871015981L;

	public ViewNotFoundException(String msg) {
		super(msg);
	}

}
