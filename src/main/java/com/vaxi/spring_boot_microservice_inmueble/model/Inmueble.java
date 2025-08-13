package com.vaxi.spring_boot_microservice_inmueble.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jakarta.persistence.Id;
import lombok.Data;

@Data // lombok --> genera getters y setters
@Entity // springBoot --> detectar esta clase como entidad
@Table(name = "inmueble") // jakarta --> saber a que tabla de la base de datos, vamos a hacer referencia
public class Inmueble {

    // configuración para que este valor se genere automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // configuración para saber que es un id (primary key)
    @Id
    private Long id;

    // configuración del nombre de la columna, longuitud y si puede o no ser null,
    // dentro de la base de datos
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "address", length = 500, nullable = false)
    private String address;

    @Column(name = "picture", length = 1200, nullable = true)
    private String picture;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "createDate", nullable = false)
    private LocalDateTime createDate;
}
