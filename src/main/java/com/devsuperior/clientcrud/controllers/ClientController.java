package com.devsuperior.clientcrud.controllers;

import com.devsuperior.clientcrud.dto.ClientDto;
import com.devsuperior.clientcrud.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    // INJEÇÃO DE DEPENDÊNCIA VIA CONSTRUTOR
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    /**
     *  Busca um cliente pelo seu identificador único
     * @param id identificador do cliente
     * @return  cliente encontrado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
}
