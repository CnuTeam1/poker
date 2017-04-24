package com.edu.cnu.poker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cse on 2017-04-17.
 */
public class EvaluatorTest {

    @Test
    public void SUIT가_5개가동일하면_플러쉬다() {
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(1,Suit.CLUBS),
                new Card(4,Suit.CLUBS),
                new Card(8,Suit.CLUBS),
                new Card(13,Suit.CLUBS),
                new Card(2,Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("FLUSH"));
    }
    @Test
    public void 연속적인_5개카드면_스트레이트다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(11,Suit.CLUBS),
                new Card(3,Suit.DIAMONDS),
                new Card(5,Suit.HEARTS),
                new Card(1,Suit.HEARTS),
                new Card(1,Suit.SPADES),
                new Card(2,Suit.CLUBS),
                new Card(7,Suit.DIAMONDS)
                );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHT"));
    }

    @Test
    public void 스트레이트이면서_플러시이면_스트레이트플러시이다(){
        Evaluator evaluator = new Evaluator();
        List<Card> cardList = Arrays.asList(
                new Card(4,Suit.CLUBS),
                new Card(5,Suit.CLUBS),
                new Card(6,Suit.CLUBS),
                new Card(7,Suit.CLUBS),
                new Card(8,Suit.CLUBS)
        );
        String result = evaluator.evaluate(cardList);
        assertThat(result, is("STRAIGHT FLUSH"));
    }
}