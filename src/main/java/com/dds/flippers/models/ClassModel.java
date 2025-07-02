package com.dds.flippers.models;

import com.dds.flippers.memento.ClassMemento;

import jakarta.persistence.*;

@Entity
@Table(name = "class")
public class ClassModel {

    @OneToOne(mappedBy = "classModel", cascade = CascadeType.ALL)
    private PromoModel promoModel;

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClass;

    @Column(name = "title_class")
    private String titleClass;

    @Column(name = "content_class")
    private String contentClass;

    @Column(name = "age_min_class")
    private Integer ageMinClass;

    @Column(name = "age_max_class")
    private Integer ageMaxClass;

    @Column(name = "price_class")
    private Double priceClass;

    @Column(name = "duration_class")
    private Integer durationClass;

    @Column(name = "mode_class")
    private String modeClass;

    public ClassModel() {
    }

    public ClassModel(String titleClass, String contentClass, Integer ageMinClass, Integer ageMaxClass,
            Double priceClass, Integer durationClass, String modeClass) {
        this.titleClass = titleClass;
        this.contentClass = contentClass;
        this.ageMinClass = ageMinClass;
        this.ageMaxClass = ageMaxClass;
        this.priceClass = priceClass;
        this.durationClass = durationClass;
        this.modeClass = modeClass;
    }

    public Integer getIdClass() {
        return idClass;
    }

    public void setIdClass(Integer idClass) {
        this.idClass = idClass;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public void setTitleClass(String titleClass) {
        this.titleClass = titleClass;
    }

    public String getContentClass() {
        return contentClass;
    }

    public void setContentClass(String contentClass) {
        this.contentClass = contentClass;
    }

    public Integer getAgeMinClass() {
        return ageMinClass;
    }

    public void setAgeMinClass(Integer ageMinClass) {
        this.ageMinClass = ageMinClass;
    }

    public Integer getAgeMaxClass() {
        return ageMaxClass;
    }

    public void setAgeMaxClass(Integer ageMaxClass) {
        this.ageMaxClass = ageMaxClass;
    }

    public Double getPriceClass() {
        return priceClass;
    }

    public void setPriceClass(Double priceClass) {
        this.priceClass = priceClass;
    }

    public Integer getDurationClass() {
        return durationClass;
    }

    public void setDurationClass(Integer durationClass) {
        this.durationClass = durationClass;
    }

    public String getModeClass() {
        return modeClass;
    }

    public void setModeClass(String modeClass) {
        this.modeClass = modeClass;
    }

    // Implementacion Memento
    public ClassMemento saveToMemento() {
        return new ClassMemento(titleClass, contentClass, ageMinClass, ageMaxClass, priceClass, durationClass,
                modeClass);
    }

    public void restoreFromMemento(ClassMemento memento) {
        this.titleClass = memento.getTitleClass();
        this.contentClass = memento.getContentClass();
        this.ageMinClass = memento.getAgeMinClass();
        this.ageMaxClass = memento.getAgeMaxClass();
        this.priceClass = memento.getPriceClass();
        this.durationClass = memento.getDurationClass();
        this.modeClass = memento.getModeClass();
    }

}
