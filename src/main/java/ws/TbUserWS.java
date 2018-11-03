package ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TbKeyDTO;
import dto.TbUserDTO;
import model.entity.TbKey;
import model.entity.TbUser;
import session.TbUserSession;

@Stateless
@Path("/TbUser")
public class TbUserWS {
	
	@EJB
	private TbUserSession objSession;
	
	@GET
	@Path("/{id}")
	public Response find (@PathParam("id") String id) {
		TbUserDTO objDTO = new TbUserDTO();
		objDTO.setEntity(new TbUser());
		objDTO.getEntity().setId(Long.parseLong(id));
		objDTO = objSession.find(objDTO);
		ObjectMapper mapper = new ObjectMapper();
		String response = null;
		try {
			response = mapper.writeValueAsString(objDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(response).build();
	}
	
	@POST
	@Path("/authenticate")
	public Response authenticate (String strJsonEntity) {
		String response = "";
		try {
			TbUserDTO objDTO = new TbUserDTO();
			ObjectMapper mapper = new ObjectMapper();
			TbUser entity = mapper.readValue(strJsonEntity, TbUser.class);
			objDTO.setEntity(entity);
			objDTO = objSession.authenticate(objDTO);
			response = mapper.writeValueAsString(objDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getStackTrace()).build();
		}
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/authenticate/{txUserName}/{txPassword}")
	public Response authenticate (@PathParam("txUserName") String txUserName, @PathParam("txPassword") String txPassword) {
		TbUserDTO objDTO = new TbUserDTO();
		objDTO.setEntity(new TbUser());
		objDTO.getEntity().setTxUserName(txUserName);
		objDTO.getEntity().setTxPassword(txPassword);
		objDTO = objSession.authenticate(objDTO);
		ObjectMapper mapper = new ObjectMapper();
		String response = null;
		try {
			response = mapper.writeValueAsString(objDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/validateToken/{id}/{txKey}")
	public Response validateToken (@PathParam("id") String id, @PathParam("txKey") String txKey) {
		TbKeyDTO objDTO = new TbKeyDTO();
		objDTO.setEntity(new TbKey());
		objDTO.getEntity().setTbUser(new TbUser());
		objDTO.getEntity().getTbUser().setId(Long.parseLong(id));
		objDTO.getEntity().setTxKey(txKey);
		objDTO = objSession.validateToken(objDTO);
		ObjectMapper mapper = new ObjectMapper();
		String response = null;
		try {
			response = mapper.writeValueAsString(objDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(response).build();
	}
	
	@GET
	@Path("/finalizeToken/{id}/{txKey}")
	public Response finalizeToken (@PathParam("id") String id, @PathParam("txKey") String txKey) {
		TbKeyDTO objDTO = new TbKeyDTO();
		objDTO.setEntity(new TbKey());
		objDTO.getEntity().setTbUser(new TbUser());
		objDTO.getEntity().getTbUser().setId(Long.parseLong(id));
		objDTO.getEntity().setTxKey(txKey);
		objDTO = objSession.finalizeToken(objDTO);
		ObjectMapper mapper = new ObjectMapper();
		String response = null;
		try {
			response = mapper.writeValueAsString(objDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok(response).build();
	}
}
