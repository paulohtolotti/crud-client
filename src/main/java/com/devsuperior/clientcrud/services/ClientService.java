package com.devsuperior.clientcrud.services;

import com.devsuperior.clientcrud.dto.ClientDto;
import com.devsuperior.clientcrud.entities.Client;
import com.devsuperior.clientcrud.repositories.ClientRepository;
import com.devsuperior.clientcrud.services.exceptions.ContentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public ClientDto findById(Long id) throws ContentNotFoundException {
        Client entity = repository.findById(id).orElseThrow(
                () -> new ContentNotFoundException("Id " + id + " não encontrado")
        );
        return new ClientDto(entity);
    }

    /**
     * Busca paginada de clientes
     * @param pageable  objeto para realizar a consulta paginada
     * @return  páginas prontas para transferência para o cliente
     */
    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> clientPage = repository.findAll(pageable);
        return clientPage.map(c -> new ClientDto(c));
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {

        Client entity = new Client(dto);
        entity = repository.save(entity);
        copyEntityToDto(dto, entity);
        return dto;

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
