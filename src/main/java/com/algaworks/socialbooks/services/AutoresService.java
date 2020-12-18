package com.algaworks.socialbooks.services;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.AutoresRepository;
import com.algaworks.socialbooks.services.exceptions.AutorExistenteException;
import com.algaworks.socialbooks.services.exceptions.AutorNaoEncontradoException;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    // LISTAR AUTORES
    public List<Autor> listar() {
        return autoresRepository.findAll();
    }

    // SALVAR AUTOR
    public Autor salvar(Autor autor) {
        if(autor.getId() != null) {

            if(autoresRepository.findById(autor.getId()).isPresent()){
                throw new AutorExistenteException("O autor já existe.");
            }
        }
        return autoresRepository.save(autor);
    }

    public Autor buscar(Long id) {
        if (!autoresRepository.findById(id).isPresent()) {
            throw new AutorNaoEncontradoException("O autor não foi encontrado.");
        }
        Autor autor = autoresRepository.findById(id).get();
        return autor;
    }

}
