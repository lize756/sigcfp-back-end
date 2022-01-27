package com.edu.icesi.sigcfp.sigcfpbackendbusiness.entity.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URL;
import java.util.List;


/**
 * The persistent class for the CURRICULUM_PDF database table.
 */
@Entity
@Table(name = "CURRICULUM_PDF")
@NamedQuery(name = "CurriculumPdf.findAll", query = "SELECT c FROM CurriculumPdf c")
public class CurriculumPdf implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CU_PDF_ID", unique = true, nullable = false, precision = 10)
    private long cuPdfId;

    @Column(name = "CU_PDF_FILE", nullable = false)
    private URL cuPdfFile;

    //bi-directional many-to-one association to Curriculum
    @OneToMany(mappedBy = "curriculumPdf")
    private List<Curriculum> curriculums;

    //bi-directional many-to-one association to Curriculum
    @ManyToOne
    @JoinColumn(name = "CURRICULUM_CURR_ID", nullable = false)
    private Curriculum curriculum;

    public CurriculumPdf() {
    }

    public long getCuPdfId() {
        return this.cuPdfId;
    }

    public void setCuPdfId(long cuPdfId) {
        this.cuPdfId = cuPdfId;
    }

    public URL getCuPdfFile() {
        return this.cuPdfFile;
    }

    public void setCuPdfFile(URL cuPdfFile) {
        this.cuPdfFile = cuPdfFile;
    }

    public List<Curriculum> getCurriculums() {
        return this.curriculums;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }

    public Curriculum addCurriculum(Curriculum curriculum) {
        getCurriculums().add(curriculum);
        curriculum.setCurriculumPdf(this);

        return curriculum;
    }

    public Curriculum removeCurriculum(Curriculum curriculum) {
        getCurriculums().remove(curriculum);
        curriculum.setCurriculumPdf(null);

        return curriculum;
    }

    public Curriculum getCurriculum() {
        return this.curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

}