package com.example.PPE1.controllers;

import com.example.PPE1.Services.CategorieService;
import com.example.PPE1.Services.DealService;
import com.example.PPE1.entities.Deal;
import com.example.PPE1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/deals")
public class DealsController {

	@Autowired
	protected DealService ds;
	@Autowired
	protected CategorieService cas;

	private String relative_img_path = "\\deals-images\\";
	private String relative_img_url = "/deals-images/";

	@GetMapping("/list")
	public String Deal(Model model) {
		model.addAttribute("deals", ds.getAll());
		return "deals_list";
	}

	@GetMapping("/add")
	public String DealAddGet(Model model) {
		model.addAttribute("deal", new Deal());
		model.addAttribute("categorie", cas.getAll());
		return "deals_add";
	}

	@PostMapping("/add")
	public String productAddPost(Deal deal,
            @RequestParam("img") MultipartFile multipartFile) throws IOException {

		if (multipartFile.getOriginalFilename() != "") {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			deal.setImage(this.relative_img_url + fileName);
			Utils.saveFile(Utils.get_images_path() + this.relative_img_path, fileName, multipartFile);
		} else {
			Deal d = ds.getDealById(deal.getId()).get();
			deal.setImage(d.getImage());
		}
		ds.save(deal);
		return "redirect:/admin/deals/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteDeal(@PathVariable long id) {
		ds.removeById(id);
		return "redirect:/admin/deals/list";
	}

	@GetMapping("/update/{id}")
	public String updateDealGet(@PathVariable long id, Model model) {
		Optional<Deal> d = ds.getDealById(id);
		if (d.isPresent()) {
			model.addAttribute("categorie", cas.getAll());
			model.addAttribute("deal", d.get());
			return "deals_add";
		}
		return "redirect:/admin/deals/list";
	}

	@GetMapping("/view/{id}")
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("deals", ds.getDealById(id).get());
		return "deals_view";
	}

}
