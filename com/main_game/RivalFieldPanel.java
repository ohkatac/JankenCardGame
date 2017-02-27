/*
  相手のフィールドのViewをつかさどるJPanel
*/

package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import java.io.IOException;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class RivalFieldPanel extends JPanel {
  MainGameModel model;
  BasePlayer rivalPlayer;
  ArrayList<CardModel> rivalHands = null;
  JLabel deckImg;

  public RivalFieldPanel(MainGameModel model, JPanel mainPanel) {
    super();
    this.model = model;
    try {
      deckImg = new JLabel( 
        new ImageIcon(
          ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/img/card/btnimg/deck.png"))
        )
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.setOpaque(false); // 背景画像を表示するためにこのJPanelそのものを透明化する。
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

// 相手のカードを表示させるメソッドを残しておく
  // public void ReshowCard(){
  //   this.removeAll();
  //   for(CardModel cm : rivalHands) {
  //     this.add(cm.getImageBtn());
  //     cm.getImageBtn().setVisible(true);
  //   }
  //   this.add(deckImg);
  //   deckImg.setVisible(true);
  // }
// 
  public void ReshowCard() {
    this.removeAll();
    for(int i = 0; i < rivalHands.size(); i++) {
      try {
        this.add(new JLabel(new ImageIcon(
          ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/img/card/btnimg/back.png"))
        )) );
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    this.add(deckImg);
    deckImg.setVisible(true);
  }
}