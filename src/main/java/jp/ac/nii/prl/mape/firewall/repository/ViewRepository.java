package jp.ac.nii.prl.mape.firewall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.firewall.model.View;

public interface ViewRepository extends JpaRepository<View, Long> {

	public Optional<View> findById(Long viewId);
	
}
