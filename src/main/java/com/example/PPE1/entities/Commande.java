package com.example.PPE1.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter
    @Setter
	private Long id;

	public static List<LigneCommande> panier;
	
	static {
		panier = new ArrayList<LigneCommande>();
	}
}
