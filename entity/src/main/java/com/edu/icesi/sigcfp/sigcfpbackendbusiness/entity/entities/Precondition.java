package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRECONDITION database table.
 * 
 */
@Entity
@Table(name="PRECONDITION")
@NamedQuery(name="Precondition.findAll", query="SELECT p FROM Precondition p")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Precondition implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PRECONDITION_PRECONDID_GENERATOR", allocationSize = 1, sequenceName = "PRECONDITION_SEQ"  )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRECONDITION_PRECONDID_GENERATOR")
	@Column(name="PRECOND_ID", unique=true, nullable=false, precision=10)
	private long precondId;

	@Column(name="PRECOND_LOGICALOPERAND", length=1)
	private String precondLogicaloperand;

	//bi-directional many-to-one association to Noti
	@ManyToOne
	@JoinColumn(name="NOTI_NOTI_ID")
	@JsonIgnore
	private Noti noti;

	public Precondition() {
	}

	public long getPrecondId() {
		return this.precondId;
	}

	public void setPrecondId(long precondId) {
		this.precondId = precondId;
	}

	public String getPrecondLogicaloperand() {
		return this.precondLogicaloperand;
	}

	public void setPrecondLogicaloperand(String precondLogicaloperand) {
		this.precondLogicaloperand = precondLogicaloperand;
	}

	public Noti getNoti() {
		return this.noti;
	}

	public void setNoti(Noti noti) {
		this.noti = noti;
	}

}
