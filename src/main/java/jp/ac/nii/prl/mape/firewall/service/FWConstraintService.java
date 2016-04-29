package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.Optional;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;
import jp.ac.nii.prl.mape.firewall.model.View;

public interface FWConstraintService {

	FWConstraint save(FWConstraint fWConstraint);

	Optional<FWConstraint> findById(Long constraintId);

	Collection<FWConstraint> findAll();

	boolean validateConstraint(FWConstraint fWConstraint, View view);

	boolean validateAllConstraints(View view);
	
}