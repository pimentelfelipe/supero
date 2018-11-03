package model.dao;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dto.TbKeyDTO;
import model.entity.TbKey;

@Stateless
public class TbKeyDAO extends DAOGeneric<Long, TbKey> {
	public TbKeyDTO validateToken(TbKeyDTO objDTO) {
		objDTO.setSuccessFalse();
		String srtSQL = "select k FROM TbKey k JOIN FETCH k.tbUser u where u.id = " + objDTO.getEntity().getTbUser().getId() + " and k.txKey = '" + objDTO.getEntity().getTxKey() + "' and k.dtEnd is null ";
		Query query = getEntityManager().createQuery(srtSQL);
		try {
			if (query.getSingleResult() != null) {
				objDTO.setSuccess(true);
			}
		} catch (NoResultException e) {
			objDTO.setSuccessFalse();
		}
		return objDTO;
	}
	
	public TbKeyDTO finalizeToken(TbKeyDTO objDTO) {
		String srtSQL = "select k FROM TbKey k JOIN FETCH k.tbUser u where u.id = " + objDTO.getEntity().getTbUser().getId() + " and k.txKey = '" + objDTO.getEntity().getTxKey() + "' and k.dtEnd is null ";
		Query query = getEntityManager().createQuery(srtSQL);
		try {
			if (query.getSingleResult() != null) {
				TbKey tbKey = (TbKey) query.getSingleResult();
				tbKey.setDtEnd(new Date());
				this.saveFlush(tbKey);
				objDTO.setSuccess(true);
			}
		} catch (NoResultException e) {
			objDTO.setSuccessFalse();
		}
		return objDTO;
	}
}
