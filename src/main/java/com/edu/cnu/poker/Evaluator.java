package com.edu.cnu.poker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cse on 2017-04-17.
 */
public class Evaluator {

    public String evaluate(List<Card> cardList) {
        Map<Suit, Integer> suitRelatedMap = new HashMap<Suit, Integer>();
        Map<Integer, Integer> rankRelatedMap = new HashMap<Integer, Integer>();


        if(isStraight(cardList, rankRelatedMap)&&isFlush(cardList,suitRelatedMap)){
            return  "STRAIGHT FLUSH";
        }
        else if(isFlush(cardList, suitRelatedMap)){
            return "FLUSH";
        }
        else if(isStraight(cardList,rankRelatedMap)){
            return  "STRAIGHT";
        }
        else if(isRoyal(cardList,rankRelatedMap)){
            return "ROYAL";
        }


        return "NOTHING";
    }

    private boolean isRoyal(List<Card> cardList, Map<Integer, Integer> rankRelatedMap) {
        for (Card card : cardList){
            if(rankRelatedMap.containsKey(11)&&rankRelatedMap.containsKey(12)&&rankRelatedMap.containsKey(13)){
                return true;
            }
            else{
                rankRelatedMap.put(card.getRank(),new Integer(1));
            }
        }
        return false;
    }

    private boolean isStraight(List<Card> cardList, Map<Integer, Integer> straightMap) {
        //Straight
        int[] straight;
        straight = new int[cardList.size()];  //추가하자면, 7개인 경우의 수도 무슨 포커를 하냐에 따라 지급한 카드의 수를 입력해야한다
        int i = 0;
        int count;
        for (Card card : cardList) {
            straight[i] = card.getRank();
            i++;
        }
        quickSort(straight, straight.length); // 오름차순으로 카드 정렬

        if (straight[0] < 10) { //10보다 크면 스트레이트가 날수가 없다. 마지막 스트레이트는 9 10 11 12 13 이기 때문
            for (int pointer = 0; pointer < straight.length-1; pointer++) {
                if (straight[pointer] - straight[pointer + 1] == -1) {   //여기서 첫번째 연속적인 숫자가 걸렷다.
                    count = 0;
                    for (int secondPointer = pointer + 1; secondPointer < straight.length; secondPointer++) {
                        //첫번째 연속적인 숫자와, 그 다음 숫자들도 연속적임
                        if (1 == straight[secondPointer] - straight[(secondPointer - 1)]) {
                            count++; //변수 count를 증가시킨다.
                            if (count==4) {
                                //count 3이 되면, 첫번쨰 연속적인 숫자가 잇고, 그 후에 연속적인 숫자가 3번이 나오게 되니까 스트레이트임
                                straightMap.put(straight[pointer],count);

                            }
                        }
                        else {
                            count = 0;
                        }
                    }
                }
                else{
                    straightMap.put(0,0);
                }
            }
        }

        for (Integer integer : straightMap.keySet()) {
            if (straightMap.get(integer) == 4) {
                //숫자중에 value가 4인것이 있으면 STRAIGHT
                return true;
            }
        }
        return false;
    }

    private boolean isFlush(List<Card> cardList, Map<Suit, Integer> tempMap) {
        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }


        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) >= 5) {
                return true;
            }
        }
        return false;
    }





    public void quickSort(int numbers[], int array_size) {
        q_sort(numbers, 0, array_size - 1);
    }

    public void q_sort(int numbers[], int left, int right) {
        int pivot, l_hold, r_hold;
        l_hold = left;
        r_hold = right;
        pivot = numbers[left];
        while (left < right) {
            while ((numbers[right] >= pivot) && (left < right))
                right--;
            if (left != right) {
                numbers[left] = numbers[right];
            }
            while ((numbers[left] <= pivot) && (left < right))
                left++;
            if (left != right) {
                numbers[right] = numbers[left];
                right--;
            }
        }
        numbers[left] = pivot;
        pivot = left;
        left = l_hold;
        right = r_hold;

        if (left < pivot)
            q_sort(numbers, left, pivot - 1);
        if (right > pivot)
            q_sort(numbers, pivot + 1, right);
    }
}