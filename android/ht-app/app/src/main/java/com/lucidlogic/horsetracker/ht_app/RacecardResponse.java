package com.lucidlogic.horsetracker.ht_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardResponse {

    private String requestID;
    private List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }



    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }
}
