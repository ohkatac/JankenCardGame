/*Controller, ゲームのメインの流れはここに書いていく 主な流れはMainGameModel
  Classであるmodelを受け取り、その中に入っているモデルたちを取り出してPanelに張り付けたりなどして扱っていく
  基本は
  FirstJankenPhase
  というフェイズで先攻後攻を決めた後
  PlayerPhase
  RivalPhase
  BattlePhase
  の3つのフェイズの流れをGotoNextPhase()メソッドを使って制御していくという方針
*/

package com.main_game.main_game_controller;

import java.util.ArrayList;
import java.util.Random; // 乱数生成のためのクラス
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.*;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;
import com.asset_controller.ImageButton;


final public class MainGameController {
  private MainGameModel model;
  private MainGamePanel panel;

  private Boolean isPlayFirst;

  private BasePhase nowPhase;

  private PlayerPhase playerPhase;
  private RivalPhase rivalPhase;
  private BattlePhase battlePhase;

  public MainGameController(MainGameModel model, MainGamePanel panel) 
  {
    this.model = model; // モデルを設定
    this.panel = panel; // MainGameのパネルのインスタンスを受け取る

// 一番初めはすべてのボタンを無効化
    model.getResultBtn().setEnabled(false);
    model.getDecideBtn().setEnabled(false);
    model.getNextBtn().setEnabled(false);

// 始めのフェイズのクラスのインスタンスを生成
    nowPhase = new FirstJankenPhase(this);

// あらかじめそれぞれのフェイズのインスタンスを作成しておく
    playerPhase = new PlayerPhase(this);
    rivalPhase = new RivalPhase(this);
    battlePhase = new BattlePhase(this);
  }

  // 先攻か後攻かを記録する変数に記録するためのメソッド
  public void setIsPlayFirst(Boolean b) { isPlayFirst = b; }

// FirstJankenPhase終了と同時にそのクラスから呼び出すメソッド。
  public void StartMainGame() {
    for(int i = 0; i < 5; i++) {
      model.getPlayer().DrawCard();
      model.getRival().DrawCard();
    }

    panel.getMyField().setVisible(true);
    panel.getRivalField().setVisible(true);

    if(isPlayFirst == true){
      nowPhase = playerPhase;
      nowPhase.startThisPhase();
   }
    else if(isPlayFirst == false){
      nowPhase = rivalPhase;
      nowPhase.startThisPhase();
    }
  }

// 次のフェイズへ進むメソッド、 それぞれのフェイズクラス内部から呼び出すこととする
  public void GotoNextPhase() {
    if(isPlayFirst == true) {
      if (nowPhase.getId() == BasePhase.PLAYER) {
        nowPhase = rivalPhase;
        nowPhase.startThisPhase();
      }
      else if (nowPhase.getId() == BasePhase.RIVAL) {
        nowPhase = battlePhase;
        nowPhase.startThisPhase();
      }
      else if (nowPhase.getId() == BasePhase.BATTLE) {
        nowPhase = playerPhase;
        nowPhase.startThisPhase();
      }
    }

    else if(isPlayFirst == false) {
      if (nowPhase.getId() == BasePhase.RIVAL) {
        nowPhase = playerPhase;
        nowPhase.startThisPhase();
      }
      else if (nowPhase.getId() == BasePhase.PLAYER) {
        nowPhase = battlePhase;
        nowPhase.startThisPhase();
      }
      else if (nowPhase.getId() == BasePhase.BATTLE) {
        nowPhase = rivalPhase;
        nowPhase.startThisPhase();
      }
    }

  }

// メインゲームを終了しリザルト画面へ進むメソッド
  public void FinishMainGame() { panel.GotoResult(); }

// method to return some Field Instance
  public MyFieldPanel getMyField() { return panel.getMyField(); }
  public MySidePanel getMySide() { return panel.getMySide(); }
  public BattleFieldPanel getBattleField() { return panel.getBattleField(); }
  public RivalFieldPanel getRivalField() { return panel.getRivalField(); }
  public RivalSidePanel getRivalSide() { return panel.getRivalSide(); }
  
  // MainGameModelよりそれぞれのボタンのインスタンスを取得
  public JButton getResultBtn() { return model.getResultBtn(); }
  public JButton getDecideBtn() { return model.getDecideBtn(); }
  public JButton getNextBtn() { return model.getNextBtn(); }

  // 現在各プレイヤーが場に出しているカードのインスタンスを返す
  public int getPlayerBattleHandIndex() { return getPlayer().getBattleHandIndex(); }
  public int getRivalBattleHandIndex() { return getRival().getBattleHandIndex(); }

  // プレイヤーモデルのインスタンスを取得
  public BasePlayer getPlayer() { return model.getPlayer(); }
  public BasePlayer getRival() { return model.getRival(); }
}