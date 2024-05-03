package com.topclass.schedulesystem.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.topclass.schedulesystem.model.userData;
import java.util.List;

@Entity
public class User {

    @Id
    private String ID;

    @ElementCollection
    private List<userData> data;


    public User() {
    }

    public User(String ID, List<userData> data) {
        this.ID = ID;
        this.data = data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<userData> getData() {
        return data;
    }

    public void setData(List<userData> data) {
        this.data = data;
    }
}

