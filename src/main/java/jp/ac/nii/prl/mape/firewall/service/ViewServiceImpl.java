package jp.ac.nii.prl.mape.firewall.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import jp.ac.nii.prl.mape.firewall.model.View;
import jp.ac.nii.prl.mape.firewall.repository.ViewRepository;

public class ViewServiceImpl implements ViewService {

	private final ViewRepository viewRepository;
	
	@Autowired
	public ViewServiceImpl(ViewRepository viewRepository) {
		this.viewRepository = viewRepository;
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ViewService#save(jp.ac.nii.prl.mape.firewall.model.View)
	 */
	@Override
	public View save(View view) {
		return viewRepository.save(view);
	}
	
	/* (non-Javadoc)
	 * @see jp.ac.nii.prl.mape.firewall.service.ViewService#findById(java.lang.Long)
	 */
	@Override
	public Optional<View> findById(Long viewId) {
		return viewRepository.findById(viewId);
	}
}
