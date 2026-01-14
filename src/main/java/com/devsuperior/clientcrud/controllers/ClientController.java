package com.devsuperior.clientcrud.controllers;

import com.devsuperior.clientcrud.dto.ClientDto;
import com.devsuperior.clientcrud.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    /**
     * Busca paginada todos os clientes cadastrados
     * @param pageable
     * @return
     */
    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
        Page<ClientDto> page = service.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    /**
     * Insere um novo cliente
     * @param dto
     * @return dados do cliente inserido, juntamente com o registro único gerado pelo BD
     */
    @PostMapping
    public ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    /**
     * Atualiza os dados de um cliente já existente
     * @param id    identificador único de um cliente já registrado
     * @param dto   novos dados do cliente
     * @return      dados do cliente atualizados
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id , @RequestBody @Valid ClientDto dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
