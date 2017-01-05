package com.main_game.main_game_model.player_model;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

// to use " new File() "
import java.io.File;

import com.main_game.main_game_model.card_model.CardModel;

public class MyPlayer extends BasePlayer {
  public MyPlayer(int[] data) {
    super(20);
    for(int i = 0; i < data.length; i++) {
      super.deck.add(new CardModel(data[i]));
    }
    imgLabel = new JLabel( new ImageIcon("assets/img/player/my.png") );
  }
}