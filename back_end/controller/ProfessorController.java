package com.topclass.schedulesystem.controller;

import com.topclass.schedulesystem.model.Professor;
import com.topclass.schedulesystem.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
@CrossOrigin
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping("/add")
    public String add(@RequestBody Professor professor){
        professorService.saveProfessor(professor);
        return "New professor is added";
    }

    @GetMapping("/getAll")
    public List<Professor> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @GetMapping("/getClassesNotTaken")
    public List<Professor> getClassesNotTaken(@RequestParam("notTaken") List<String> notTakenClasses) {
        return professorService.getClassesNotTaken(notTakenClasses);
    }

}
