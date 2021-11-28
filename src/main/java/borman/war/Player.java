package borman.war;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<PlayingCard> cardPile = new ArrayList<>();
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

    public PlayingCard topCardInStack() {
        if (!cardPile.isEmpty()) {
            return cardPile.remove(cardPile.size() - 1);
        } else
            throw new RuntimeException("Game Over.");
    }

}