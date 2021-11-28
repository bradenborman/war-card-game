package borman.war;

public class PlayingCard implements Comparable<PlayingCard> {

    public enum Rank {

        DEUCE(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private final int value;

        Rank(int points) {
            this.value = points;
        }

        public int getValue() {
            return this.value;
        }

    }

    public enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES;
    }

    private final Rank rank;
    private final Suit suit;

    PlayingCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() {
        return this.rank;
    }

    public Suit suit() {

        return this.suit;

    }

    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public int compareTo(PlayingCard o) {
        return Integer.compare(o.rank.value, this.rank.value);
    }

}