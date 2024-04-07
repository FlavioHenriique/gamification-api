package io.github.gamification.gamificationapi.repository;

import io.github.gamification.gamificationapi.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    @Query(value = "SELECT * from RESPOSTA where usuario_Id = :idUsuario order by momento limit 3",
            nativeQuery = true)
    public List<Resposta> findFirst3(long idUsuario);

    @Query(value = "SELECT * from RESPOSTA where usuario_Id = :idUsuario",
            nativeQuery = true)
    List<Resposta> findAllByIdUsuario(long idUsuario);
}
