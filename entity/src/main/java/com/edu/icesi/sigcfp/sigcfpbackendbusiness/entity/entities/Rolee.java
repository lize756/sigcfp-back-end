package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the ROLEE database table.
 */
@Entity
@Table(name = "ROLEE")
@NamedQuery(name = "Rolee.findAll", query = "SELECT r FROM Rolee r")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Rolee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ROLEE_ROLEID_GENERATOR", allocationSize = 1, sequenceName = "ROLEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLEE_ROLEID_GENERATOR")
    @Column(name = "ROLE_ID", unique = true, nullable = false, precision = 10)
    private long roleId;

    @Column(name = "ROLE_DESCRIPTION", length = 1000)
    private String roleDescription;

    @Column(name = "ROLE_NAME", length = 255)
    private String roleName;

    //bi-directional many-to-one association to Permmission
    @OneToMany(mappedBy = "rolee")
    @JsonIgnore
    private List<Permmission> permmissions;

    //bi-directional many-to-one association to Userr
    //@ManyToOne
    //@JoinColumn(name="USERR_USER_ID")
    //@JsonIgnore
    @OneToMany(mappedBy = "rolee")
    @JsonIgnore
    private List<Userr> userrs;

    public Rolee() {
    }

    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Userr> getUserrs() {
        return userrs;
    }

    public void setUserrs(List<Userr> userrs) {
        this.userrs = userrs;
    }

    public Userr addUserr(Userr userr) {
        getUserrs().add(userr);
        userr.setRolee(this);

        return userr;
    }

    public Userr removeUserr(Userr userr) {
        getUserrs().remove(userr);
        userr.setRolee(null);

        return userr;
    }


    public List<Permmission> getPermmissions() {
        return this.permmissions;
    }

    public void setPermmissions(List<Permmission> permmissions) {
        this.permmissions = permmissions;
    }

    public Permmission addPermmission(Permmission permmission) {
        getPermmissions().add(permmission);
        permmission.setRolee(this);

        return permmission;
    }

    public Permmission removePermmission(Permmission permmission) {
        getPermmissions().remove(permmission);
        permmission.setRolee(null);

        return permmission;
    }


}
