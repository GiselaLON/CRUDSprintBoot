package com.adso.projet_class_1.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="registro")

public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;	
    private String correo;
    private String contrasena;
    private String  descripcion;

}

