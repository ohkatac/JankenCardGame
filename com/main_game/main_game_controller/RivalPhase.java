package com.main_game.main_game_controller;

import java.awt.event.*;
import java.util.ArrayList;

import com.main_game.*;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_controller.rival_signal.ComSignal;

public class RivalPhase extends BasePhase {
  public ComSignal signal;

  private MainGameController mainCont;
  private BasePlayer rival;
  private BattleFieldPanel battleField;
  private RivalFieldPanel rivalField;
  private RivalSidePanel rivalSide;
  private ArrayList<CardModel> riHands;

  public RivalPhase(MainGameController mainCont) {
    super(RIVAL);
    this.mainCont = mainCont;
    rival = mainCont.getRival();
    battleField = mainCont.getBattleField();
    rivalSide = mainCont.getRivalSide();
    rivalField = mainCont.getRivalField();

    // 相手の手を読み取るクラスの宣言
    signal = new ComSignal(this, rival);
  }

  public void startThisPhase() {
    rivalField.setImvisible();
    rival.DrawCard();
    rivalField.ReshowCard();

    signal.startSignal();
  }

  public void endThisPhase() {
    signal.destroySignal();
  }

  public void DecideEvent() {
    endThisPhase();
    mainCont.GotoNextPhase();
  }

  private void PopRivalCard(int index) {
    rival.PopCard(index);
    battleField.setRivalCard(rival.getPoppingCard());
  }

  public void signalAction(String st) {
    if(st == "decide") {
      if(rival.getPoppingCard() != null) {
        DecideEvent();
      }
      return ;
    }
    int index = Integer.parseInt(st);
    if( index < rival.getHands().size() ) PopRivalCard(index);
  }
}