package com.example.PPE1.Services;

import com.example.PPE1.DAO.CommandeRepository;
import com.example.PPE1.entities.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

	@Autowired
	CommandeRepository cor;

	public void save(Commande c) {
		cor.save(c);
	}
}
