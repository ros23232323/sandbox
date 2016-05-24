package com.lucidlogic.horsetracker.ht_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itowey on 23/05/16.
 */
public class RacecardDTO {

  private String requestID;

  private List<CardDTO> cards = new ArrayList<>();

  public List<CardDTO> getCards() {
    return this.cards;
  }

  public void setCards(final List<CardDTO> cards) {
    this.cards = cards;
  }

  public String getRequestID() {
    return this.requestID;
  }

  public void setRequestID(final String requestID) {
    this.requestID = requestID;
  }
}
