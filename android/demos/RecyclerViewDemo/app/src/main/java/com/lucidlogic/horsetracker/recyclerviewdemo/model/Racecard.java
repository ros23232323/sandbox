package com.lucidlogic.horsetracker.recyclerviewdemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 25/05/16.
 */
public class Racecard {

    private String requestID;

    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return this.cards;
    }

    public void setCards(final List<Card> cards) {
        this.cards = cards;
    }

    public String getRequestID() {
        return this.requestID;
    }

    public void setRequestID(final String requestID) {
        this.requestID = requestID;
    }
}
