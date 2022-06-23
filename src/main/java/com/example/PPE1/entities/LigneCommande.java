package com.example.PPE1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@Getter
    @Setter
	private Long id;

    @Getter
    @Setter
	@ManyToOne
	@JoinColumn(name="id_deal")
    private Deal deal;

    @Getter
    @Setter
    private Long quantite = 0L;
 
    @Getter
    @Setter
    private Double prix;
    
    @Getter
    @Setter
    private Double prix_total;
}
