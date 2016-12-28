package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class MyFieldPanel extends JPanel {
  MainGameModel model;
  BasePlayer myPlayer;
  ArrayList<CardModel> myHands = null;

  public MyFieldPanel(MainGameModel model, JPanel mainPanel) {
    super();
    this.model = model;
    // カードの画像の高さが140pxなので160に設定
    this.setPreferredSize( new Dimension(mainPanel.getPreferredSize().width, 160) ); 

    this.setLayout( new FlowLayout() );

    myPlayer = model.getPlayer();
    myHands = myPlayer.getHands();

    for(int i = 0; i < myHands.size(); i++) {
      // 将来的にTimerをセットしてアニメーションを入れる
      this.add(myHands.get(i).getImageBtn());
    }
    this.add( new JLabel( new ImageIcon("assets/img/card/Deck.png") ) );
  }

}