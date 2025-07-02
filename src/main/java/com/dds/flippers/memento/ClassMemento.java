package com.dds.flippers.memento;

public class ClassMemento {
    private final String titleClass;
    private final String contentClass;
    private final Integer ageMinClass;
    private final Integer ageMaxClass;
    private final Double priceClass;
    private final Integer durationClass;
    private final String modeClass;

    public ClassMemento(String titleClass, String contentClass, Integer ageMinClass, Integer ageMaxClass,
            Double priceClass, Integer durationClass, String modeClass) {
        this.titleClass = titleClass;
        this.contentClass = contentClass;
        this.ageMinClass = ageMinClass;
        this.ageMaxClass = ageMaxClass;
        this.priceClass = priceClass;
        this.durationClass = durationClass;
        this.modeClass = modeClass;
    }

    public String getTitleClass() {
        return titleClass;
    }

    public String getContentClass() {
        return contentClass;
    }

    public Integer getAgeMinClass() {
        return ageMinClass;
    }

    public Integer getAgeMaxClass() {
        return ageMaxClass;
    }

    public Double getPriceClass() {
        return priceClass;
    }

    public Integer getDurationClass() {
        return durationClass;
    }

    public String getModeClass() {
        return modeClass;
    }
}
