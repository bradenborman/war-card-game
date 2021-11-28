package borman.war;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void orderDeckByValueTest() {

        List<PlayingCard> playingCards = Deck.newShuffledDeck();
        System.out.println("Shuffled Order:");
        playingCards.forEach(x -> System.out.println(x.toString()));

        System.out.println("\n\nSorted Order:");
        Deck.orderDeckByValue(playingCards);
        playingCards.forEach(x -> System.out.println(x.toString()));

        assertFalse(playingCards.isEmpty());
    }
}