package com.topclass.schedulesystem.repository;

import org.springframework.stereotype.Repository;

import com.topclass.schedulesystem.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor,String>{
}
