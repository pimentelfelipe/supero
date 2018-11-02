package session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import bo.TbUserBO;
import dto.TbUserDTO;
import model.entity.TbUser;

@Stateless
public class TbUserSession {
	
	@EJB
	private TbUserBO objBO;
	
	public TbUserDTO authenticate(TbUserDTO objDTO) {
		objDTO = objBO.authenticate(objDTO);
		return objDTO;
	}
	
	public TbUserDTO validateToken(TbUserDTO objDTO) {
		objDTO = objBO.validateToken(objDTO);
		return objDTO;
	}
	
	public TbUserDTO finalizeToken(TbUserDTO objDTO) {
		objDTO = objBO.finalizeToken(objDTO);
		return objDTO;
	}
	
	public TbUserDTO findAll(TbUserDTO objDTO) {
		objDTO.setCollection(objBO.getObjDAO().findAll());
		return objDTO;
	}
	
	public TbUserDTO find(TbUserDTO objDTO) {
		objDTO.setEntity(objBO.getObjDAO().find(objDTO.getEntity().getId()));
		return objDTO;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TbUserDTO salvar(TbUserDTO objDTO) {
		TbUser entity = objDTO.getEntity();
		if (entity.getId() == null || entity.getId() == 0) {
			entity = objBO.getObjDAO().save(entity);
		} else {
			objBO.getObjDAO().update(entity);
			objBO.getObjDAO().flush();
		}

		objDTO.setEntity(entity);
		return objDTO;
	}
	
	public TbUserDTO delete(TbUserDTO objDTO) {
		TbUser entity = objDTO.getEntity();
		if (entity.getId() != null || entity.getId() != 0) {
			entity = objBO.getObjDAO().find(entity.getId());
			objBO.getObjDAO().delete(entity);
			objBO.getObjDAO().flush();
		}
		objDTO.setEntity(entity);
		return objDTO;
	}
}
