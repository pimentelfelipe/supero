package dto;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

public class DTOGeneric<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private T entity;
	private boolean success = true;
	private Collection<T> collection;
	private Map<String, String> parameterValue;

	public DTOGeneric() {
	}
	
	public DTOGeneric(boolean success) {
		this.success = success;
	}
	
	public DTOGeneric(T entity) {
		this.entity = entity;
	}
	
	public DTOGeneric(Collection<T> collection) {
		this.collection = collection;
	}

	public DTOGeneric(T entity,Collection<T> collection) {
		this.entity = entity;
		this.collection = collection;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Collection<T> getCollection() {
		return collection;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	public void setSuccessFalse() {
		this.success = false;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getClasse() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Map<String, String> getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(Map<String, String> parameterValue) {
		this.parameterValue = parameterValue;
	}
}
