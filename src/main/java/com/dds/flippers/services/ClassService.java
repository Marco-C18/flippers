package com.dds.flippers.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dds.flippers.memento.ClassCaretaker;
import com.dds.flippers.memento.ClassMemento;
import com.dds.flippers.models.ClassModel;
import com.dds.flippers.repositories.ClassRepository;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public List<ClassModel> getAllClasses() {
        return classRepository.findAll();
    }

    public void saveClass(ClassModel clase) {
        classRepository.save(clase);
    }

    public ClassModel getClassById(Integer id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Clase no encontrada"));

    }

    public void deleteClass(Integer id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Clase no encontrada para eliminar");
        }
    }

    // Implementacion Memento
    private final Map<Integer, ClassCaretaker> respaldoClases = new HashMap<>();

    public void saveClassWithBackup(ClassModel clase) {
        if (clase.getIdClass() != null && classRepository.existsById(clase.getIdClass())) {
            ClassModel original = getClassById(clase.getIdClass());
            ClassMemento backup = original.saveToMemento();
            respaldoClases
                    .computeIfAbsent(clase.getIdClass(), k -> new ClassCaretaker())
                    .saveMemento(backup);
        }
        classRepository.save(clase);
    }

    public boolean restoreLastVersion(Integer idClass) {
        ClassCaretaker caretaker = respaldoClases.get(idClass);
        if (caretaker != null && caretaker.history()) {
            ClassMemento memento = caretaker.restore();
            ClassModel clase = classRepository.findById(idClass).orElse(null);
            if (clase != null && memento != null) {
                clase.restoreFromMemento(memento);
                classRepository.save(clase);
                return true;
            }
        }
        return false;
    }
}
