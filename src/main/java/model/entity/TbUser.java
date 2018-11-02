package model.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column
	private String txUserName;
	
	@Column
	private String txPassword;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbUser", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private Collection<TbKey> keyCollection;
	
	
	@Transient
	private String txKey;
	
	
	public TbUser() {
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxUserName() {
		return txUserName;
	}

	public void setTxUserName(String txUserName) {
		this.txUserName = txUserName;
	}

	public String getTxPassword() {
		return txPassword;
	}

	public void setTxPassword(String txPassword) {
		this.txPassword = txPassword;
	}



	public Collection<TbKey> getKeyCollection() {
		return keyCollection;
	}



	public void setKeyCollection(Collection<TbKey> keyCollection) {
		this.keyCollection = keyCollection;
	}



	public String getTxKey() {
		return txKey;
	}



	public void setTxKey(String txKey) {
		this.txKey = txKey;
	}

}
