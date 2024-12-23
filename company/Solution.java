package company;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    static class Card {
        String rankStr;
        String suit;
        String rankSuit;
        Integer rankNum;

        public Card(String inp) {
            this.rankSuit = inp;
            this.suit = inp.substring(inp.length() - 1);
            this.rankStr = inp.substring(0, inp.length() - 1);
            this.rankNum = this.getRankNum(rankStr);
        }

        private Integer getRankNum(String rankStr) {
            if (rankStr == "J") return 11;
            else if (rankStr == "Q") return 12;
            else if (rankStr == "K") return 13;
            else if (rankStr == "A") return 14;
            else return Integer.parseInt(rankStr);
        }
    }

    class Results {
        public String set_name;
        public String[] selected_cards;
    }

    public Results solution(String[] cards) {
        // Implement your solution here
        Results result = new Results();
/*

        result.set_name = ...
        result.selected_cards = ...
        return result;
*/
        LinkedList<Card> cardList = new LinkedList<>();
        Map<String, List<String>> rankMap = new HashMap<>();
        Map<String, List<String>> suitMap = new HashMap<>();

        for (String card : cards) {
            cardList.add(new Card(card));
        }

        cardList.sort((a, b) -> b.rankNum - a.rankNum);
        for (Card card : cardList) {
            rankMap.computeIfAbsent(card.rankStr, (k) -> new LinkedList<>());
            rankMap.computeIfPresent(card.rankStr, (k, list) -> {
                list.add(card.rankSuit);
                return list;
            });
        }

        List<String> res1 = checkIfTAndP(rankMap);
        if (res1.size() > 0) {
            result.set_name = "a triple and a pair";
            result.selected_cards = res1.toArray(String[]::new);
            return result;
        }

        res1 = checkIfSuit(suitMap);


        return null;


    }

    private List<String> checkIfSuit(Map<String, List<String>> suitMap) {
        return null;
    }

    private List<String> checkIfTAndP(Map<String, List<String>> rankMap) {
        List<String> res = new LinkedList<>();
        for (List<String> values : rankMap.values()) {
            if (values.size() > 3 || values.size() > 2) {
                res.addAll(values);
            }
        }
        return res.subList(0, 5);
    }


}


