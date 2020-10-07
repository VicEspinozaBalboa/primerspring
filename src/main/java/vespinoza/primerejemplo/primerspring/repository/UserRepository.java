package vespinoza.primerejemplo.primerspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vespinoza.primerejemplo.primerspring.entity.UserDemo;

@Repository
public interface UserRepository extends JpaRepository<UserDemo, Long>{
   UserDemo findByNombre (String nombre);
}
