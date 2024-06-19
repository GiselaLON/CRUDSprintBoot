package com.adso.projet_class_1.service;

import com.adso.projet_class_1.model.Registro;
import org.springframework.beans.factory.annotation.Autowired;
import com.adso.projet_class_1.repositoty.ReposytoryRegistro;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ServiceRegistro {
    @Autowired
    private ReposytoryRegistro reposytoryRegistro;

    /* Recuperar todos los Registros*/
    public List<Registro> getAllRegistros() {
        return reposytoryRegistro.findAll();
    }

    // Encontrar un Registro por ID
    public Optional<Registro> getRegistro(Long id) {
        return reposytoryRegistro.findById(id);
    }

    // Guardar o actualizar
    public void saveOrUpdate(Registro registro){
        reposytoryRegistro.save(registro);

    }

    // Elimina un registro por su ID
    public void deleteRegistro(Long id) {
        reposytoryRegistro.deleteById(id);
    }
}

