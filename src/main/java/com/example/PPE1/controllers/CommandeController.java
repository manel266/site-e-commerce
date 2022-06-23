package com.example.PPE1.controllers;

import com.example.PPE1.Services.CommandeService;
import com.example.PPE1.Services.DealService;
import com.example.PPE1.entities.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/commande")
public class CommandeController {

	@Autowired
	DealService ds;
	CommandeService cs;

	@PostMapping("/panier/add")
	public String panier_add(@RequestBody MultiValueMap<String, String> formData) {
		String id = formData.get("id").get(0);
		String quantite = formData.get("quantite").get(0);
		
		LigneCommande lc = new LigneCommande();
		
		boolean trouve = false;
		
		for (int counter = 0; counter < Commande.panier.size(); counter++) {
			if (Commande.panier.get(counter).getDeal().getId() == Long.parseLong(id)) {
				lc = Commande.panier.get(counter);
				trouve = true;
			}
		}


		Optional<Deal> d = ds.getDealById(Long.parseLong(id));
		if (d.isPresent()) {
			lc.setDeal(d.get());
			lc.setQuantite(lc.getQuantite() + Long.parseLong(quantite));
			lc.setPrix(d.get().getNouveau_prix());
			lc.setPrix_total(lc.getQuantite() * d.get().getNouveau_prix());
			if (!trouve)
				Commande.panier.add(lc);
		}
		return "redirect:/accueil";

	}

	@GetMapping("/panier/list")
	public String panier_list(Model model) {
		model.addAttribute("cartCount", Commande.panier.size());
		model.addAttribute("total", Commande.panier.stream().mapToDouble(LigneCommande::getPrix_total).sum());
		model.addAttribute("panier", Commande.panier);
		return "panier_list";
	}

	@GetMapping("/panier/delete/{id}")
	public String panier_remove(@PathVariable Long id) {
		for (int counter = 0; counter < Commande.panier.size(); counter++) {
			if (Commande.panier.get(counter).getDeal().getId() == id ) {
				Commande.panier.remove(counter);
			}
		}
		return "redirect:/commande/panier/list";
	}

	@GetMapping("/panier/valider")
	public String panier_valider(Model model) {
		model.addAttribute("total", Commande.panier.stream().mapToDouble(LigneCommande::getPrix_total).sum());
		return "panier_valider";
	}

	@PostMapping("/panier/payer")
	public String panier_payer(Model model) {
		return "panier_list";
	}

	@GetMapping("/panier/quantite/deal/{id}")
    public String panier_quantite(Model model ,@PathVariable Long id) {
        model.addAttribute("deal", ds.getDealById(id).get());
        model.addAttribute("cartCount",Commande.panier.size());
        return "panier_quantite";
    }

}

