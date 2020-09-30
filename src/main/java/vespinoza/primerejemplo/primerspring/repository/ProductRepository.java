package vespinoza.primerejemplo.primerspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vespinoza.primerejemplo.primerspring.entity.Category;
import vespinoza.primerejemplo.primerspring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    public List<Product> findByCategory(Category category);

    //AQUI
    public List<Product> findByName(String name);
}
