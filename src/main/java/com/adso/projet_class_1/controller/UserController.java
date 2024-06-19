package com.adso.projet_class_1.controller;
import com.adso.projet_class_1.model.Registro;
import com.adso.projet_class_1.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired

    private ServiceRegistro serviceRegistro;

    @GetMapping({"/","/index"})
    public String home() {
        return "pages/index"; // Asegúrate de tener una plantilla llamada "index"
    }

    @GetMapping("/register/new") //
    public String FormRegister(Model model){
        model.addAttribute("registro", new Registro());
        return "pages/registro";
    }

    @PostMapping("/registro")
    public String CreateUser(@ModelAttribute Registro registro){
        serviceRegistro.saveOrUpdate(registro);
        return "redirect:/registros";
    }
    @GetMapping("/{registroId}")
    public Optional<Registro> getById(@PathVariable("registroId")Long registroId) {
        return serviceRegistro.getRegistro(registroId);
    }
    @GetMapping("/registros")
    public String ListRegister(Model model){
        model.addAttribute("Datos", serviceRegistro.getAllRegistros());
        return "pages/dataSave";
    }
    //Eliminar
    @GetMapping("/registros/delete/{registroId}")
    public String deleteRegistro(@PathVariable("registroId") Long registroId) {
        serviceRegistro.deleteRegistro(registroId);
        return "redirect:/registros";

    }


    @GetMapping("/registros/edit/{id}")
    public String editRegistro(@PathVariable Long id, Model model) {
        Registro registro = serviceRegistro.getRegistro(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de registro inválido: " + id));
        model.addAttribute("registro", registro);
        return "fragments/editRegistro"; // Asegúrate de tener una plantilla llamada "editRegistro"
    }

    @PostMapping("/registros/update/{id}")
    public String updateRegistro(@PathVariable Long id, @ModelAttribute Registro registro) {
        registro.setId(id);
        serviceRegistro.saveOrUpdate(registro);
        return "redirect:/registros"; // Redirige a la lista de registros después de actualizar
    }
    @GetMapping("/search")
    public String searchRegistroById(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            Optional<Registro> registro = serviceRegistro.getRegistro(id);
            if (registro.isPresent()) {
                model.addAttribute("Datos", Collections.singletonList(registro.get()));
            } else {
                model.addAttribute("Datos", serviceRegistro.getAllRegistros());
                model.addAttribute("Mensaje", "No se encontró ningún registro con el ID proporcionado.");
            }
        } else {
            model.addAttribute("Datos", serviceRegistro.getAllRegistros());
        }
        return "pages/dataSave";
    }

}

