package session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import bo.TbTaskBO;
import dto.TbTaskDTO;
import model.entity.TbTask;

@Stateless
public class TbTaskSession {
	
	@EJB
	private TbTaskBO objBO;
	
	
	public TbTaskDTO findAll(TbTaskDTO objDTO) {
		objDTO.setCollection(objBO.getObjDAO().findAll());
		return objDTO;
	}
	
	public TbTaskDTO find(TbTaskDTO objDTO) {
		objDTO.setEntity(objBO.getObjDAO().find(objDTO.getEntity().getId()));
		return objDTO;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TbTaskDTO save(TbTaskDTO objDTO) {
		TbTask entity = objDTO.getEntity();
		if (entity.getId() == null || entity.getId() == 0) {
			entity = objBO.getObjDAO().save(entity);
		} else {
			objBO.getObjDAO().update(entity);
			objBO.getObjDAO().flush();
		}
		objDTO.setEntity(entity);
		return objDTO;
	}
	
	public TbTaskDTO delete(TbTaskDTO objDTO) {
		TbTask entity = objDTO.getEntity();
		if (entity.getId() != null || entity.getId() != 0) {
			entity = objBO.getObjDAO().find(entity.getId());
			objBO.getObjDAO().delete(entity);
			objBO.getObjDAO().flush();
		}
		objDTO.setEntity(entity);
		return objDTO;
	}
}
