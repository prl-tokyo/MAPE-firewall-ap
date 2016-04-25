package jp.ac.nii.prl.mape.firewall;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import jp.ac.nii.prl.mape.firewall.controller.ViewNotFoundException;

@ControllerAdvice
public class RestErrorHandler {

	public RestErrorHandler() {}
	
	@ExceptionHandler(ViewNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleViewNotFoundException(ViewNotFoundException ex) {}
}
