package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the PRECONDITION database table.
 */
@Entity
@Table(name = "PRECONDITION")
@NamedQuery(name = "Precondition.findAll", query = "SELECT p FROM Precondition p")
public class Precondition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRECOND_ID", unique = true, nullable = false, precision = 10)
    private long precondId;

    @Column(name = "PRECOND_LOGICALOPERAND", nullable = false, length = 1)
    private String precondLogicaloperand;

    //bi-directional many-to-one association to Noti
    @ManyToOne
    @JoinColumn(name = "NOTI_NOTI_ID", nullable = false)
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
