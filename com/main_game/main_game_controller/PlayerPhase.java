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

  public PlayerPhase(MainGameController mainCont) {
    super(PLAYER);
    this.mainCont = mainCont;
    battleField = mainCont.getBattleField();
    myField = mainCont.getMyField();
    decideBtn = mainCont.getDecideBtn();
    player = mainCont.getPlayer();
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

  private void PopMyCard(int index) {
    player.PopCard(index);
    battleField.setMyCard(player.getPoppingCard());
    if(decideBtn.isEnabled() == false) { // 連続してListenerにComponentをaddするのを避けるためif文を入れる
      decideBtn.setEnabled(true);
      decideBtn.addActionListener(this);
    }
  }
}