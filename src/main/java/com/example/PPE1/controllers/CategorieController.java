package com.example.PPE1.controllers;

import com.example.PPE1.DAO.*;
import com.example.PPE1.Services.CategorieService;
import com.example.PPE1.entities.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategorieController {

	@Autowired
	protected UserRepository ur;
	@Autowired
	protected CategorieRepository cr;
	@Autowired
	protected CategorieService cas;

	@GetMapping("/list")
	public String getCat(Model model) {
		model.addAttribute("categorie", cas.getAll());
		return "categories_list";
	}

	@GetMapping("/add")
	public String GetCatAdd(Model model) {
		model.addAttribute("categorie", new Categorie());
		return "categories_add";
	}

	@PostMapping("/add")
	public String PostCatAdd(@ModelAttribute("categorie") Categorie c) {
		cas.save(c);
		return "redirect:/admin/categories/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteCat(@PathVariable Long id) {
		cas.removeById(id);
		return "redirect:/admin/categories/list";
	}

	@GetMapping("/update/{id}")
	public String updateCat(@PathVariable Long id, Model model) {
		Optional<Categorie> c = cas.getById(id);
		if (c.isPresent()) {
			model.addAttribute("categorie", c.get());
			return "categories_add";
		}
		return "redirect:/admin/categories/list";
	}

}
