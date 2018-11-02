package model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TbKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@JoinColumn(name = "idUser", referencedColumnName = "id")
	@ManyToOne
	private TbUser tbUser;
	
	
	@Column
	private String txKey;
	
	@Column
	private Date dtStart;
	
	@Column
	private Date dtEnd;
	
	
	
	public TbKey() {
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public TbUser getTbUser() {
		return tbUser;
	}



	public void setTbUser(TbUser tbUser) {
		this.tbUser = tbUser;
	}



	public String getTxKey() {
		return txKey;
	}



	public void setTxKey(String txKey) {
		this.txKey = txKey;
	}



	public Date getDtStart() {
		return dtStart;
	}



	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}



	public Date getDtEnd() {
		return dtEnd;
	}



	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

}
