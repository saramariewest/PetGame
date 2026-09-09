package petgame.persistence.mysql;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaveGameRepository extends JpaRepository<SaveGameEntity, String> {
    List<SaveGameEntity> findAllByOrderBySavedAtDesc();
}
