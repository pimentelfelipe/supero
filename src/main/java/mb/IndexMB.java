package mb;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TbUserDTO;
import model.entity.TbUser;
import session.TbUserSession;
import ws.TbUserWS;


@ManagedBean
@SessionScoped
public class IndexMB extends MBGeneric implements Serializable {

	private static final long serialVersionUID = 2225812843656700438L;

	@EJB
	private TbUserSession tbUserSession;
	
	@EJB
	private TbUserWS tbUserWS;
	
	private TbUserDTO tbUserDTO;

	private TbUser tbUser = new TbUser();
	
	
	public void authenticate() throws IOException, NoSuchAlgorithmException {
		if (tbUser.getTxUserName() != null && !tbUser.getTxUserName().trim().equals("") && tbUser.getTxPassword() != null && !tbUser.getTxPassword().trim().equals("")) {
			TbUser tbUserTemp = new TbUser();
			
			tbUserTemp.setTxUserName(tbUser.getTxUserName());
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
	        byte[] bytes = md.digest(tbUser.getTxPassword().getBytes("UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	           sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        tbUserTemp.setTxPassword(sb.toString());
	        tbUserDTO = new TbUserDTO(tbUserTemp);
	        
	        
	        // Session Class use ////////////////////////////////////////////////////
	        //tbUserDTO = tbUserSession.authenticate(tbUserDTO);
	        // Session Class use ////////////////////////////////////////////////////
	        
	        
	        // WS Class use ////////////////////////////////////////////////////////
	        Response userDTO = tbUserWS.authenticate(tbUserTemp.getTxUserName(), tbUserTemp.getTxPassword());
	        ObjectMapper mapper = new ObjectMapper();
	        JSONObject objJson = new JSONObject((String) userDTO.getEntity());
	        tbUserDTO.setEntity(mapper.readValue(objJson.getJSONObject("entity").toString(), TbUser.class));
	        tbUserDTO.setSuccess(objJson.getBoolean("success"));
	        // WS Class use ////////////////////////////////////////////////////////
	        
	        
			if (tbUserDTO.getEntity().getId() != null) {
				tbUser = tbUserDTO.getEntity();
				
				// Gravando o token do OAuth2 em cookies no cliente
				FacesContext context = FacesContext.getCurrentInstance();
				Cookie cookieId = new Cookie("superoAppId", tbUser.getId().toString());
				Cookie cookieKey = new Cookie("superoAppKey", tbUser.getTxKey());
				((HttpServletResponse)context.getExternalContext().getResponse()).addCookie(cookieId);
				((HttpServletResponse)context.getExternalContext().getResponse()).addCookie(cookieKey);
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("indexin.faces");
			} else {
				addMessageWarn("Error");
			}
		} else {
			addMessageWarn("Fill all fields");
		}
	}

	public TbUserDTO getTbUserDTO() {
		return tbUserDTO;
	}

	public void setTbUserDTO(TbUserDTO tbUserDTO) {
		this.tbUserDTO = tbUserDTO;
	}

	public TbUser getTbUser() {
		return tbUser;
	}

	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}
	
}
