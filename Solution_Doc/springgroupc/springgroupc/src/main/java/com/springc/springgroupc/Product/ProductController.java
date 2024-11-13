package com.springc.springgroupc.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("")
    public String getProducts(Model model) {
        List<ProductEntity> productEntities = productRepository.findAll();
        model.addAttribute("formProduct", new ProductEntity());
        model.addAttribute("products", productEntities);
        model.addAttribute("title", "Welcome to Product Page");
        return "products/layout";
    }
    @PostMapping("save")
    public String postProduct(
        @RequestParam("name") String name,
         @RequestParam("description") String description,
         @RequestParam("price") double price, 
         @RequestParam("image") MultipartFile image,
         @RequestParam("code") String code,
         @RequestParam("country") String country
         ) {
        
        return "";
    }
    @PostMapping("saveProduct")
    public String postProduct2(@ModelAttribute("formProduct") ProductEntity prodiucEntity) {
        productRepository.save(prodiucEntity);
        
        return "redirect:/products";
    }
    
    
    
}
