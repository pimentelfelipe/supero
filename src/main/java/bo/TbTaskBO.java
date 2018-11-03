package bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import model.dao.TbTaskDAO;

@Stateless
public class TbTaskBO {

	@EJB
	private TbTaskDAO objDAO;
	
	
	public TbTaskDAO getObjDAO() {
		return objDAO;
	}

	public void setObjDAO(TbTaskDAO objDAO) {
		this.objDAO = objDAO;
	}
}
