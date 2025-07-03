package com.dds.flippers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dds.flippers.model.ClassModel;
import com.dds.flippers.service.ClassService;

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
