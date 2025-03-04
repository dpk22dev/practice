package javalang.streams;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class NestedStreamExample {


    private enum GameType {
        CARD, WHEEL, DICE
    }

    private static class Player {
        public int playerId;
        public String name;

        public Player(int playerId, String name) {
            this.playerId = playerId;
            this.name = name;
        }

        public int getPlayerId() {
            return playerId;
        }

        public String getName() {
            return name;
        }
    }

    private static class Game {
        public int gameId;
        public GameType gameType;

        public Game(int gameId, GameType gameType) {
            this.gameId = gameId;
            this.gameType = gameType;
        }

        public int getGameId() {
            return gameId;
        }

        public GameType getGameType() {
            return gameType;
        }
    }

    private static class Bet {
        public int betcode;
        public int amount;

        public Bet(int betcode, int amount) {
            this.betcode = betcode;
            this.amount = amount;
        }

        public int getBetcode() {
            return betcode;
        }

        public int getAmount() {
            return amount;
        }
    }

    private static class Table {
        public int id;
        public Map<Player, Map<Game, Set<Bet>>> userMap;

        public Table(int id, Map<Player, Map<Game, Set<Bet>>> userMap) {
            this.id = id;
            this.userMap = userMap;
        }

        public int getId() {
            return id;
        }

        public Map<Player, Map<Game, Set<Bet>>> getUserMap() {
            return userMap;
        }
    }

    public static void main(String[] args) {
        Map<Player, Map<Game, Set<Bet>>> userMap = Map.of(
                new Player(1, "p1"), Map.of(new Game(11, GameType.CARD), Set.of(new Bet(101, 1000), new Bet(102, 2000), new Bet(103, 3000))),
                new Player(1, "p1"), Map.of(new Game(41, GameType.DICE), Set.of(new Bet(101, 1000), new Bet(102, 2000), new Bet(103, 3000))),
                new Player(1, "p1"), Map.of(new Game(51, GameType.WHEEL), Set.of(new Bet(101, 1000), new Bet(102, 2000), new Bet(103, 3000))),
                new Player(2, "p2"), Map.of(new Game(21, GameType.DICE), Set.of(new Bet(201, 2000), new Bet(202, 1000), new Bet(203, 5000))),
                new Player(3, "p3"), Map.of(new Game(31, GameType.WHEEL), Set.of(new Bet(301, 2000), new Bet(302, 4000), new Bet(303, 3000)))
        );
        Table table1 = new Table(1, userMap);

        // Map<game-type, plaeyer-name> with bet amount > 2000

        Map<GameType, Set<String>> result = table1.getUserMap().entrySet().stream()
                // Step 1: Process each player and their games
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        // Map to (GameType, PlayerName) pairs for bets > 2000
                        .filter(gameEntry -> gameEntry.getValue().stream().anyMatch(bet -> bet.getAmount() > 2000))
                        .map(gameEntry -> Map.entry(gameEntry.getKey().getGameType(), entry.getKey().getName()))
                )
                // Step 2: Group by GameType and collect player names into a Set
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet())));

        // Print the result
        System.out.println(result);


    }

}
