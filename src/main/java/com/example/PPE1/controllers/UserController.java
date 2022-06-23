package com.example.PPE1.controllers;

import com.example.PPE1.DAO.*;
import com.example.PPE1.Services.UserService;
import com.example.PPE1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	protected UserRepository ur;
	@Autowired
	protected CategorieRepository cr;

	@Autowired
	protected UserService us;
	
	Pattern BCRYPT_PATTERN = Pattern.compile("^\\$2[aby]?\\$\\d{1,2}\\$[.\\/A-Za-z0-9]{53}$");

	
	@GetMapping("/list")
	public String getClient(Model model) {
		model.addAttribute("user", us.getAll());
		return "users_list";
	}

	@GetMapping("/add")
	public String GetCAdd(Model model) {
		model.addAttribute("user", new User());
		return "users_add";
	}

	@PostMapping("/add")
	public String PostCatAdd(@ModelAttribute("user") User u) {
		u.setPassword(new BCryptPasswordEncoder(12).encode(u.getPassword()));
		u.setActive(true);
		u.setRoles("ROLE_USER");
		us.save(u);
		return "redirect:/admin/users/list";
	}

	@GetMapping("/delete/{id}")
	public String delete_users(@PathVariable Long id) {
		us.removeById(id);
		return "redirect:/admin/users/list";
	}

	@GetMapping("/update/{id}")
	public String update_users(@PathVariable Long id, Model model) {
		Optional<User> user = us.getById(id);
		if (user.isPresent()) {
			if (!BCRYPT_PATTERN.matcher(user.get().getPassword()).matches()) {
				user.get().setPassword(new BCryptPasswordEncoder(12).encode(user.get().getPassword()));
			}
			user.get().setRoles("ROLE_USER");
			model.addAttribute("user", user);
			return "users_add";
		}
		return "redirect:/admin/users/list";
	}

}
