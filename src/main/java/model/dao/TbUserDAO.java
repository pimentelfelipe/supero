package model.dao;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
	
	public TbUserDTO validateToken(TbUserDTO objDTO) {
		objDTO.setSucessoFalse();
		String srtSQL = "select u FROM TbUser u JOIN FETCH u.keyCollection k where u.id = " + objDTO.getEntity().getId() + " and k.txKey = '" + objDTO.getEntity().getTxKey() + "' and k.dtEnd is null ";
		Query query = getEntityManager().createQuery(srtSQL);
		try {
			if (query.getSingleResult() != null) {
				objDTO.setSucesso(true);
			}
		} catch (NoResultException e) {
			objDTO.setSucessoFalse();
		}
		return objDTO;
	}
	
	public TbUserDTO finalizeToken(TbUserDTO objDTO) {
		String srtSQL = "select u FROM TbUser u JOIN FETCH u.keyCollection k where u.id = " + objDTO.getEntity().getId() + " and k.txKey = '" + objDTO.getEntity().getTxKey() + "' and k.dtEnd is null ";
		Query query = getEntityManager().createQuery(srtSQL);
		try {
			if (query.getSingleResult() != null) {
				TbUser tbUserTemp = (TbUser) query.getSingleResult();
				tbUserTemp.getKeyCollection().iterator().next().setDtEnd(new Date());
				this.saveFlush(tbUserTemp);
				objDTO.setSucesso(true);
			}
		} catch (NoResultException e) {
			objDTO.setSucessoFalse();
		}
		return objDTO;
	}
}
