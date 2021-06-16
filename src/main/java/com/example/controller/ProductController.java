package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.entity.Product;
import com.example.form.ProductForm;
import com.example.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@RequestMapping("/")
	public String viewProduct(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		if(!model.containsAttribute("product")) {
			Product product = new Product();
			model.addAttribute("product", product);
		}
		return "new_product";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") @Valid ProductForm product, BindingResult result,
			RedirectAttributes redAtr)  {
		if(result.hasErrors()) {
			redAtr.addFlashAttribute("errors",getError(result) );
			redAtr.addFlashAttribute("product", product);
			return "redirect:/new";
		}
		Product pd = new Product();
		pd.setId(product.getId());
		pd.setName(product.getName());
		pd.setBrand(product.getBrand());
		pd.setMadein(product.getMadein());
		pd.setPrice(product.getPrice());
		service.save(pd);

		return "redirect:/";
	}
	

	private Map<String, String> getError(BindingResult redAtr) {
		Map<String, String> errorMap = new HashMap<String, String>();
		for(ObjectError object : redAtr.getAllErrors()) {
			if(object instanceof FieldError) {
				FieldError fieldError= (FieldError) object;
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		return errorMap;
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id, Model model) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);

		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/";
	}
}
