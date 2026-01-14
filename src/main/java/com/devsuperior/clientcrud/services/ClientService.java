package com.devsuperior.clientcrud.services;

import com.devsuperior.clientcrud.dto.ClientDto;
import com.devsuperior.clientcrud.entities.Client;
import com.devsuperior.clientcrud.repositories.ClientRepository;
import com.devsuperior.clientcrud.services.exceptions.ContentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    // INJEÇÃO DE DEPENDÊNCIA VIA CONSTRUTOR
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * Busca um cliente no banco de dados
     * @param id identificador único do cliente
     * @return  dados do cliente no formato adequado para trafegar na rede
     * @throws ContentNotFoundException se o id não existir
     */
    public ClientDto findById(Long id) throws ContentNotFoundException {
        Client entity = repository.findById(id).orElseThrow(
                () -> new ContentNotFoundException("Id " + id + " não encontrado")
        );
        return new ClientDto(entity);
    }

    /**
     * Copia dados de uma entidade para um dto
     * @param dto   objeto de transferência de dados
     * @param entity    entidade recuperada do banco de dados
     */
    private void copyEntityToDto(ClientDto dto, Client entity) {
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCpf(entity.getCpf());
        dto.setIncome(entity.getIncome());
        dto.setChildren(entity.getChildren());
    }

}
