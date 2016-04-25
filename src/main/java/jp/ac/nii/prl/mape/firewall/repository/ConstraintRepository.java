package jp.ac.nii.prl.mape.firewall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.firewall.model.Constraint;

public interface ConstraintRepository extends JpaRepository<Constraint, Long> {

}
