package vespinoza.primerejemplo.primerspring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserDemo {
    @Id
    private Long id;
    private String nombre;
    private String clave;
}
