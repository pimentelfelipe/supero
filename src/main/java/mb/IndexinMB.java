package mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.primefaces.event.DragDropEvent;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.TbKeyDTO;
import dto.TbTaskDTO;
import dto.TbUserDTO;
import model.entity.TbKey;
import model.entity.TbTask;
import model.entity.TbUser;
import session.TbTaskSession;
import session.TbUserSession;
import ws.TbTaskWS;
import ws.TbUserWS;

@ManagedBean
@SessionScoped
public class IndexinMB extends MBGeneric implements Serializable {

	private static final long serialVersionUID = 2225812843656700438L;

	@EJB
	private TbUserSession tbUserSession;
	
	@EJB
	private TbTaskSession tbTaskSession;
	
	@EJB
	private TbUserWS tbUserWS;
	
	@EJB
	private TbTaskWS tbTaskWS;
	
	private TbTaskDTO tbTaskDTO;
	private Collection<TbTask> tbTaskList = new ArrayList<TbTask>();
	private Collection<TbTask> tbTaskListStarted = new ArrayList<TbTask>();
	private Collection<TbTask> tbTaskListFinished = new ArrayList<TbTask>();
	private Collection<TbTask> tbTaskListDeleted = new ArrayList<TbTask>();
	private TbTask tbTask = new TbTask();
	
	private TbUserDTO tbUserDTO;
	private TbUser tbUser = new TbUser();
	
	private TbKeyDTO tbKeyDTO;
	private TbKey tbKey = new TbKey();
	
