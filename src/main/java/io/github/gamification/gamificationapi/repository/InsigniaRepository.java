package io.github.gamification.gamificationapi.repository;

import io.github.gamification.gamificationapi.model.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsigniaRepository extends JpaRepository<Insignia, Long> {

    @Query(
            value = "SELECT count(*) from usuario_insignias_conquistadas where insignias_conquistadas_id  = ?1",
            nativeQuery = true
    )
    int contaPercentualUsuarios(long id);
}
