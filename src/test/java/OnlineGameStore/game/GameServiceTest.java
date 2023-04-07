package OnlineGameStore.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    private GameService underTest;

    @BeforeEach
    void setUp() {
        underTest = new GameService(gameRepository);
    }

    @Test
    void itShouldGetAllGames() {
        //when
        underTest.getAllGames();
        //then
        verify(gameRepository).findAll();
    }

    @Test
    void itShouldGetGameByName() {
        //given
        String name="Gwent";
        Game game = new Game(0L, "Gwent", GameGenres.card_game,
                "Gwent", "CD Projekt RED", LocalDate.parse("2020-12-10"), 0.00);
        //when
        underTest.getGameByName(name);

        //then
        ArgumentCaptor<String> nameArgumentCaptor =
                ArgumentCaptor.forClass(String.class);
        verify(gameRepository).findGameByName(nameArgumentCaptor.capture());
        String capturedName = nameArgumentCaptor.getValue();

        assertThat(capturedName).isEqualTo(name);
    }


    @Test
    void itShouldAddGame() {
        //given
        Game game = new Game(0L, "Cyberpunk 2077", GameGenres.action,
                "Cyberpunk", "CD Projekt RED", LocalDate.parse("2020-12-10"), 50.00);
        //when
        underTest.addGame(game);

        //then
        ArgumentCaptor<Game> gameArgumentCaptor =
                ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).save(gameArgumentCaptor.capture());
        Game capturedGame = gameArgumentCaptor.getValue();

        assertThat(capturedGame).isEqualTo(game);
    }

    @Test
    void itShouldDeleteGame() {
        //given
        Long gameId=1L;

        //when
        underTest.deleteGame(gameId);

        //then
        ArgumentCaptor<Long> idArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(gameRepository).deleteById(idArgumentCaptor.capture());
        Long capturedId = idArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(gameId);
    }

    @Test
    void updateGame() {
        //given
        Long gameId=1L;
        Game game1 = new Game(gameId, "Cyberpunk 2077", GameGenres.action,
                "Cyberpunk", "CD Projekt RED", LocalDate.parse("2020-12-10"), 50.00);
        //when
        underTest.updateGame(gameId, game1);

        //then
        ArgumentCaptor<Long> idArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(gameRepository).findById(idArgumentCaptor.capture());
        Long capturedId = idArgumentCaptor.getValue();

        ArgumentCaptor<Game> gameArgumentCaptor =
                ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).save(gameArgumentCaptor.capture());
        Game capturedGame = gameArgumentCaptor.getValue();

        assertThat(capturedGame).isEqualTo(game1);
        assertThat(capturedId).isEqualTo(gameId);

    }
}