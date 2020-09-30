package vespinoza.primerejemplo.primerspring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import vespinoza.primerejemplo.primerspring.entity.Category;
import vespinoza.primerejemplo.primerspring.entity.Product;
import vespinoza.primerejemplo.primerspring.service.ProductService;

@RestController
@RequestMapping(value = "api/products")
public class ProductController {
    
    @Autowired
    private ProductService service;

    
    /*@GetMapping
    public ResponseEntity<List<Product>> get() {
        List<Product> list = service.listAllProducts();
        if (list.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }*/

  // @GetMapping(value="/category/{id}")
   @GetMapping
   public ResponseEntity<List<Product>>listProduct(@RequestParam(name = "CategoryId", required = false) Long categoryId){
       List<Product> products = new ArrayList<>();
        if (null ==  categoryId){
             products = service.listAllProducts();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = service.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        Product product = service.getProduct(id);
        if (null == product){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        service.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        var resultado = service.updateProduct(product);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDelete = service.deleteProduct(id);
        if (null == productDelete){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productDelete);
    }

    //AQUI
    @GetMapping(value = "/nameProduct")
    public ResponseEntity<List<Product>>getProductByName(@RequestParam(name = "nameProduct", required = true) String nameProduct){
        List<Product> products = new ArrayList<>();
        products = service.findByProductName(nameProduct);
        if (null == products){
            return ResponseEntity.notFound().build();
        }
         return ResponseEntity.ok(products);
     }

     @GetMapping(value = "/{id}/stock")
     public ResponseEntity<Product>UpdateStockProduct(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity){
        Product product = service.updateStock(id, quantity);
        if (null == product){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(product);
     }

     private String formatMessage(BindingResult result){
        List<Map<String, String>> errors = result.getFieldErrors().stream()
            .map(err -> {
                Map<String, String> error = new HashMap<>();
                error.put(err.getField(), err.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());

        
        ErrorMessage errorMessage = ErrorMessage.builder()
            .code("01")
            .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
     }
}
