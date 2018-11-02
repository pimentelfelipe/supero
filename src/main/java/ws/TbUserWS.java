package ws;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TbUserDTO;
import model.entity.TbUser;
import session.TbUserSession;

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
}
