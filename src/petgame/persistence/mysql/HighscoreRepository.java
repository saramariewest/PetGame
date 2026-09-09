package petgame.persistence.mysql;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighscoreRepository extends JpaRepository<HighscoreEntity, Long> {
    List<HighscoreEntity> findTop10ByOrderByScoreDescLevelDescSurvivalTimeMillisDesc();
}
