package com.topclass.schedulesystem.model;

import jakarta.persistence.*;

@Entity
@IdClass(ProfessorID.class)
public class Professor {


    @Id
    private String name;

    @Id
    private String classID;

    private String classTitle;

    public Professor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }
}
