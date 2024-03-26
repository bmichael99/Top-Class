package com.topclass.schedulesystem.model;

import java.io.Serializable;

public class ProfessorID implements Serializable {
    private String name;
    private String classID;

    public ProfessorID() {
    }

    public ProfessorID(String name, String classID) {
        this.name = name;
        this.classID = classID;
    }
}
