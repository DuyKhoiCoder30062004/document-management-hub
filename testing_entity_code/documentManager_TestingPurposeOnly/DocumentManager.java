package com.saigontechnologyintern.document_management;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

//JPA - Hibernate
@Entity
//Persistence entity should have primary key
//doc_id, title, owner_id, metadata, created_at, updated_at, folder_id
public class DocumentManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //lien tuc tang ID vi vay nen can` ALTER SEQUENCE document_manager_id_seq RESTART WITH 1; de reset ID ve 1
    //Auto-increment
    //Property nao theo trước thì làm primary key
    private Integer id;
    //@Transient: tell you that this field is not to be a column
    private String name;
    // neeus có field age, getAge có logic có thể nhu sau
    // return Period.between(this.dob, LocalDate.now()).getYears(); //dob: LocalDate
    public DocumentManager() {
    }
    //Linux: C
    //System developer: 90%.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DocumentManager that = (DocumentManager) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentManager(Integer id, String name) {
        this.id = id;
        this.name = name;

    }
}
