package OnlineGameStore.game;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/games")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames(){
        return gameService.getAllGames();
    }

    @GetMapping(path = "/")
    @ResponseBody
    public Game getGameByName(@RequestParam String name){
        return gameService.getGameByName(name);
    }

    @PostMapping(path = "/post")
    public Game addGame(@RequestBody Game game){
        return gameService.addGame(game);

    }
    @DeleteMapping(path = "/delete/{id}")
    public String deleteGame(@PathVariable Long id){
        return gameService.deleteGame(id);
    }

    @PutMapping(path = "/update/{id}")
    public Game updateGame(@RequestBody Game game, @PathVariable Long id){
        return gameService.updateGame(id, game);
    }
}
