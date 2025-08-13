package com.vaxi.spring_boot_microservice_inmueble.repository;

import com.vaxi.spring_boot_microservice_inmueble.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;

// extends --> para designar la tarea de manejo de la data (CRUD) a esta clase
//             los parametros que le asignamos, son
//             Inmueble: Entidad sobre la cual va a implementar el repositorio 
//             Long: El tipo de datos del que el ID o primaryKey de esa entidad 
public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {

}
