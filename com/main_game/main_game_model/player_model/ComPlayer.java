package com.main_game.main_game_model.player_model;

import java.util.ArrayList;

// import java.util.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

// to use " new File() "
import java.io.File;

import com.asset_controller.RW_csv;
import com.main_game.main_game_model.card_model.CardModel;

public class ComPlayer extends BasePlayer{
  RW_csv deckCsv;
  public ComPlayer(int[] deckData) {
    super(20);
    for(int i = 0; i < deck.size(); i++) {
      deck.add(new CardModel(deckData[i]));
    }
    imgLabel = new JLabel( new ImageIcon("assets/img/player/rival.png") );
  }
}