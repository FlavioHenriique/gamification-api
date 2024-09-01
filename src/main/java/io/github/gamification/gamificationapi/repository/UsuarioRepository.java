package io.github.gamification.gamificationapi.repository;

import io.github.gamification.gamificationapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsuario(String usuario);

    @Query("select u from Usuario u order by u.pontuacao desc")
    List<Usuario> findAllOrderByPontuacaoDesc();
}
