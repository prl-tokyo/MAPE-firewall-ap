package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.Optional;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.model.View;

public interface ConstraintService {

	Constraint save(Constraint constraint);

	Optional<Constraint> findById(Long constraintId);

	Collection<Constraint> findAll();

	boolean validateConstraint(Constraint constraint, View view);

	boolean validateAllConstraints(View view);
	
}