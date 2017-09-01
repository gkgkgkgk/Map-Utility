package com.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

	@GetMapping("/")
	public String redirect(Model model) {
		return "redirect:/home";
	}
	
	
	@GetMapping("/home")
	public String getHome(Model model) {
		model.addAttribute("home", new Entity());
		return "home";
	}

	@GetMapping("/maps")
	public String getMappings(Model model) {
		model.addAttribute("maps", new Entity());
		return "maps";
	}
	
	@GetMapping("/{floorname}")
	public String getFloorplan(@PathVariable String floorname, Model model) {
		model.addAttribute("floorplan", new Entity());
		return floorname;
	}
}
