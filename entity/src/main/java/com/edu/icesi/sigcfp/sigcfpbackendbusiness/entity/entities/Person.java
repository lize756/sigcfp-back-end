package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the PERSON database table.
 */
@Entity
@Table(name = "PERSON")
@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERS_ID", unique = true, nullable = false, precision = 10)
    private long persId;

    @Column(name = "PERS_DOCUMENT", nullable = false, length = 20)
    private String persDocument;

    @Column(name = "PERS_EMAIL", nullable = false, length = 255)
    private String persEmail;

    @Column(name = "PERS_FIRST_NAME", nullable = false, length = 255)
    private String persFirstName;

    @Column(name = "PERS_GENRE", nullable = false, length = 4)
    private String persGenre;

    @Column(name = "PERS_LAST_NAME", nullable = false, length = 255)
    private String persLastName;

    //bi-directional many-to-one association to Curriculum
    @OneToMany(mappedBy = "person")
    private List<Curriculum> curriculums;

    //bi-directional many-to-one association to Userr
    @ManyToOne
    @JoinColumn(name = "USERR_USER_ID", nullable = false)
    private Userr userr;

    //bi-directional many-to-one association to Userr
    @OneToMany(mappedBy = "person")
    private List<Userr> userrs;

    public Person() {
    }

    public long getPersId() {
        return this.persId;
    }

    public void setPersId(long persId) {
        this.persId = persId;
    }

    public String getPersDocument() {
        return this.persDocument;
    }

    public void setPersDocument(String persDocument) {
        this.persDocument = persDocument;
    }

    public String getPersEmail() {
        return this.persEmail;
    }

    public void setPersEmail(String persEmail) {
        this.persEmail = persEmail;
    }

    public String getPersFirstName() {
        return this.persFirstName;
    }

    public void setPersFirstName(String persFirstName) {
        this.persFirstName = persFirstName;
    }

    public String getPersGenre() {
        return this.persGenre;
    }

    public void setPersGenre(String persGenre) {
        this.persGenre = persGenre;
    }

    public String getPersLastName() {
        return this.persLastName;
    }

    public void setPersLastName(String persLastName) {
        this.persLastName = persLastName;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        getCurriculums().add(curriculum);
        curriculum.setPerson(this);

        return curriculum;
    }

    public Curriculum removeCurriculum(Curriculum curriculum) {
        getCurriculums().remove(curriculum);
        curriculum.setPerson(null);

        return curriculum;
    }

    public Userr getUserr() {
        return this.userr;
    }

    public void setUserr(Userr userr) {
        this.userr = userr;
    }

    public List<Userr> getUserrs() {
        return this.userrs;
    }

    public void setUserrs(List<Userr> userrs) {
        this.userrs = userrs;
    }

    public Userr addUserr(Userr userr) {
        getUserrs().add(userr);
        userr.setPerson(this);

        return userr;
    }

    public Userr removeUserr(Userr userr) {
        getUserrs().remove(userr);
        userr.setPerson(null);

        return userr;
    }

}
