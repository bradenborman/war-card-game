package borman.war;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class WarSimulator implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WarSimulator.class);

    @Override
    public void run(String... args) {

        List<PlayingCard> deck = Deck.newShuffledDeck();

        List<Player> players = Arrays.asList(
                new Player("Austin"),
                new Player("Casandra")
        );

        Deck.dealToPlayers(deck, players);

        int turnCount = 0;

        do {
            turnCount = turnCount + 1;
            playWar(players, Collections.emptyList());
        } while (checkWinner(players));

        logger.info("Game over after {} turns!", turnCount);

    }

    boolean checkWinner(List<Player> players) {
        return players.stream().noneMatch(player -> player.getCardPile().isEmpty());
    }

    private void playWar(List<Player> players, List<PlayingCard> cardsToAwardWinner) {

        logPlay(players);

        HashMap<String, PlayingCard> cardsPlayed = new HashMap<>();

        //everyone play a card
        players.forEach(player -> {
            cardsPlayed.put(player.getName(), player.topCardInStack());
        });

        //Sort map by highest value player -- doesn't take into ties yet
        Map<String, PlayingCard> sorted = cardsPlayed
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, PlayingCard>comparingByValue())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)
                );

        Iterator<Map.Entry<String, PlayingCard>> iterator = sorted.entrySet().iterator();

        Map.Entry<String, PlayingCard> highestPlayedCard = iterator.next();
        Map.Entry<String, PlayingCard> nextHighest = iterator.next();

        if (highestPlayedCard.getValue().rank().getValue() == nextHighest.getValue().rank().getValue()) {
            //handle tie

            logger.info("TIE IN CARDS PLAYED! {} | {}", highestPlayedCard.getValue(), nextHighest.getValue());

            List<Player> tiedPlayers = players.stream()
                    .filter(player -> player.getName().equals(highestPlayedCard.getKey()) || player.getName().equals(nextHighest.getKey()))
                    .collect(Collectors.toList());

            List<PlayingCard> tiedReward = new ArrayList<>();
            tiedPlayers.forEach(player ->
                    IntStream.range(0, player.getCardPile().size() > 3 ? 3 : player.getCardPile().size() - 2)
                            .forEach(x -> {
                                tiedReward.add(player.topCardInStack());
                            })
            );

            logger.info("Rewarded cards in tie: {}", tiedReward.stream().map(PlayingCard::toString).collect(Collectors.joining(", ")));

            tiedReward.addAll(cardsToAwardWinner);
            tiedReward.add(highestPlayedCard.getValue());
            tiedReward.add(nextHighest.getValue());

            logger.info("Number of cards awarded to next winner: {}", tiedReward.size());
            playWar(tiedPlayers, tiedReward);

        } else {
            Player winningPlayer = players.stream()
                    .filter(player -> player.getName().equals(highestPlayedCard.getKey()))
                    .findFirst()
                    .orElseThrow(RuntimeException::new);

            cardsPlayed
                    .values()
                    .forEach(winningPlayer::collectWinnings);

            winningPlayer.getCardPile().addAll(winningPlayer.getCardPile().size() - 1, cardsToAwardWinner);
        }


    }


    private void logPlay(List<Player> players) {
        logger.info("Before WAR: {}'s deck size: {}\t  {}'s Deck size: {}",
                players.get(0).getName(),
                players.get(0).getCardPile().size(),
                players.get(1).getName(),
                players.get(1).getCardPile().size());
    }

}