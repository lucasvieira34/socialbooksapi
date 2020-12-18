package com.algaworks.socialbooks.repository;

import com.algaworks.socialbooks.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentario, Long> {
}
