package com.devsuperior.clientcrud.dto;

import com.devsuperior.clientcrud.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class ClientDto {

    private Long id;
    @NotBlank(message = "Não é possível adicionar um valor vazio")
    private String name;
    private String cpf;
    @PositiveOrZero(message = "Renda não pode ser um valor negativo")
    private Double income;
    @PastOrPresent(message = "Não é permitido adicionar uma data futura")
    private LocalDate birthDate;
    @PositiveOrZero(message = "Número de filhos não pode ser um valor negativo")
    private Integer children;

    public ClientDto() {

    }

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setCpf(entity.getCpf());
        this.setIncome(entity.getIncome());
        this.setChildren(entity.getChildren());
        this.setBirthDate(entity.getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotBlank(message = "Não é possível adicionar um valor vazio") String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIncome(@PositiveOrZero(message = "Renda não pode ser um valor negativo") Double income) {
        this.income = income;
    }

    public void setBirthDate(@PastOrPresent(message = "Não é permitido adicionar uma data futura") LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setChildren(@PositiveOrZero(message = "Número de filhos não pode ser um valor negativo") Integer children) {
        this.children = children;
    }
}
