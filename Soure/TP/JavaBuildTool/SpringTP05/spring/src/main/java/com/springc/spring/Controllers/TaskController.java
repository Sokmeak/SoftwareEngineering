package com.springc.spring.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springc.spring.ProductEntity.Product;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/TP05")
public class TaskController {


    // use database instead.


    // this is for testing only 
    public static List<Product> products = new ArrayList<>(Arrays.asList(
        new Product("P001", "Laptop", "USA", 899.99, 750.00, "/images/laptop.jpeg", "High-performance laptop"),
        new Product("P002", "Smartphone", "China", 499.99, 400.00, "/images/smartphone.jpeg", "Latest smartphone model"),
        new Product("P003", "Headphones", "Germany", 79.99, 50.00, "/images/headphone.jpeg", "Noise-cancelling headphones")
    ));

    @GetMapping("task1")
    public String getModel(Model model){

        // create array of products

       
        model.addAttribute("products", products);
        return "task1";
    }

    @GetMapping("task1/add")
    public String getFormAdd(Model model) {
         model.addAttribute("product", new Product());
        return "formAdd";  
    }


    

        @PostMapping("task1/edit/{index}")
        public String updateProduct(@ModelAttribute Product pro ,@PathVariable int index) {
            System.out.println("Proudct "+index+" has updated.");

            // how can i get index?
            
            products.set(index, pro);
            
            return "redirect:/TP05/task1";
        }

        @GetMapping("task1/edit/{index}")
        public String getUpdateAdd(@PathVariable int index, Model model) {
        Product product = products.get(index); // Retrieve the product by index
        model.addAttribute("product", product);
        model.addAttribute("index", index);   // Pass the index to the template
        return "editForm";
    }
        
        
    @PostMapping("task1/add")
    public String addProduct(@ModelAttribute Product product) {
        System.out.println("\n\n\n\n------- Hello -------");

        System.out.println(product.toString());
       products.add(product);
       

    
        
      return "redirect:/TP05/task1";
    }
    



    // public void updateProduct(String productCode, Product updatedProduct) {
    //     Product existingProduct = productRepository.findByProductCode(productCode);
    //     if (existingProduct != null) {
    //         existingProduct.setProductName(updatedProduct.getProductName());
    //         existingProduct.setOriginCountry(updatedProduct.getOriginCountry());
    //         existingProduct.setPrice(updatedProduct.getPrice());
    //         existingProduct.setCost(updatedProduct.getCost());
    //         existingProduct.setImage(updatedProduct.getImage());
    //         existingProduct.setDescription(updatedProduct.getDescription());
    //         productRepository.save(existingProduct);  // Save the updated product
    //     }
    // }
    

  


    
}
