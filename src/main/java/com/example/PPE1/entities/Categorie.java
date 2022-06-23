package com.example.PPE1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@NoArgsConstructor
@Data
@Entity
public class Categorie{


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;


    private String nom;

    @OneToMany( mappedBy="categorie")
    private List<Deal> deals;





}
