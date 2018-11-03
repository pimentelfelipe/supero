package model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column
	private String txUserName;
	
	@Column
	private String txPassword;
	
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

	public String getTxKey() {
		return txKey;
	}

	public void setTxKey(String txKey) {
		this.txKey = txKey;
	}
}
