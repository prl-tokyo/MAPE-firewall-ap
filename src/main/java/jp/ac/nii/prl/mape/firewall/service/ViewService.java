package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.Optional;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;
import jp.ac.nii.prl.mape.firewall.model.View;

public interface ViewService {

	View save(View view);

	Optional<View> findById(Long viewId);
	
	Collection<String> findPortsByViewIdAndSecurityGroupTo(Long viewId, String sg);

	Collection<FWConstraint> analyse(View view);

	View plan(View view, Collection<FWConstraint> violations);

}