package com.example.PPE1.DAO;

import com.example.PPE1.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DealRepository extends JpaRepository<Deal, Long> {
	@Query("select d from Deal d where d.categorie.id = :id")
	List<Deal> findAllByCategorieId(Long id);

}
