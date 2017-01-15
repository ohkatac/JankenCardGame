package com.main_game.main_game_controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_controller.rival_signal.ComSignal;

public class FirstJankenPhase extends BasePhase implements ActionListener {
  MainGameController mainCont;
  private int plBattleId, riBattleId;
  private CardModel gu, chi, pa;

  private ComSignal signal;

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

    mainCont.getMyField().add(gu.getImageBtn());
    mainCont.getMyField().add(chi.getImageBtn());
    mainCont.getMyField().add(pa.getImageBtn());

    gu.getImageBtn().addActionListener(this);
    chi.getImageBtn().addActionListener(this);
    pa.getImageBtn().addActionListener(this);

    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
    mainCont.getRivalField().add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png")));
  }

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

  //Override
  public void signalAction(String data) {
    if(riBattleId == -1) {
      InputJanken(Integer.parseInt(data), BasePlayer.RIVAL);
      signal.destroySignal();
    }
  }

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

          gu.EnableButton();
          chi.EnableButton();
          pa.EnableButton();

          signal.startSignal();
          break;
        case 1:
          // 後攻に設定する
          mainCont.setIsPlayFirst(false);
          signal.destroySignal();

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
          signal.destroySignal();

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