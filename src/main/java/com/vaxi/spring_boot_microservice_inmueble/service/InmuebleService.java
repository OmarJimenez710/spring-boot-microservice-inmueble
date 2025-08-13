
package com.vaxi.spring_boot_microservice_inmueble.service;

import com.vaxi.spring_boot_microservice_inmueble.model.Inmueble;
import java.util.List;

public interface InmuebleService {
    public Inmueble saveInmueble(Inmueble inmueble);

    public void deleteInmueble(Long inmuebleId);

    public List<Inmueble> getInmuebles();

}
