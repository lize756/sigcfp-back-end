package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the TRIGGERR database table.
 */
@Entity
@Table(name = "TRIGGERR")
@NamedQuery(name = "Triggerr.findAll", query = "SELECT t FROM Triggerr t")
public class Triggerr implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRIG_ID", unique = true, nullable = false, precision = 10)
    private long trigId;

    @Column(name = "TRIG_DESCRIPTION", length = 1000)
    private String trigDescription;

    @Column(name = "TRIG_NAME", nullable = false, length = 255)
    private String trigName;

    @Column(name = "TRIG_SCOPE", nullable = false, length = 255)
    private String trigScope;

    //bi-directional many-to-many association to Noti
    @ManyToMany
    @JoinTable(
            name = "NOTI_AUTO_NOTIF"
            , joinColumns = {
            @JoinColumn(name = "TRIGGERR_TRIG_ID", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "NOTI_NOTI_ID", nullable = false)
    }
    )
    private List<Noti> notis;

    public Triggerr() {
    }

    public long getTrigId() {
        return this.trigId;
    }

    public void setTrigId(long trigId) {
        this.trigId = trigId;
    }

    public String getTrigDescription() {
        return this.trigDescription;
    }

    public void setTrigDescription(String trigDescription) {
        this.trigDescription = trigDescription;
    }

    public String getTrigName() {
        return this.trigName;
    }

    public void setTrigName(String trigName) {
        this.trigName = trigName;
    }

    public String getTrigScope() {
        return this.trigScope;
    }

    public void setTrigScope(String trigScope) {
        this.trigScope = trigScope;
    }

    public List<Noti> getNotis() {
        return this.notis;
    }

    public void setNotis(List<Noti> notis) {
        this.notis = notis;
    }

}
