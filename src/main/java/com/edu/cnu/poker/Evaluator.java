package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {
    public String evaluate(List<Card> cardList) {

        Map<Suit, Integer> tempMap = new HashMap<Suit, Integer>();
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();

        boolean onePair = false;
        boolean triple = false;

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }
        for (Card card : cardList) {
            if (countMap.containsKey(card.getRank())) {
                Integer count = countMap.get(card.getRank());
                count = new Integer(count.intValue() + 1);
                countMap.put(card.getRank(), count);
            } else {
                countMap.put(card.getRank(), new Integer(1));
            }
        }

        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return "FLUSH";
            }
        }
        for (Integer key : countMap.keySet()) {

            if(countMap.get(key) == 2){
                onePair = true;
            }
            else if (countMap.get(key) == 3) {
                triple = true;
            }
            else if(countMap.get(key) == 4){
                return "FOURCARD";
            }
        }
        if(onePair && triple){
            return "FULLHOUSE";
        }
        else if(triple){
            return "TRIPLE";
        }
        else if(onePair){
            return "ONEPAIR";
        }
        return "NOTHING";
    }
}
