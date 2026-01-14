package com.devsuperior.clientcrud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
}
