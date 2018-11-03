package ws;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TbTaskDTO;
import model.entity.TbTask;
import session.TbTaskSession;

@Path("/TbTask")
public class TbTaskWS {
	
	@EJB
	private TbTaskSession objSession;
	
	@GET
	@Path("/{id}")
	public Response find (@PathParam("id") String id) {
		TbTaskDTO objDTO = new TbTaskDTO();
		objDTO.setEntity(new TbTask());
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
	
	@GET
	@Path("/")
	public Response findAll () {
		TbTaskDTO objDTO = new TbTaskDTO();
		objDTO.setEntity(new TbTask());
		objDTO = objSession.findAll(objDTO);
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
	@Path("/save")
	public Response save (String strJsonEntity) {
		String response = "";
		try {
			TbTaskDTO objDTO = new TbTaskDTO();
			ObjectMapper mapper = new ObjectMapper();
			TbTask entity = mapper.readValue(strJsonEntity, TbTask.class);
			objDTO.setEntity(entity);
			objDTO = objSession.save(objDTO);
			response = mapper.writeValueAsString(objDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getStackTrace()).build();
		}
		return Response.ok(response).build();
	}
	
	@DELETE
	@Path("/delete")
	public Response delete (String strJsonEntity) {
		String response = "";
		try {
			TbTaskDTO objDTO = new TbTaskDTO();
			ObjectMapper mapper = new ObjectMapper();
			TbTask entity = mapper.readValue(strJsonEntity, TbTask.class);
			objDTO.setEntity(entity);
			objDTO = objSession.delete(objDTO);
			response = mapper.writeValueAsString(objDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.ok(e.getStackTrace()).build();
		}
		return Response.ok(response).build();
	}
}
