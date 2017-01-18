/*
  RivalPhaseをつかさどるConttollerクラス
  ネットワーク対戦にも対応するためにrival_signal/ComSignal.javaクラスを生成し、
  そこから信号を受け取る(String)ことで
*/

package com.main_game.main_game_controller;

import java.awt.event.*;
import java.util.ArrayList;

import com.main_game.*;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_controller.rival_signal.*;

public class RivalPhase extends BasePhase {
  public BaseSignal signal;

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

// このフェイズが始まるときにMainGameControllerから呼び出されるメソッド
// Override
  public void startThisPhase() {
    rivalField.setImvisible();
    rival.DrawCard();
    rivalField.ReshowCard();

    signal.startSignal();
  }

// このフェイズが始まるときにMainGameControllerから呼び出されるメソッド
// Override
  public void endThisPhase() {
    signal.stopSignal();
  }

// 場に出すカードを決めるメソッド
  public void DecideEvent() {
    endThisPhase();
    mainCont.GotoNextPhase();
  }

// 相手のカードを場に出すメソッド
  private void PopRivalCard(int index) {
    // rivalクラスの中のpoppingCardに手札のindexに相当するカードを代入する。
    rival.PopCard(index);
    battleField.setRivalCard(rival.getPoppingCard());
  }


// ComSignalクラスからこのメソッドが呼び出される。 ちなみにComSignalクラスはTimer駆動でString変数をその都度送ってくるという仕様
// Override
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