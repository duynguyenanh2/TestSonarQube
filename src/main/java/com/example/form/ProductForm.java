package com.example.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.validator.NotExistProductName;

public class ProductForm {
	
	public Long id;
	
	@NotExistProductName(message = "Name is exist")
	@NotBlank(message = "Please enter name")
	@Size(min=5, max=30, message = "Length name from 5 to 30")
	public String name;
	
	@NotBlank(message = "Please enter brand")
	public String brand;
	
	@NotNull
	public String madein;
	
	@Min(10)
	public float price;

	public ProductForm() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMadein() {
		return madein;
	}

	public void setMadein(String madein) {
		this.madein = madein;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
