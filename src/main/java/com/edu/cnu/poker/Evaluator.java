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

        for (Card card : cardList) {
            if (tempMap.containsKey(card.getSuit())) {
                Integer count = tempMap.get(card.getSuit());
                count = new Integer(count.intValue() + 1);
                tempMap.put(card.getSuit(), count);
            } else {
                tempMap.put(card.getSuit(), new Integer(1));
            }
        }
        //Straigth
        String x = isStraight(cardList);
        if (x != null)
            return x;


        //end
        for (Suit key : tempMap.keySet()) {
            if (tempMap.get(key) == 5) {
                return "FLUSH";
            }
        }
        return "NOTHING";

    }

    private String isStraight(List<Card> cardList) {
        //Straight
        int [] straight;
        straight = new int[cardList.size()];  //추가하자면, 7개인 경우의 수도 무슨 포커를 하냐에 따라 지급한 카드의 수를 입력해야한다
        int i = 0;
        for( Card card: cardList){
            straight[i]=card.getRank();
            i++;
        }
        quickSort(straight,straight.length); // 오름차순으로 카드 정렬

        if(straight[0]<10){ //10보다 크면 스트레이트가 날수가 없다. 마지막 스트레이트는 9 10 11 12 13 이기 때문
            int count = 0;
            for(int pointer = 1 ; pointer < straight.length; pointer++){
                if(straight[pointer] - straight[pointer-1] == 1){   //여기서 첫번째 연속적인 숫자가 걸렷다.
                    for(int secondPointer = pointer+1; secondPointer < straight.length; secondPointer++){
                        //첫번째 연속적인 숫자와, 그 다음 숫자들도 연속적임
                       if( straight[secondPointer] - straight[pointer] == 1){
                           count++; //변수 count를 증가시킨다.
                       }
                    }
                    if(count==3){
                        //count 3이 되면, 첫번쨰 연속적인 숫자가 잇고, 그 후에 연속적인 숫자가 3번이 나오게 되니까 스트레이트임
                        return "STRAIGHT";
                    }
                }
            }
        } else{
            return "NOTHING";
        }
        return null;
    }

    public void quickSort(int numbers[], int array_size)
    {
        q_sort(numbers, 0, array_size -1);
    }

    public void q_sort(int numbers[], int left, int right)
    {
        int pivot, l_hold, r_hold;
        l_hold = left;
        r_hold = right;
        pivot = numbers[left]; // 0번째 원소를 피봇으로 선택
        while (left < right)
        {
            // 값이 선택한 피봇과 같거나 크다면, 이동할 필요가 없다
            while ((numbers[right] >= pivot) && (left < right))
                right --;

            // 그렇지 않고 값이 피봇보다 작다면,
            // 피봇의 위치에 현재 값을 넣는다.
            if (left != right)
            {
                numbers[left] = numbers[right];
            }
            // 왼쪽부터 현재 위치까지 값을 읽어들이면서
            // 피봇보다 큰 값이 있다면, 값을 이동한다.
            while ((numbers[left] <= pivot) && (left < right))
                left ++;
            if (left != right)
            {
                numbers[right] = numbers[left];
                right --;
            }
        }
        // 모든 스캔이 끝났다면, 피봇값을 현재 위치에 입력한다.
        // 이제 피봇을 기준으로 왼쪽에는 피봇보다 작거나 같은 값만 남았다.
        numbers[left] = pivot;
        pivot = left;
        left = l_hold;
        right = r_hold;

        // 재귀호출을 수행한다.
        if (left < pivot)
            q_sort(numbers, left, pivot - 1);
        if (right > pivot)
            q_sort(numbers, pivot+1, right);
    }
}
