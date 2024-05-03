package com.topclass.schedulesystem.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class userData {

    private String classID;
    private String professor_name;

    public userData() {
    }

    public userData(String classID, String professor_name) {
        this.classID = classID;
        this.professor_name = professor_name;
    }



    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getProfessor_name() {
        return professor_name;
    }

    public void setProfessor_name(String professor_name) {
        this.professor_name = professor_name;
    }
}
