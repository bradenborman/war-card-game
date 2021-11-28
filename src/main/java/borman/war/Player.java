package borman.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final Logger logger = LoggerFactory.getLogger(Player.class);

    private List<PlayingCard> cardPile = new ArrayList<>();
    private PlayingCard bottomCard;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public List<PlayingCard> getCardPile() {
        return cardPile;
    }

    public void setCardPile(List<PlayingCard> cardPile) {
        this.cardPile = cardPile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void dealCardToPlayer(PlayingCard cardToAdd) {
        cardPile.add(cardToAdd);
    }

    public void setBottomCard() {
        if (!cardPile.isEmpty())
            bottomCard = cardPile.get(cardPile.size() - 1);
    }

    public PlayingCard getBottomCard() {
        return bottomCard;
    }

    public PlayingCard topCardInStack() {
        if (!cardPile.isEmpty()) {
            PlayingCard topCard = cardPile.remove(0);
            if (topCard.equals(bottomCard)) {
                logger.info("{} has reached end of deck. Shuffling after getting last card.", name);
                Collections.shuffle(cardPile);
                setBottomCard();
            }
            return topCard;
        } else
            throw new RuntimeException("Game Over.");
    }

    public void collectWinnings(PlayingCard playingCard) {
        cardPile.add(cardPile.size(), playingCard);
    }
}