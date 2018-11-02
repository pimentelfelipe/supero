package dto;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

public class DTOGeneric<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private T entity;
	private boolean sucesso = true;
	private Collection<T> collection;
	private Map<String, String> parametroValor;

	public DTOGeneric() {
	}
	
	public DTOGeneric(boolean sucesso) {
		this.sucesso = sucesso;
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
	
	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public void setSucessoFalse() {
		this.sucesso = false;
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getClasse() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Map<String, String> getParametroValor() {
		return parametroValor;
	}

	public void setParametroValor(Map<String, String> parametroValor) {
		this.parametroValor = parametroValor;
	}
}
