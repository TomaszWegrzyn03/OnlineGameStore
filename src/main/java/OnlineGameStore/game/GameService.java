package OnlineGameStore.game;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    private  final GameRepository gameRepository;
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }

    public Game addGame(Game game){
        return gameRepository.save(game);

    }

    public Game getGameByName(String name) {
        return gameRepository.findGameByName(name);
    }

    public String deleteGame(Long id) {
        gameRepository.deleteById(id);
        return "Game with id: " + id + " has been deleted.";
        }


    public Game updateGame(Long id, Game newGame) {
        return gameRepository.findById(id)
                .map(game -> {
                    game.setId(game.getId());
                    game.setName(newGame.getName());
                    game.setDescription(newGame.getDescription());
                    game.setGenre(newGame.getGenre());
                    game.setDeveloper(newGame.getDeveloper());
                    game.setDollarPrice(newGame.getDollarPrice());
                    game.setReleaseDate(newGame.getReleaseDate());
                    return gameRepository.save(game);
                })
                .orElseGet(()->{
                    newGame.setId(id);
                    return gameRepository.save(newGame);
                });
    }
}
