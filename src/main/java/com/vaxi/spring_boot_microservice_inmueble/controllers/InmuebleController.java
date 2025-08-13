package com.vaxi.spring_boot_microservice_inmueble.controllers;

import com.vaxi.spring_boot_microservice_inmueble.service.InmuebleService;
import com.vaxi.spring_boot_microservice_inmueble.model.Inmueble;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController // indicamos que es una clase tipo controller
// indica que vamos a poder hacer uso de getMapping, post, delete, put ...
// ademas de ello, agregamos la ruta de acceso por defecto
@RequestMapping("api/inmueble")
public class InmuebleController {

    @Autowired
    private InmuebleService inmuebleService;

    // ResponseEntity --> objeto que contiene el httpStatus, httpPayload(data que se
    // va a
    // tranferir al cliente), <?> --> hacer alución a que es un tipo generico

    // @RequestBody --> la data del cliente, va a viajar por el body
    @PostMapping
    public ResponseEntity<?> saveInmueble(@RequestBody Inmueble inmueble) {
        // ReponseEntity recibe dos parametros
        // 1.- la data que se se crea
        // 2.- el status de la respuesta

        return new ResponseEntity<>(inmuebleService.saveInmueble(inmueble), HttpStatus.CREATED);
    }

    // @PathVariable --> la data el cliente, va a viajar por la url
    @DeleteMapping("{inmuebleId}")
    public ResponseEntity<?> deleteInmueble(@PathVariable Long inmuebleId) {
        inmuebleService.deleteInmueble(inmuebleId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getInmuebleList() {
        return ResponseEntity.ok(inmuebleService.getInmuebles());
    }

}
