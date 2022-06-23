package com.example.PPE1.controllers;

import com.example.PPE1.DAO.UserRepository;
import com.example.PPE1.Services.CategorieService;
import com.example.PPE1.Services.DealService;
import com.example.PPE1.entities.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	protected DealService ds;
	@Autowired
	protected CategorieService cas;
	@Autowired
	protected UserRepository ur;

	@GetMapping({"/login"})
	public String getlogin(Model model) {
		return "login";
	}

	@GetMapping({"/register"})
	public String getregister(Model model) {
		return "inscrire";
	}

	@GetMapping({ "/", "/home", "/accueil" })
	public String accueil(Model model, @RequestParam("id") Optional<Long> id) {
		if (!id.isPresent()) {
			model.addAttribute("deals", ds.getAll());
		} else {
			model.addAttribute("deals", ds.getAllByCategorieId(id.get()));
		}
		model.addAttribute("categories", cas.getAll());
		model.addAttribute("cartCount", Commande.panier.size());
		return "accueil";
	}

	@GetMapping({"/admin", "/admin/home", "/admin/accueil"})
	public String home(Model model) {
		model.addAttribute("user", ur.findAll());
		return "admin_accueil";
	}
	
//	@GetMapping({ "/categories/{id}" })
//	public String choisirParCat(Model model, @PathVariable Long id) {
//		model.addAttribute("categorie", cas.getAll());
//		model.addAttribute("cartCount", Commande.panier.size());
//		model.addAttribute("deal", ds.getAllByCategorieId(id));
//		return "accueil";
//	}

	@GetMapping("/deals/{id}")
	public String viewDeal(Model model, @PathVariable Long id) {
		model.addAttribute("deal", ds.getDealById(id).get());
		model.addAttribute("cartCount", Commande.panier.size());
		return "deals_edit";
	}

}
