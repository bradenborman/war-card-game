package borman.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final Logger logger = LoggerFactory.getLogger(Deck.class);

    private static final List<PlayingCard> protoDeck = new ArrayList<PlayingCard>();

    // Initialize prototype deck
    static {
        for (PlayingCard.Suit suit : PlayingCard.Suit.values())
            for (PlayingCard.Rank rank : PlayingCard.Rank.values())
                protoDeck.add(new PlayingCard(rank, suit));

    }

    public static ArrayList<PlayingCard> newDeck() {
        return new ArrayList<PlayingCard>(protoDeck);
    }

    public static void orderDeckByValue(List<PlayingCard> cardsToOrder) {
        Collections.sort(cardsToOrder);
    }

    public static List<PlayingCard> newShuffledDeck() {
        List<PlayingCard> playingCards = new ArrayList<PlayingCard>(protoDeck);
        Collections.shuffle(playingCards);
        return playingCards;
    }

    public static void dealToPlayers(List<PlayingCard> deck, List<Player> players) {
        while (deck.size() > 0) {
            for (Player player : players) {
                if (deck.size() > 0)
                    player.dealCardToPlayer(deck.remove(0));
            }
        }

        players.forEach(Player::setBottomCard);

        logger.info("Dealing done. Each player has {} cards dealt to them", players.get(0).getCardPile().size());
    }
}