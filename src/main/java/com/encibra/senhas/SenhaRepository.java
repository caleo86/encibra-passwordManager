package com.encibra.senhas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {
    List<Senha> findAllByUsuarioId(Long idUsuario);

    @Query("SELECT COUNT(*) FROM Senha s WHERE s.usuario.id = :idUsuario")
    Long findCountByUsuarioId(Long idUsuario);
}