	private void fillToken() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("superoAppId")) {
					if (Long.valueOf(cookie.getValue()) != null) {
						tbUser.setId(Long.valueOf(cookie.getValue()));
					}
				}
				if(cookie.getName().equals("superoAppKey")) {
					tbUser.setTxKey(cookie.getValue());
					tbKey.setTxKey(cookie.getValue());
				}
			}
		}
	}
	
	private void clearToken() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("superoAppId")) {
					if (Long.valueOf(cookie.getValue()) != null) {
						cookie.setValue(null);
						cookie.setMaxAge(0);
						cookie.setPath("/");
						((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cookie);
					}
				}
				if(cookie.getName().equals("superoAppKey")) {
					cookie.setValue(null);
					cookie.setMaxAge(0);
					cookie.setPath("/");
					((HttpServletResponse)facesContext.getExternalContext().getResponse()).addCookie(cookie);
				}
			}
		}
	}
	
	private boolean finalizeToken() throws JsonParseException, JsonMappingException, JSONException, IOException {
		tbKeyDTO = new TbKeyDTO();
		tbKey.setTbUser(tbUser);
		tbKeyDTO.setEntity(tbKey);
		
		// Session Class use ////////////////////////////////////////////////////
		//tbKeyDTO = tbUserSession.finalizeToken(tbKeyDTO);
		// Session Class use ////////////////////////////////////////////////////
		
		
		// WS Class use ////////////////////////////////////////////////////////
        Response keyDTO = tbUserWS.finalizeToken(tbKey.getTbUser().getId().toString(), tbKey.getTxKey());
        ObjectMapper mapper = new ObjectMapper();
        JSONObject objJson = new JSONObject((String) keyDTO.getEntity());
        tbKeyDTO.setEntity(mapper.readValue(objJson.getJSONObject("entity").toString(), TbKey.class));
        tbKeyDTO.setSuccess(objJson.getBoolean("success"));
        // WS Class use ////////////////////////////////////////////////////////
		
		
		return tbKeyDTO.isSuccess();
	}
	
	private boolean validateToken() throws JsonParseException, JsonMappingException, JSONException, IOException {
		tbKeyDTO = new TbKeyDTO();
		tbKey.setTbUser(tbUser);
		tbKeyDTO.setEntity(tbKey);
		
		// Session Class use ////////////////////////////////////////////////////
		//tbKeyDTO = tbUserSession.validateToken(tbKeyDTO);
		// Session Class use ////////////////////////////////////////////////////
		
		
		// WS Class use ////////////////////////////////////////////////////////
        Response keyDTO = tbUserWS.validateToken(tbKey.getTbUser().getId().toString(), tbKey.getTxKey());
        ObjectMapper mapper = new ObjectMapper();
        JSONObject objJson = new JSONObject((String) keyDTO.getEntity());
        tbKeyDTO.setEntity(mapper.readValue(objJson.getJSONObject("entity").toString(), TbKey.class));
        tbKeyDTO.setSuccess(objJson.getBoolean("success"));
        // WS Class use ////////////////////////////////////////////////////////
		
		return tbKeyDTO.isSuccess();
	}
	
	public void init() throws IOException{
		fillToken();
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO = tbTaskSession.findAll(tbTaskDTO);
			tbTaskList = new ArrayList<TbTask>();
			tbTaskListStarted = new ArrayList<TbTask>();
			tbTaskListFinished = new ArrayList<TbTask>();
			tbTaskListDeleted = new ArrayList<TbTask>();
			tbTaskList.addAll(tbTaskDTO.getCollection());
			
			if (tbTaskDTO.getCollection() != null) {
				for (TbTask tbTaskTemp : tbTaskDTO.getCollection()) {
					if (tbTaskTemp.getTxStatus().toLowerCase().equals("started")) {
						tbTaskListStarted.add(tbTaskTemp);
					} else if (tbTaskTemp.getTxStatus().toLowerCase().equals("finished")) {
						tbTaskListFinished.add(tbTaskTemp);
					} else if (tbTaskTemp.getTxStatus().toLowerCase().equals("deleted")) {
						tbTaskListDeleted.add(tbTaskTemp);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	public void refreshBean() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		}
	}
	
	
	public void logout() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			finalizeToken();
			clearToken();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		}
	}
	
	
	
	
	public void onStartedTaskDrop(DragDropEvent ddEvent) throws IOException {
        tbTask = (TbTask) ddEvent.getData();
        this.startTask();
        this.init();
    }
	
	public void onFinishedTaskDrop(DragDropEvent ddEvent) throws IOException {
		tbTask = (TbTask) ddEvent.getData();
		this.finishTask();
		this.init();
    }
	
	public void onDeletedTaskDrop(DragDropEvent ddEvent) throws IOException {
		tbTask = (TbTask) ddEvent.getData();
		this.deleteTask();
		this.init();
    }
	
	
	public void findTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO = tbTaskSession.find(tbTaskDTO);
		}
	}
	
	public void newTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask = new TbTask();
			tbTask.setTxStatus("Started");
			tbTask.setDtStart(new Date());
			FacesContext.getCurrentInstance().getExternalContext().redirect("task.faces");
		}
	}
	
	public void editTask(TbTask objResource) throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask = objResource;
			FacesContext.getCurrentInstance().getExternalContext().redirect("task.faces");
		}
	}
	
	public void viewTasks() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("indexin.faces");
		}
	}
	
	public void saveTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask.setDtEdit(new Date());
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO.setSuccessFalse();
			tbTaskDTO.setEntity(tbTask);
			tbTaskDTO = tbTaskSession.save(tbTaskDTO);
			if (tbTaskDTO.isSuccess()) {
				addMessageInfo("Saved");
			} else {
				addMessageWarn("Error");
			}
		}
	}
	
	public void startTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask.setTxStatus("Started");
			tbTask.setDtStart(new Date());
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO.setSuccessFalse();
			tbTaskDTO.setEntity(tbTask);
			tbTaskDTO = tbTaskSession.save(tbTaskDTO);
			if (tbTaskDTO.isSuccess()) {
				addMessageInfo("Saved");
			} else {
				addMessageWarn("Error");
			}
		}
	}
	
	public void finishTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask.setTxStatus("Finished");
			tbTask.setDtEnd(new Date());
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO.setSuccessFalse();
			tbTaskDTO.setEntity(tbTask);
			tbTaskDTO = tbTaskSession.save(tbTaskDTO);
			if (tbTaskDTO.isSuccess()) {
				addMessageInfo("Saved");
			} else {
				addMessageWarn("Error");
			}
		}
	}
	
	public void deleteTask() throws IOException{
		if (!validateToken()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
		} else {
			tbTask.setTxStatus("Deleted");
			tbTask.setDtDelete(new Date());
			tbTaskDTO = new TbTaskDTO();
			tbTaskDTO.setSuccessFalse();
			tbTaskDTO.setEntity(tbTask);
			tbTaskDTO = tbTaskSession.save(tbTaskDTO);
			if (tbTaskDTO.isSuccess()) {
				addMessageInfo("Saved");
			} else {
				addMessageWarn("Error");
			}
		}
	}
	
	
	

	public TbTaskDTO getTbTaskDTO() {
		return tbTaskDTO;
	}

	public void setTbTaskDTO(TbTaskDTO tbTaskDTO) {
		this.tbTaskDTO = tbTaskDTO;
	}

	public Collection<TbTask> getTbTaskList() {
		return tbTaskList;
	}

	public void setTbTaskList(Collection<TbTask> tbTaskList) {
		this.tbTaskList = tbTaskList;
	}

	public Collection<TbTask> getTbTaskListStarted() {
		return tbTaskListStarted;
	}

	public void setTbTaskListStarted(Collection<TbTask> tbTaskListStarted) {
		this.tbTaskListStarted = tbTaskListStarted;
	}

	public Collection<TbTask> getTbTaskListFinished() {
		return tbTaskListFinished;
	}

	public void setTbTaskListFinished(Collection<TbTask> tbTaskListFinished) {
		this.tbTaskListFinished = tbTaskListFinished;
	}

	public Collection<TbTask> getTbTaskListDeleted() {
		return tbTaskListDeleted;
	}

	public void setTbTaskListDeleted(Collection<TbTask> tbTaskListDeleted) {
		this.tbTaskListDeleted = tbTaskListDeleted;
	}

	public TbTask getTbTask() {
		return tbTask;
	}

	public void setTbTask(TbTask tbTask) {
		this.tbTask = tbTask;
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

	public TbKeyDTO getTbKeyDTO() {
		return tbKeyDTO;
	}

	public void setTbKeyDTO(TbKeyDTO tbKeyDTO) {
		this.tbKeyDTO = tbKeyDTO;
	}

	public TbKey getTbKey() {
		return tbKey;
	}

	public void setTbKey(TbKey tbKey) {
		this.tbKey = tbKey;
	}
}
