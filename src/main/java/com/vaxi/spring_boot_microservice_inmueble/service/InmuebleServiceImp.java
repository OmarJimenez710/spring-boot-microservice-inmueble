package com.vaxi.spring_boot_microservice_inmueble.service;

import java.time.LocalDateTime;
import java.util.List;

import com.vaxi.spring_boot_microservice_inmueble.repository.InmuebleRepository;
import com.vaxi.spring_boot_microservice_inmueble.model.Inmueble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // indicamos que la clase es un Service (reglas de negocio)
public class InmuebleServiceImp implements InmuebleService {

    // generamos una inyección de dependencias, es decir, y no es
    // necesario instanciar la clase, con @Autowired, ya podemos
    // hacer uso de la dependencia como tal
    @Autowired
    private InmuebleRepository inmuebleRepository;

    @Override
    public Inmueble saveInmueble(Inmueble inmueble) {
        // agregamos la fecha de hoy al campo "createDate"
        inmueble.setCreateDate(LocalDateTime.now());

        // crear un nuevo registro, con base a lo que llega de parametro
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public void deleteInmueble(Long inmuebleId) {
        // elimina un registro, con base en el ID que le llega de parametro
        inmuebleRepository.deleteById(inmuebleId);
    }

    @Override
    public List<Inmueble> getInmuebles() {
        // regresa todos los registros que estan en base de datos
        return inmuebleRepository.findAll();
    }

}
