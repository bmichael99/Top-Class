package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.Professor;
import com.topclass.schedulesystem.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService{



    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();

    }



}


