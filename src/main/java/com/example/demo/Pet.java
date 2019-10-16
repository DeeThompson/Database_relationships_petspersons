package com.example.demo;

import javax.persistence.*;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Petid;
    private String PetName;
    private String age;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "Petid")
    private Pet pet;

    public long getPetid() {
        return Petid;
    }

    public void setPetid(long petid) {
        Petid = petid;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
