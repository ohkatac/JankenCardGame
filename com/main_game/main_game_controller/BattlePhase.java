/*
  場に出されたカードをもとに勝敗判定をするためのフェイズクラス

*/

package com.main_game.main_game_controller;

import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;

import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;


public class BattlePhase extends BasePhase implements ActionListener{
  private MainGameController mainCont;
  private BasePlayer player, rival;
  private CardModel plbattleCard, ribattleCard;
  private int judge;
  private JButton nextBtn;

  public BattlePhase(MainGameController mainCont) {
    super(BATTLE);
    this.mainCont = mainCont;
    player = mainCont.getPlayer();
    rival = mainCont.getRival();
    plbattleCard = player.getPoppingCard();
    ribattleCard = rival.getPoppingCard();
    nextBtn = mainCont.getNextBtn();
  }

// 子のフェイズの初めにMainGameControllerより呼び出されるメソッド

  // Override
  public void startThisPhase() {
    judge = isWinPlayer(player.getPoppingCard(), rival.getPoppingCard());

    switch(judge) {
      case 1:
        rival.Damage(player.getPoppingCard().getCost());
        break;
      case -1:
        player.Damage(rival.getPoppingCard().getCost());
        break;
      default:
    }

    mainCont.getBattleField().openRivalCard();
    // バトルの詳細を表示
    mainCont.getRivalSide().showCaption(player, rival, player.getPoppingCard(), rival.getPoppingCard(), judge);
    nextBtn.setEnabled(true);
    nextBtn.addActionListener(this);
  }

// このフェイズを終了するときに呼び出されるメソッド ActionLisetenerなどの後始末が基本となっている。
// Override
  public void endThisPhase() {
    mainCont.getBattleField().RemoveCards();
    mainCont.getRivalSide().deleteCaption();

    mainCont.getMyField().ReshowCard();
    mainCont.getRivalField().ReshowCard();

    nextBtn.setEnabled(false);
    nextBtn.removeActionListener(this);

    player.RemoveHandsCard(player.getBattleHandIndex());
    rival.RemoveHandsCard(rival.getBattleHandIndex());

    mainCont.getMyField().ReshowCard();
    mainCont.getRivalField().ReshowCard();

    if(player.getLife() < 0 || rival.getLife() < 0) { mainCont.FinishMainGame(); }
    else { mainCont.GotoNextPhase(); }

  }

  public void actionPerformed(ActionEvent e) {
    // 次のバトルへ進むための処理
    if (e.getSource() == nextBtn) { endThisPhase(); }
  }

// 勝敗判定をするためのメソッド playerが勝ったら1, 引き分けなら0, 負けなら-1が返るようになっている。
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