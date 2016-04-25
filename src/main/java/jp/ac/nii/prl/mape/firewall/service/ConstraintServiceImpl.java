package jp.ac.nii.prl.mape.firewall.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.firewall.model.Constraint;
import jp.ac.nii.prl.mape.firewall.repository.ConstraintRepository;

@Service("constraintRepository")
public class ConstraintServiceImpl implements ConstraintService {

	private final ConstraintRepository constraintRepository;
	
	@Autowired
	public ConstraintServiceImpl(ConstraintRepository constraintRepository) {
		this.constraintRepository = constraintRepository;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#save(jp.ac.nii.prl.mape.firewall.model.Constraint)
	 */
	@Override
	public Constraint save(Constraint constraint) {
		return constraintRepository.save(constraint);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findById(java.lang.Long)
	 */
	@Override
	public Optional<Constraint> findById(Long constraintId) {
		return constraintRepository.findById(constraintId);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ConstraintService#findAll()
	 */
	@Override
	public Collection<Constraint> findAll() {
		return constraintRepository.findAll();
	}
}
