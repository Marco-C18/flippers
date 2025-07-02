package com.dds.flippers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dds.flippers.models.ClassModel;
import com.dds.flippers.services.ClassService;

@RestController
@RequestMapping("/admin/api/class")
public class ApiAdminController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public List<ClassModel> listarClases() {
        return classService.getAllClasses();
    }

}
