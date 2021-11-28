package borman.war;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void setBottomCardTest() {
        Player player = new Player("Test Player");
        player.setCardPile(Deck.newShuffledDeck());
        player.setBottomCard();
        Assertions.assertNotNull(player.getBottomCard());
    }
}