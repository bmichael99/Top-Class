package com.topclass.schedulesystem.service;

import com.topclass.schedulesystem.model.Professor;
import com.topclass.schedulesystem.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Professor> getClassesNotTaken(List<String> notTakenClasses) {
        List<Professor> allClasses = professorRepository.findAll();

        // Logging the contents of notTakenClasses
        System.out.println("Not Taken Classes:");
        notTakenClasses.forEach(System.out::println);

        List<Professor> filteredClasses = allClasses.stream()
                .filter(professor -> notTakenClasses.contains(professor.getClassID()))
                .collect(Collectors.toList());

        // Logging the contents of filteredClasses
        System.out.println("Filtered Classes:");
        filteredClasses.forEach(professor -> System.out.println(professor.getClassID()));

        return filteredClasses;
    }



}


