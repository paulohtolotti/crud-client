package com.devsuperior.clientcrud.services;

import com.devsuperior.clientcrud.dto.ClientDto;
import com.devsuperior.clientcrud.entities.Client;
import com.devsuperior.clientcrud.repositories.ClientRepository;
import com.devsuperior.clientcrud.services.exceptions.ContentNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    /**
     * Insere um novo cliente no banco de dados
     * @param dto
     * @return
     *
     */
    @Transactional
    public ClientDto insert(ClientDto dto) {
        Client entity = new Client(dto);
        entity = repository.save(entity);
        return new ClientDto(entity);
    }

    /**
     * Atualiza os dados de um cliente no banco de dados
     * @param id    identificador único do cliente
     * @param dto   dados da requisição
     * @return      cliente com os dados atualizados
     * @throws ContentNotFoundException se o ID do cliente não existir
     */
    @Transactional
    public ClientDto update(Long id, ClientDto dto) throws ContentNotFoundException {
        // Recuperar a entidade pelo ID -> Copiar os dados da entidade para o DTO -> Salvar o DTO

        try {
            Client entity = repository.getReferenceById(id);

            copyParameters(dto, entity);

            entity = repository.save(entity);

            return new ClientDto(entity);
        } catch(EntityNotFoundException err) {
            throw new ContentNotFoundException("Cliente com id " + id + " não encontrado");
        }

    }

    /**
     * Deleta um cliente cadastrado
     * @param id    identificador único do cliente
     * @throws ContentNotFoundException se o id não existir
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) throws ContentNotFoundException {
        if(!repository.existsById(id)) throw new ContentNotFoundException("Impossível deletar: cliente não cadastrado.");
        repository.deleteById(id);
    }

    /**
     * Copia dados de uma dto para uma entidade
     * @param dto   objeto de transferência de dados
     * @param entity    entidade recuperada do banco de dados
     */
    private void copyParameters(ClientDto dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());
    }

}
