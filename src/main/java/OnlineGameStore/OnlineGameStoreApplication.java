package OnlineGameStore;

import OnlineGameStore.game.Game;
import OnlineGameStore.game.GameRepository;
import OnlineGameStore.security.user.User;
import OnlineGameStore.security.user.UserRepository;
import OnlineGameStore.security.user.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import OnlineGameStore.game.GameGenres;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class OnlineGameStoreApplication {


	public static void main(String[] args) {
		SpringApplication.run(OnlineGameStoreApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(GameRepository gameRepository, UserRepository userRepository){
		return args -> {
			Game game1 = new Game(0L, "Cyberpunk 2077", GameGenres.action,
					"Cyberpunk 2077 is an open-world, action-adventure RPG set in the megalopolis of Night City," +
							" where you play as a cyberpunk mercenary wrapped up in a do-or-die fight for survival.", "CD PROJEKT RED",
					LocalDate.parse("2020-12-10"), 50.00);

			Game game2 = new Game(2L, "Witcher 3", GameGenres.rpg,
					"The Witcher 3: Wild Hunt, the RPG epic with a mature, non-linear story that reacts to your decisions, a vast open world with a living ecosystem, " +
					"dynamic and tactical combat, and stunning visuals", "CD PROJEKT RED",
					LocalDate.parse("2015-05-18"), 20.99);

			Game game3 = new Game(0L, "Gwent", GameGenres.card_game,
					"Join in The Witcher universe’s favorite card game — available for free! Blending the CCG and TCG genres, " +
					"GWENT sees you clash in fast-paced online PvP duels that combine bluffing, on-the-fly decision making and careful deck construction",
					"CD PROJEKT RED", LocalDate.parse("2017-05-24"), 0.00);

			Game game4 = new Game(0L, "Witcher: Enchanced Edition", GameGenres.rpg, "Set in a dark fantasy world with a u" +
					"nique medieval, yet somewhat modern, feel to it, " + "The Witcher isn't just another fantasy RPG.", "CD PROJEKT RED",
					LocalDate.parse("2007-10-26"), 3.00);

			Game game5 = new Game(0L, "Witcher 2: Assasin of Kings", GameGenres.rpg, "The second installment in the RPG saga about the Witcher, Geralt of Rivia. A new, modern game engine, " +
					"responsible both for beautiful visuals and sophisticated game mechanics puts players in the most lively and believable world ever created in an RPG game.",
					"CD PROJEKT RED", LocalDate.parse("2012-04-17"), 5.99);

			Game game6 = new Game(0L, "Thronebreaker: The Witcher Tales", GameGenres.adventure,
					"Thronebreaker is a single player role-playing game set in the world of The Witcher that combines " +
							"narrative-driven exploration with unique puzzles and card battle mechanics.\n", "CD PROJEKT RED",
					LocalDate.parse("2019-05-24"), 18.99);

			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);
			User user  = new User(0L,"admin", new BCryptPasswordEncoder(11).encode("admin"), UserRole.ADMIN);
			userRepository.save(user);

		};
	}
}

