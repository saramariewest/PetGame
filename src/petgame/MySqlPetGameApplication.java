package petgame;

import javax.swing.SwingUtilities;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import petgame.application.PetGame;
import petgame.persistence.GameDataStore;
import petgame.ui.UiTheme;

@SpringBootApplication
public class MySqlPetGameApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MySqlPetGameApplication.class)
                .headless(false)
                .run(args);
    }

    @Bean
    PetGame petGame(GameDataStore gameDataStore) {
        return new PetGame(gameDataStore);
    }

    @Bean
    ApplicationRunner launchGame(PetGame petGame) {
        return args -> SwingUtilities.invokeLater(() -> {
            UiTheme.install();
            petGame.start();
        });
    }
}
