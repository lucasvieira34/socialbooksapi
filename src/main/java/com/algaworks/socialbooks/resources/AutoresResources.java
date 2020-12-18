package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Autor;
import com.algaworks.socialbooks.services.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresResources {

    @Autowired
    private AutoresService autoresService;

    // LISTAR AUTORES
    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = autoresService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(autores);
    }

    // SALVAR AUTOR
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid Autor autor) {
        autor = autoresService.salvar(autor);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autor.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // BUSCAR AUTOR
    @GetMapping(value = "/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable("id") Long id) {
        Autor autor = autoresService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(autor);
    }

}
