package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NOTI_TYPE database table.
 * 
 */
@Entity
@Table(name="NOTI_TYPE")
@NamedQuery(name="NotiType.findAll", query="SELECT n FROM NotiType n")
public class NotiType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="NOTI_TYPE_NOTITYPEID_GENERATOR", sequenceName = "NOTI_TYPE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTI_TYPE_NOTITYPEID_GENERATOR")
	@Column(name="NOTI_TYPE_ID", unique=true, nullable=false, precision=10)
	private long notiTypeId;

	@Column(name="NOTI_TYPE_NAME", nullable=false, length=255)
	private String notiTypeName;

	//bi-directional many-to-one association to Noti
	@ManyToOne
	@JoinColumn(name="NOTI_NOTI_ID")
	private Noti noti;

	public NotiType() {
	}

	public long getNotiTypeId() {
		return this.notiTypeId;
	}

	public void setNotiTypeId(long notiTypeId) {
		this.notiTypeId = notiTypeId;
	}

	public String getNotiTypeName() {
		return this.notiTypeName;
	}

	public void setNotiTypeName(String notiTypeName) {
		this.notiTypeName = notiTypeName;
	}

	public Noti getNoti() {
		return this.noti;
	}

	public void setNoti(Noti noti) {
		this.noti = noti;
	}

}
