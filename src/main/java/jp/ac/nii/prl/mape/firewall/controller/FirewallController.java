package jp.ac.nii.prl.mape.firewall.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.model.Rule;
import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.service.RuleService;
import jp.ac.nii.prl.mape.firewall.service.ViewService;

@RestController
@Component
@RequestMapping("/firewall")
public class FirewallController {

	private final ViewService viewService;
	private final RuleService ruleService;
	
	@Autowired
	public FirewallController(ViewService viewService, RuleService ruleService) {
		this.viewService = viewService;
		this.ruleService = ruleService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> createView(@RequestBody View view) {
		// persist view and rules
		viewService.save(view);
		for (Rule rule:view.getRules())
			ruleService.save(rule);
		
		// analysis and plan
		Collection<Constraint> violations = viewService.analyse(view);
		if (!violations.isEmpty()) { // no need to plan if there are no violations
			view = viewService.plan(view, violations);
			viewService.save(view);
			for (Rule rule:view.getRules())
				ruleService.save(rule);
		}
		
		// create response
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(view.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{viewId}", method=RequestMethod.GET)
	public View getView(@PathVariable Long viewId) {
		Optional<View> view = viewService.findById(viewId);
		
		if (view.isPresent())
			return view.get();
		//else
		throw new ViewNotFoundException(String.format("No view with id %s", viewId));
	}
	
}
