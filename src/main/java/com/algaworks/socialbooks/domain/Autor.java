package com.algaworks.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode estar vazio.")
    private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Campo nascimento é de preenchimento obrigatório.")
    private Date nascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Campo nacionalidade é de preenchimento obrigatório.")
    private String nacionalidade;


    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Livro> livros;

}
