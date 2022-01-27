package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the NOTI_TYPE database table.
 */
@Entity
@Table(name = "NOTI_TYPE")
@NamedQuery(name = "NotiType.findAll", query = "SELECT n FROM NotiType n")
public class NotiType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTI_TYPE_ID", unique = true, nullable = false)
    private long notiTypeId;

    @Column(name = "NOTI_TYPE_NAME", nullable = false, length = 255)
    private String notiTypeName;

    //bi-directional many-to-one association to Noti
    @OneToMany(mappedBy = "notiType")
    private List<Noti> notis;

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

    public List<Noti> getNotis() {
        return this.notis;
    }

    public void setNotis(List<Noti> notis) {
        this.notis = notis;
    }

    public Noti addNoti(Noti noti) {
        getNotis().add(noti);
        noti.setNotiType(this);

        return noti;
    }

    public Noti removeNoti(Noti noti) {
        getNotis().remove(noti);
        noti.setNotiType(null);

        return noti;
    }

}
