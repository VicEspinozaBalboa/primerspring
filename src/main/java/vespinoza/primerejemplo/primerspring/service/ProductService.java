package vespinoza.primerejemplo.primerspring.service;

import java.util.Date;
//import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import vespinoza.primerejemplo.primerspring.entity.Category;
import vespinoza.primerejemplo.primerspring.entity.Product;
import vespinoza.primerejemplo.primerspring.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAllProducts(){
        return repository.findAll();
    }

    public Product getProduct(Long id) {
        Product product = repository.findById(id).orElse(null);
        return product;
    }

    public Product createProduct (Product product){
        product.setCreateAt(new Date());
        product.setStatus("CREATED");
        return repository.save(product);
    }

    /*private List<String> validate(Product product) throws Exception {
        List<String> errores = new LinkedList<>();
        if (product.getDescription().length() <= 0) {
            errores.add("La descripcion es incorrecta");
        }
        return errores;
    }*/

    public Product updateProduct(Product product) {
        Product productDb = getProduct(product.getId());
        if (productDb == null) {
            return null;
        }

        productDb.setCategory(product.getCategory());
        productDb.setDescription(product.getDescription());
        productDb.setName(product.getName());
        productDb.setPrice(product.getPrice());

        return repository.save(productDb);
    }


     public Product deleteProduct(Long id) {
        Product productDb = getProduct(id);
        if (productDb == null) {
            return null;
        }

        productDb.setStatus("DELETED");
        return repository.save(productDb);
    }

    public List<Product> findByCategory(Category category){
        return repository.findByCategory(category);
    }

    //AQUI
    public List<Product> findByProductName(String name){
        return repository.findByName(name);         
    }

    public Product updateStock(Long id, Double quantity){
        Product productDb = getProduct(id);
        if (productDb == null) {
            return null;
        }

        Double stock = productDb.getStock() + quantity;
        productDb.setStock(stock);
        return repository.save(productDb);
    }
    
}
