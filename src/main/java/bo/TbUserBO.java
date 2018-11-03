package bo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.TbKeyDTO;
import dto.TbUserDTO;
import model.dao.TbKeyDAO;
import model.dao.TbUserDAO;
import model.entity.TbKey;

@Stateless
public class TbUserBO {

	@EJB
	private TbUserDAO objDAO;
	
	@EJB
	private TbKeyDAO objKeyDAO;
	
	public TbUserDAO getObjDAO() {
		return objDAO;
	}

	public void setObjDAO(TbUserDAO objDAO) {
		this.objDAO = objDAO;
	}

	public TbUserDTO authenticate(TbUserDTO objDTO) {
		objDTO = objDAO.findByTxUserNameAndTxPassword(objDTO);
		if (objDTO.getCollection().size() > 0) {
			objDTO.setEntity(objDTO.getCollection().iterator().next());
			
			
			// Gravando token do OAuth2
			Random objRand = new Random();
			String strRand = String.valueOf(objRand.nextLong());
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-512");
				byte[] bytes = md.digest(strRand.getBytes("UTF-8"));
				
				StringBuilder sb = new StringBuilder();
		        for(int i=0; i< bytes.length ;i++){
		           sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		        }
		        TbKey tbKey = new TbKey();
		        tbKey.setTbUser(objDTO.getEntity());
		        tbKey.setTxKey(sb.toString());
		        objDTO.getEntity().setTxKey(sb.toString());
		        tbKey.setDtStart(new Date());
		        objKeyDAO.saveFlush(tbKey);
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return objDTO;
	}
	
	public TbKeyDTO validateToken(TbKeyDTO objDTO) {
		objDTO = objKeyDAO.validateToken(objDTO);
		return objDTO;
	}
	
	public TbKeyDTO finalizeToken(TbKeyDTO objDTO) {
		objDTO = objKeyDAO.finalizeToken(objDTO);
		return objDTO;
	}

}
