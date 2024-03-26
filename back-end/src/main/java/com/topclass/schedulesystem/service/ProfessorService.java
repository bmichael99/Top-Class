package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.Professor;

import java.util.List;
public interface ProfessorService {

    public Professor saveProfessor(Professor professor);
    public List<Professor> getAllProfessors();
}
