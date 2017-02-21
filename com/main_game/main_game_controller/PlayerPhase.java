/*
  自分のターン(フェイズ)のController
  一番初めはdecideBtnを無効化しておく
  自分の手札からカードが選ばれたら(ActionListenerによって)
*/

package com.main_game.main_game_controller;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;

import com.main_game.*;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;

public class PlayerPhase extends BasePhase implements ActionListener {
  private MainGameController mainCont;
  private MyFieldPanel myField;
  private BattleFieldPanel battleField;
  private JButton decideBtn;
  private BasePlayer player;

  private Boolean isLocalhost;
  private Boolean isServer;
  private int port;

  public PlayerPhase(MainGameController mainCont) {
    super(PLAYER);
    this.mainCont = mainCont;
    battleField = mainCont.getBattleField();
    myField = mainCont.getMyField();
    decideBtn = mainCont.getDecideBtn();
    player = mainCont.getPlayer();
    this.isLocalhost = mainCont.getIsLocalhost();
  }

  public PlayerPhase(MainGameController mainCont, int port, Boolean isServer) {
    this(mainCont);
    this.port = port;
    this.isServer = isServer;
  }

// Override
  public void startThisPhase() {
    myField.setImvisible();
    player.DrawCard();
    myField.ReshowCard();

// decideBtn, 手札のボタンをActionListenerに加える
    for(CardModel cm : player.getHands() ){
      cm.EnableButton();
      cm.getImageBtn().addActionListener(this);
    }

    // 今がなんのフェイズなのかを表示
    mainCont.getRivalSide().showNowPhase(super.id);
  }

// Override
  public void endThisPhase() {
    // フェイズの変更のためすべてのボタンを無効化
    decideBtn.setEnabled(false);
    decideBtn.removeActionListener(this);
    for( CardModel cm : player.getHands() ) {
      cm.DisableButton();
      cm.getImageBtn().removeActionListener(this);
    }
    // ターンの詳細を消す
    mainCont.getRivalSide().deleteCaption();

    mainCont.GotoNextPhase();
  }

  public void actionPerformed(ActionEvent e) {
    // 手札入力の読み取り
    for(int i = 0; i < player.getHands().size(); i++) {
      if(e.getSource() == player.getHands().get(i).getImageBtn()) PopMyCard(i);
    }

    if (e.getSource() == decideBtn) { // どのカードを出すかを決定するボタン
      endThisPhase();
    }
  }

// 自分の手札からカードを一枚場に出すメソッド。 この段階では手札からカードをremoveする処理は行わない
// またdecideBtnを有効化させて次のフェイズに進めるようにする。
  private void PopMyCard(int index) {
    // playerクラスのpoppingCardメンバに今場に出しているカードを指定する。
    player.PopCard(index);
    // 場にカードをセットする。
    battleField.setMyCard(player.getPoppingCard());
    if(decideBtn.isEnabled() == false) { // 連続してListenerにComponentをaddするのを避けるためif文を入れる
      decideBtn.setEnabled(true);
      decideBtn.addActionListener(this);
    }
  }
}