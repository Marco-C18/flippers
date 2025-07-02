package com.dds.flippers.services;

import com.dds.flippers.models.ClassModel;
import com.dds.flippers.models.PromoModel;
import com.dds.flippers.repositories.ClassRepository;
import com.dds.flippers.repositories.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private ClassRepository classRepository;

    public List<PromoModel> getAllPromos() {
        return promoRepository.findAll();
    }

    public List<ClassModel> getAllClasses() {
        return classRepository.findAll();
    }

    public void createPromo(PromoModel promo, Integer idClass) {
        ClassModel clase = classRepository.findById(idClass)
                .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada con id: " + idClass));
        promo.setClassModel(clase);
        promoRepository.save(promo);
    }

    public PromoModel getPromoById(Integer id) {
        return promoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Promoción no encontrada"));
    }

    public void saveEditedPromo(PromoModel promo) {
        promoRepository.save(promo);
    }

    public void deletePromo(Integer id) {
        PromoModel promo = getPromoById(id);
        ClassModel clase = promo.getClassModel();

        if (clase != null) {
            clase.setPromoModel(null);
            promo.setClassModel(null);
            classRepository.save(clase);
        }

        promoRepository.save(promo);
        promoRepository.deleteById(id);
    }
}
