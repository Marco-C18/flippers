
package com.dds.flippers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "promo")
public class PromoModel {

    @OneToOne
    @JoinColumn(name = "id_class") // FK hacia la clase
    @JsonBackReference
    private ClassModel classModel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPromo;

    @Column(name = "title_promo")
    private String titlePromo;

    @Column(name = "description_promo")
    private String descriptionPromo;

    @Column(name = "discount_percent")
    private Double discountPercent;

    public PromoModel() {
    }

    public PromoModel(String titlePromo, String descriptionPromo, Double discountPercent,
            ClassModel classModel) {
        this.titlePromo = titlePromo;
        this.descriptionPromo = descriptionPromo;
        this.discountPercent = discountPercent;
        this.classModel = classModel;
    }

    public Integer getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Integer idPromo) {
        this.idPromo = idPromo;
    }

    public String getTitlePromo() {
        return titlePromo;
    }

    public void setTitlePromo(String titlePromo) {
        this.titlePromo = titlePromo;
    }

    public String getDescriptionPromo() {
        return descriptionPromo;
    }

    public void setDescriptionPromo(String descriptionPromo) {
        this.descriptionPromo = descriptionPromo;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }
}