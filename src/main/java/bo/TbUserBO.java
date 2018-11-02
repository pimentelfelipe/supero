package bo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dto.TbUserDTO;
import model.dao.TbUserDAO;
import model.entity.TbKey;

@Stateless
public class TbUserBO {

	@EJB
	private TbUserDAO objDAO;
	
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
		        TbKey tbKeyTemp = new TbKey();
		        tbKeyTemp.setTbUser(objDTO.getEntity());
		        tbKeyTemp.setTxKey(sb.toString());
		        tbKeyTemp.setDtStart(new Date());
		        objDTO.getEntity().setKeyCollection(new ArrayList<TbKey>());
		        objDTO.getEntity().getKeyCollection().add(tbKeyTemp);
		        
		        objDTO.setEntity(objDAO.saveFlush(objDTO.getEntity()));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objDTO;
	}
	
	public TbUserDTO validateToken(TbUserDTO objDTO) {
		objDTO = objDAO.validateToken(objDTO);
		return objDTO;
	}
	
	public TbUserDTO finalizeToken(TbUserDTO objDTO) {
		objDTO = objDAO.finalizeToken(objDTO);
		return objDTO;
	}

}
