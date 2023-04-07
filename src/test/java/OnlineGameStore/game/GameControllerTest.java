package OnlineGameStore.game;

import OnlineGameStore.security.jwt.JWTAuthenticationFilter;
import OnlineGameStore.security.jwt.JwtService;
import OnlineGameStore.security.user.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = GameController.class)
@AutoConfigureMockMvc(addFilters = false)
class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private GameRepository gameRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    void itShouldGetAllGames() throws Exception {
        //given
        Game game1 = new Game(1L, "Cyberpunk 2077", GameGenres.action, "Cyberpunk", "CD Projekt RED",
                LocalDate.parse("2020-12-10"), 50.00);
        Game game2 = new Game(2L, "Witcher3", GameGenres.rpg, "Witcher3", "CD Projekt RED",
                LocalDate.parse("2015-05-18"), 29.99);
        List<Game> games= List.of(game1,game2);
        Mockito.when(gameService.getAllGames()).thenReturn(games);
        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/games").characterEncoding(StandardCharsets.UTF_8);
        MvcResult result = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        String content  =result.getResponse().getContentAsString();
        //then
        List<Game> resultList = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(content, new TypeReference<List<Game>>() {});
        assertThat(games).isEqualTo(resultList);
    }

    @Test
    void itShouldGetGameByName() throws Exception {
        //given
        String name = "Cyberpunk 2077";
        Game game = new Game(1L, "Cyberpunk 2077", GameGenres.action, "Cyberpunk",
                "CD Projekt RED", LocalDate.parse("2020-12-10"), 50.00);

        Mockito.when(gameService.getGameByName(name)).thenReturn(game);
        //when
        RequestBuilder request = MockMvcRequestBuilders.get("/api/games/?name=Cyberpunk 2077").characterEncoding(StandardCharsets.UTF_8);
        MvcResult result = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        String content  =result.getResponse().getContentAsString();
        //then
        Game result1 = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(content, Game.class);
        assertThat(game).isEqualTo(result1);
    }

    @Test
    void itShouldAddGame() throws Exception {
        //given
        Game game = new Game(1L, "Cyberpunk 2077", GameGenres.action, "Cyberpunk",
                "CD Projekt RED", LocalDate.parse("2020-12-10"), 50.00);
        Mockito.when(gameService.addGame(any(Game.class))).thenReturn(game);

        RequestBuilder request = MockMvcRequestBuilders.post("/api/games/post").contentType(MediaType.APPLICATION_JSON).content(" {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"Witcher 2: Assasin of kings\",\n" +
                "        \"genre\": \"rpg\",\n" +
                "        \"description\": \"Witcher 2\",\n" +
                "        \"developer\": \"CD Projekt RED\",\n" +
                "        \"releaseDate\": \"2023-04-06\",\n" +
                "        \"dollarPrice\": 6.99\n" +
                "    }");
        MvcResult result = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        String content  =result.getResponse().getContentAsString();
        //then
        Game result1 = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(content, Game.class);
        assertThat(game).isEqualTo(result1);

    }

    @Test
    void deleteGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/games/delete/1");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void updateGame() throws Exception {
        //given

        Game updatedGame = new Game(1L, "Witcher 2: Assasin of kings", GameGenres.rpg, "Witcher 2",
                "CD Projekt RED", LocalDate.parse("2011-05-17"), 6.99);

        Mockito.when(gameService.updateGame(any(Long.class), any(Game.class))).thenReturn(updatedGame);

        RequestBuilder request = MockMvcRequestBuilders.put("/api/games/update/1").contentType(MediaType.APPLICATION_JSON).content(" {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"Witcher 2: Assasin of kings\",\n" +
                "        \"genre\": \"rpg\",\n" +
                "        \"description\": \"Witcher 2\",\n" +
                "        \"developer\": \"CD Projekt RED\",\n" +
                "        \"releaseDate\": \"2011-05-17\",\n" +
                "        \"dollarPrice\": 6.99\n" +
                "    }");
        MvcResult result = mvc.perform(request).andDo(print()).andExpect(status().isOk()).andReturn();
        String content  =result.getResponse().getContentAsString();
        //then
        Game result1 = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(content, Game.class);
        assertThat(result1).isEqualTo(updatedGame);
    }
}