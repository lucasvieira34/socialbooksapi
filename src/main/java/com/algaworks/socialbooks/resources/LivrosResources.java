package com.algaworks.socialbooks.resources;

import com.algaworks.socialbooks.domain.Comentario;
import com.algaworks.socialbooks.domain.Livro;
import com.algaworks.socialbooks.repository.LivrosRepository;
import com.algaworks.socialbooks.services.LivrosService;
import com.algaworks.socialbooks.services.exceptions.LivroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;

    // BUSCAR LIVROS
    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livrosService.listar());
    }

    // SALVAR LIVRO
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid Livro livro) {
        livro = livrosService.salvar(livro);

        //Construir URI para consumo do recurso criado
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livro.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // BUSCAR LIVRO
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
        Livro livro = livrosService.buscar(id);

        CacheControl cacheControl = CacheControl.maxAge(10, TimeUnit.MINUTES);

        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
    }

    // DELETAR LIVRO
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        livrosService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //ATUALIZAR LIVRO
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
        livro.setId(id);
        livrosService.atualizar(livro);
        return ResponseEntity.noContent().build();
    }

    // SALVAR COMENTÁRIOS DE UM LIVRO
    @PostMapping(value = "/{id}/comentarios")
    public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
                                    @RequestBody Comentario comentario) {

        //PEGAR USUÁRIO AUTENTICADO
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comentario.setUsuario(auth.getName());

        livrosService.salvarComentario(livroId, comentario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(uri).build();
    }

    // BUSCAR COMENTÁRIOS DE UM LIVRO
    @GetMapping(value = "/{id}/comentarios")
    public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId) {

        List<Comentario> comentarios = livrosService.listarComentarios(livroId);

        return ResponseEntity.status(HttpStatus.OK).body(comentarios);
    }
}
