package jp.ac.nii.prl.mape.firewall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.firewall.model.FWConstraint;

public interface FWConstraintRepository extends JpaRepository<FWConstraint, Long> {

	Optional<FWConstraint> findById(Long constraintId);

}
