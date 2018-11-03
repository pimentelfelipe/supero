package model.dao;

import javax.ejb.Stateless;

import dto.TbUserDTO;
import model.entity.TbUser;

@Stateless
public class TbUserDAO extends DAOGeneric<Long, TbUser> {
	@SuppressWarnings("unchecked")
	public TbUserDTO findByTxUserNameAndTxPassword(TbUserDTO objDTO) {
		String srtSQL = "select u FROM TbUser u where txUserName = '" + objDTO.getEntity().getTxUserName() + "' and txPassword = '" + objDTO.getEntity().getTxPassword() + "'";
		objDTO.setCollection(getEntityManager().createQuery(srtSQL).getResultList());
		return objDTO;
	}
}
