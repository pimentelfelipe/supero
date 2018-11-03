package model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TbTask implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String txTitle;
	
	@Column
	private String txStatus;
	
	@Column
	private String txDesc;
	
	@Column
	private Date dtStart;
	
	@Column
	private Date dtEdit;
	
	@Column
	private Date dtDelete;
	
	@Column
	private Date dtEnd;
	
	
	public TbTask() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTxTitle() {
		return txTitle;
	}

	public void setTxTitle(String txTitle) {
		this.txTitle = txTitle;
	}

	public String getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}

	public String getTxDesc() {
		return txDesc;
	}

	public void setTxDesc(String txDesc) {
		this.txDesc = txDesc;
	}

	public Date getDtStart() {
		return dtStart;
	}

	public void setDtStart(Date dtStart) {
		this.dtStart = dtStart;
	}

	public Date getDtEdit() {
		return dtEdit;
	}

	public void setDtEdit(Date dtEdit) {
		this.dtEdit = dtEdit;
	}

	public Date getDtDelete() {
		return dtDelete;
	}

	public void setDtDelete(Date dtDelete) {
		this.dtDelete = dtDelete;
	}

	public Date getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(Date dtEnd) {
		this.dtEnd = dtEnd;
	}

}
