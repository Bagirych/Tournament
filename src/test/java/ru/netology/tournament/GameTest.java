package ru.netology.tournament;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotRegisteredException;
import ru.netology.domain.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game = new Game();
    private Player player1 = new Player(1, "Vader", 1);
    private Player player2 = new Player(2, "Potter", 2);
    private Player player3 = new Player(3, "Shredder", 3);
    private Player player4 = new Player(4, "Ash", 3);


    @BeforeEach
    void setUp() {
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
    }

    @Test
    void shouldFindRegisteredPlayers() {
        Assertions.assertEquals(List.of(player1,player2,player3,player4), game.findAll());
    }

    @Test
    void shouldFindByName() {
        Assertions.assertEquals(player3, game.findByName("Shredder"));
    }

    @Test
    void shouldNotFindByName() {
        Assertions.assertEquals(null, game.findByName("Luke"));
    }

    @Test
    void shouldWinTheFirstOne() {
        int actual = game.round("Ash", "Potter");
        Assertions.assertEquals(1, actual);
    }


    @Test
    void shouldWinTheSecondOne() {
        int actual = game.round("Vader", "Shredder");
        Assertions.assertEquals(2, actual);
    }

    @Test
    void shouldBeDraw() {
        int actual = game.round("Shredder", "Ash");
        Assertions.assertEquals(0, actual);
    }

    @Test
    void shouldThrowExceptionWhenPlayer1Unreg() {
        Assertions.assertThrows(NotRegisteredException.class,
                () -> game.round("Leia", "Ash"));
    }

    @Test
    void shouldThrowExceptionWhen2PlayersUnreg() {
        Assertions.assertThrows(NotRegisteredException.class,
                () -> game.round("Luke", "Chewie"));
    }

    @Test
    void shouldThrowExceptionWhenPlayer2Unreg() {
        Assertions.assertThrows(NotRegisteredException.class,
                () -> game.round("Potter", "Han"));
    }

}