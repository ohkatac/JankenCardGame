package com.main_game.main_game_model.player_model;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

// to use " new File() "
import java.io.File;

import com.main_game.main_game_model.card_model.CardModel;

public class MyPlayer extends BasePlayer {
  public MyPlayer(int[] data) {
    super(20);
    ArrayList<Integer> temp = new ArrayList<Integer>();
    for(int i = 0; i < data.length; i++) {
      temp.add(data[i]);
    }
    for(int i = 0; i < data.length; i++) {
      Random r = new Random();
      int rnd_i = r.nextInt(temp.size());
      data[i] = temp.get( rnd_i );
      temp.remove( rnd_i );
    }
    for(int i = 0; i < data.length; i++) {
      super.deck.add(new CardModel(data[i]));
    }
  }
}