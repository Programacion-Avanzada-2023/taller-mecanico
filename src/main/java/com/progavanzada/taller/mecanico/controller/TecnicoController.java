package com.progavanzada.taller.mecanico.controller;

import org.springframework.web.bind.annotation.RestController;
import com.progavanzada.taller.mecanico.controller.dto.TecnicoDto;
import com.progavanzada.taller.mecanico.services.TecnicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping
    public List<TecnicoDto> getTecnicos() {
        return this.service.buscarTodos();
    }

}
