package com.example.PPE1.DAO;
import com.example.PPE1.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie , Long> {

}
