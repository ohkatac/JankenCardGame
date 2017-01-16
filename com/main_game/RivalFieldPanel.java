/*
  相手のフィールドのViewをつかさどるJPanel
*/

package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;


import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class RivalFieldPanel extends JPanel {
  MainGameModel model;
  BasePlayer rivalPlayer;
  ArrayList<CardModel> rivalHands = null;
  JLabel deckImg = new JLabel( new ImageIcon("assets/img/card/btnImg/deck.png") );

  public RivalFieldPanel(MainGameModel model, JPanel mainPanel) {
    super();
    this.model = model;
    // カードの画像の高さが140pxなので160に設定
    this.setPreferredSize( new Dimension(mainPanel.getPreferredSize().width, 160) );

    this.setLayout( new FlowLayout() );

    rivalPlayer = model.getRival();
    rivalHands = rivalPlayer.getHands();
  }

  public void setImvisible() {
    for(CardModel cm : rivalHands) {
      cm.getImageBtn().setVisible(false);
    }
    deckImg.setVisible(false);
  }

// Debug用に相手のカードを表示させるメソッドを残しておく
  public void ReshowCard(){
    this.removeAll();
    for(CardModel cm : rivalHands) {
      this.add(cm.getImageBtn());
      cm.getImageBtn().setVisible(true);
    }
    this.add(deckImg);
    deckImg.setVisible(true);
  }
// 
  public void closeCards() {
    this.removeAll();
    for(int i = 0; i < rivalHands.size(); i++) {
      this.add(new JLabel(new ImageIcon("assets/img/card/btnimg/back.png" )) );
    }
    this.add(deckImg);
    deckImg.setVisible(true);
  }
}