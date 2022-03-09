package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * The persistent class for the NOTI_TYPE database table.
 */
@Entity
@Table(name = "NOTI_TYPE")
@NamedQuery(name = "NotiType.findAll", query = "SELECT n FROM NotiType n")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class NotiType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "NOTI_TYPE_NOTITYPEID_GENERATOR", sequenceName = "NOTI_TYPE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTI_TYPE_NOTITYPEID_GENERATOR")
    @Column(name = "NOTI_TYPE_ID", unique = true, nullable = false, precision = 10)
    private long notiTypeId;

    @Column(name = "NOTI_TYPE_NAME", nullable = false, length = 255)
    private String notiTypeName;

    //bi-directional many-to-one association to Noti
    @ManyToOne
    @JoinColumn(name = "NOTI_NOTI_ID")
    @JsonIgnore
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
