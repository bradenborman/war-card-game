package borman.war;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class WarSimulatorTest {

    @Test
    @DisplayName("Run a game of war")
    void runSimulation() {
        WarSimulator warSimulator = new WarSimulator();
        warSimulator.run();
    }

    @Test
    void checkWinnerTest() {
        List<Player> playerList = Collections.singletonList(new Player("Test Player"));
        WarSimulator simulator = new WarSimulator();

        Assertions.assertTrue(
                simulator.checkWinner(playerList)
        );
    }

    @Test
    void checkWinnerFalseTest() {
        Player player1 = new Player("Test Player");
        player1.setCardPile(Deck.newDeck());
        List<Player> playerList = Collections.singletonList(player1);
        WarSimulator simulator = new WarSimulator();

        Assertions.assertFalse(
                simulator.checkWinner(playerList)
        );
    }
}