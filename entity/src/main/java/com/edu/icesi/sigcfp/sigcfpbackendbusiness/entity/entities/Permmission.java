package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * The persistent class for the PERMMISSION database table.
 */
@Entity
@Table(name = "PERMMISSION")
@NamedQuery(name = "Permmission.findAll", query = "SELECT p FROM Permmission p")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Permmission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "PERMMISSION_PERMID_GENERATOR", sequenceName = "PERMISSION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMMISSION_PERMID_GENERATOR")
    @Column(name = "PERM_ID", unique = true, nullable = false)
    private long permId;

    @Column(name = "PERM_DESCRIPTION", length = 1000)
    private String permDescription;

    @Column(name = "PERM_ISACTIVE", nullable = false, length = 1)
    private String permIsactive;

    @Column(name = "PERM_NAME", nullable = false, length = 255)
    private String permName;

    //bi-directional many-to-one association to Rolee
    @ManyToOne
    @JoinColumn(name = "ROLEE_ROLE_ID")
    @JsonIgnore
    private Rolee rolee;

    public Permmission() {
    }

    public long getPermId() {
        return this.permId;
    }

    public void setPermId(long permId) {
        this.permId = permId;
    }

    public String getPermDescription() {
        return this.permDescription;
    }

    public void setPermDescription(String permDescription) {
        this.permDescription = permDescription;
    }

    public String getPermIsactive() {
        return this.permIsactive;
    }

    public void setPermIsactive(String permIsactive) {
        this.permIsactive = permIsactive;
    }

    public String getPermName() {
        return this.permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public Rolee getRolee() {
        return this.rolee;
    }

    public void setRolee(Rolee rolee) {
        this.rolee = rolee;
    }

}
