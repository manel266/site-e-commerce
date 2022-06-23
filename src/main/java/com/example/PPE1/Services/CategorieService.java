package com.example.PPE1.Services;

import com.example.PPE1.DAO.CategorieRepository;
import com.example.PPE1.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
	@Autowired
	CategorieRepository cr;

	public List<Categorie> getAll() {
		return cr.findAll();
	}

	public void save(Categorie c) {
		cr.save(c);
	}

	public void removeById(Long id) {
		cr.deleteById(id);
	}

	public Optional<Categorie> getById(Long id) {
		return cr.findById(id);
	}
}
