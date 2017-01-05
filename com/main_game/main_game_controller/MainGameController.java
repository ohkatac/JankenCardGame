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

//Controller, ゲームのメインの流れはここに書いていく 主な流れはMainGameModel 
//Classであるmodelを受け取り、その中に入っているモデルたちを取り出してPanelに張り付けたりなどして扱っていく

final public class MainGameController implements ActionListener {
  private MainGameModel model;
  private MainGamePanel panel;

  private MyFieldPanel myField;
  private MySidePanel mySide;
  private BattleFieldPanel battleField;
  private RivalFieldPanel rivalField;
  private RivalSidePanel rivalSide;

  private JButton sideCaption;
  private JButton nextBtn;

  private JButton resultBtn;
  private JButton decideBtn;

  private Boolean decideEnable;
  private BasePlayer player;
  private BasePlayer rival;

  private CardModel plbattleCard;
  private int plbattleId;
  private CardModel ribattleCard;
  private int ribattleId;

  private ArrayList<CardModel> handsModels;

  public MainGameController(MainGameModel model, MainGamePanel panel) 
  {
    this.model = model; // モデルを設定
    this.panel = panel; // MainGameのパネルのインスタンスを受け取る

    // 各種フィールドのインスタンスを取得、ここに
    myField = panel.getMyField();
    mySide = panel.getMySide();
    battleField = panel.getBattleField();
    rivalField = panel.getRivalField();
    rivalSide = panel.getRivalSide();
    //

    resultBtn = model.getResultBtn(); // 結果ボタンをモデルから取得
    decideBtn = model.getDecideBtn(); //決定ボタンをモデルから取得
    nextBtn = model.getNextBtn();
    nextBtn.setEnabled(false);
    decideBtn.setEnabled(false);
    decideEnable = false; // 決定ボタンをはじめは無効化する。

    player = model.getPlayer(); // プレイヤーモデルをMainGameModelから取得
    rival = model.getRival(); // ライバルモデルをMainGameModelから取得
    handsModels = player.getHands(); // プレイヤーモデルから手札のCardModelリスト構造を受け取る

    // 手札のボタンのモデルを受け取りActionListenerに追加
    for(int i = 0; i < handsModels.size(); i++) {
      handsModels.get(i).getImageBtn().setActionCommand("CardBtn"+i);
      handsModels.get(i).getImageBtn().addActionListener(this);
    }

    // 各種ボタンをActionListenerに入れる
    nextBtn.addActionListener(this);
    resultBtn.addActionListener(this);
    decideBtn.addActionListener(this);

    // Timerを使って将来的にアニメーションを実現させる。
    PopRivalCard();

  }

  private void PopRivalCard() {
    Random rnd = new Random();
    int randomId = rnd.nextInt(rival.getHands().size());
    ribattleCard = rival.PopCard(randomId);
    battleField.setRivalCard(ribattleCard);
    ribattleId = randomId;
  }

  private void PopMyCard(int index) {
    decideEnable = true;
    decideBtn.setEnabled(true);
    plbattleCard = player.PopCard(index);
    battleField.setMyCard(plbattleCard);
    plbattleId = index;
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resultBtn) {
      // リザルト画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      panel.GotoResult();
    }
    else if (e.getSource() == decideBtn) {
      // どのカードを出すかを決定するボタン
      decideEnable = false;
      decideBtn.setEnabled(false);
      for( CardModel m : handsModels ) {
        m.DisableButton();
      }
      int judge = isWinPlayer(plbattleCard, ribattleCard);
      battleField.openRivalCard();
      switch(judge) {
        case 1:
          rival.Damage(plbattleCard.getCost());
          break;
        case -1:
          player.Damage(ribattleCard.getCost());
          break;
        default:
      }

      // バトルの詳細を表示
      rivalSide.ShowCaption(player, rival, plbattleCard, ribattleCard, judge);

      nextBtn.setEnabled(true);
    }

    else if (e.getSource() == nextBtn) {
      for( CardModel m : handsModels ) {
        m.EnableButton();
      }
      battleField.RemoveCards();
      rivalSide.DeleteCaption();
      nextBtn.setEnabled(false);

      CardModel temp;

      myField.setImvisible();
      player.RemoveHandsCard(plbattleId);
      temp = player.DrawCard();
      if(temp != null) temp.getImageBtn().addActionListener(this);
      myField.ReshowCard();

      rivalField.setImvisible();
      if(rival.getHands().get(ribattleId) != null) rival.RemoveHandsCard(ribattleId);
      temp = rival.DrawCard();
      if(temp != null) temp.getImageBtn().addActionListener(this);
      rivalField.ReshowCard();

      if(rival.getHands().size() > 0) PopRivalCard();
    }

    for(int i = 0; i < handsModels.size(); i++) {
      if(e.getSource() == handsModels.get(i).getImageBtn()) PopMyCard(i);
    }

  }

  public int isWinPlayer(CardModel plCard, CardModel riCard) {
    // 単色カード同士のバトルの場合
    int pl = plCard.getID();
    int ri = riCard.getID();

    switch(pl) {
      case 1:
        switch(ri) {
          case 1: return 0;
          case 2: return 1;
          case 3: return -1;
          case 4: return 0;
          case 5: return -1;
          case 6: return -1;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 2:
        switch(ri) {
          case 1: return -1;
          case 2: return 0;
          case 3: return 1;
          case 4: return -1;
          case 5: return 0;
          case 6: return -1;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 3:
        switch(ri) {
          case 1: return 1;
          case 2: return -1;
          case 3: return 0;
          case 4: return -1;
          case 5: return -1;
          case 6: return 0;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 4:
        switch(ri) {
          case 1: return 0;
          case 2: return 1;
          case 3: return 1;
          case 4: return 0;
          case 5: return -1;
          case 6: return 1;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 5:
        switch(ri) {
          case 1: return 1;
          case 2: return 0;
          case 3: return 1;
          case 4: return 1;
          case 5: return 0;
          case 6: return -1;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 6:
        switch(ri) {
          case 1: return 1;
          case 2: return 1;
          case 3: return 0;
          case 4: return -1;
          case 5: return 1;
          case 6: return 0;
          case 7: return -1;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      case 7:
        switch(ri) {
          case 1: return 1;
          case 2: return 1;
          case 3: return 1;
          case 4: return 1;
          case 5: return 1;
          case 6: return 1;
          case 7: return 0;
          default:
            System.out.println("相手のじゃんけんの値が不正です。");
            return -2;
        }
      default:
        System.out.println("あなたのじゃんけんの値が不正です。");
        return -2;
    }
  }
}