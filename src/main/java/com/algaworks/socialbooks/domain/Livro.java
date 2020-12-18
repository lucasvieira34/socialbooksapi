package com.algaworks.socialbooks.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

@Setter @Getter @NoArgsConstructor
@Entity
public class Livro {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo nome não pode estar vazio.")
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Campo publicacao é de preenchimento obrigatório.")
    private Date publicacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull(message = "Campo editora é de preenchimento obrigatório.")
    private String editora;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotEmpty(message = "O resumo deve ser preenchido.")
    @Size(max = 1500, message = "O resumo não pode conter mais de 1500 caracteres.")
    private String resumo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @OneToMany(mappedBy = "livro")
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "AUTOR_ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Autor autor;

    public Livro(String nome){
        this.nome = nome;
    }
}
