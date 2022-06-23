package com.example.PPE1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
@ToString(exclude="categorie")
public class Deal {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String nom;

    @Getter
    @Setter
    private Double ancien_prix;
    @Getter
    @Setter

    private Double pourcentage;
    @Getter
    @Setter

    private Double nouveau_prix;
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date temps_rest;

    @Getter
    @Setter
    private Long quantite;

    @Getter
    @Setter
    private String image;
    
    @Getter
    @Setter
    private int avis;
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
	@ManyToOne
	@JoinColumn(name="id_categorie")
    private Categorie categorie;
}
