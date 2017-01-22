/*
  一番初めの先攻後攻を決めるためのじゃんけんフェイズ
  コンピュータ対戦。ネットワーク対戦両方に対応できるように
  com/main_game/main_game_model/rival_signal/ComSignal.java(Timer駆動にしてある)
  クラスより発信してきた信号(String value)を読み取りそれにより相手の動きをこちら側でも再現している。
  後にネットワーク対戦も実装する。・
*/

package com.main_game.main_game_controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_controller.rival_signal.*;

public class FirstJankenPhase extends BasePhase implements ActionListener {
  MainGameController mainCont;
  private int plBattleId, riBattleId;
  private CardModel gu, chi, pa;

  private BaseSignal signal;

  public FirstJankenPhase(MainGameController mainCont) {
    super(BasePhase.FIRST);
    this.mainCont = mainCont;
    // 始めのじゃんけんの処理をここに入れる

    plBattleId = -1;
    riBattleId = -1;

// Computer対戦の時はこのsignalを指定する。 ネットワーク対戦の場合は後で記述する。
    signal = new ComSignal(this, mainCont.getRival());
    signal.startSignal();

    gu = new CardModel(1);
    chi = new CardModel(2);
    pa = new CardModel(3);

// 自分の場にじゃんけんカードをセット
    mainCont.getMyField().add(gu.getImageBtn());
    mainCont.getMyField().add(chi.getImageBtn());
    mainCont.getMyField().add(pa.getImageBtn());

    gu.getImageBtn().addActionListener(this);
    chi.getImageBtn().addActionListener(this);
    pa.getImageBtn().addActionListener(this);

// 相手フィールドにもカードを裏面で設置
    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
  }

// 自分が何を出したのかをよみとるコールバックメソッド。 
// Override
  public void actionPerformed(ActionEvent e) {
    // ここに初めのじゃんけんの処理を入れる
    if (e.getSource() == gu.getImageBtn()) {
      InputJanken(CardModel.GU, BasePlayer.PLAYER); 
    }
    else if (e.getSource() == chi.getImageBtn()) {
      InputJanken(CardModel.CHI, BasePlayer.PLAYER); 
    }
    else if (e.getSource() == pa.getImageBtn()) {
      InputJanken(CardModel.PA, BasePlayer.PLAYER); 
    }
  }

// 相手が何を出したのかを読み取るためのメソッド。 rival_signal側でこのメソッドが呼び出され、それによって動きを再現している。
  //Override
  public void signalAction(String data) {
    if(riBattleId == -1) {
      InputJanken(Integer.parseInt(data), BasePlayer.RIVAL);
      signal.stopSignal();
    }
  }

// 自分もしくは相手がじゃんけんの手を出したときに呼び出すメソッド、 両プレイヤーが手を出していたら勝敗判定が始まる。
  public void InputJanken(int jankenId, int basePlayerId) {
    if(basePlayerId == BasePlayer.PLAYER) {
      // 
      this.plBattleId = jankenId;
      mainCont.getBattleField().setMyCard(new CardModel(jankenId));
    }
    else if(basePlayerId == BasePlayer.RIVAL) {
      //
      this.riBattleId = jankenId;
      mainCont.getBattleField().setRivalCard( new CardModel(jankenId) );
    }

    if(plBattleId != -1 && riBattleId != -1) {
      switch((plBattleId-riBattleId+3) % 3) {
        case 0:
          // あいこの時の処理
          plBattleId = -1;
          riBattleId = -1;
          // もう一回じゃんけんのやりなおし

          gu.EnableButton();
          chi.EnableButton();
          pa.EnableButton();

          signal.startSignal(); // 再び信号発信を再開させる。
          break;
        case 1:
          // 後攻に設定する
          mainCont.setIsPlayFirst(false);
          signal.stopSignal();

          // フェイズ終了処理
          gu.DisableButton();
          chi.DisableButton();
          pa.DisableButton();

          mainCont.getRivalField().ReshowCard();
          mainCont.getMyField().ReshowCard();

          mainCont.StartMainGame();
          break;
        case 2:
          // 先攻に設定する
          mainCont.setIsPlayFirst(true);
          signal.stopSignal();

          // フェイズ終了処理
          gu.DisableButton();
          chi.DisableButton();
          pa.DisableButton();
          mainCont.getRivalField().ReshowCard();
          mainCont.getMyField().ReshowCard();

          mainCont.StartMainGame();
          break;
      }
      mainCont.getBattleField().RemoveCards();
    }
  }

}
