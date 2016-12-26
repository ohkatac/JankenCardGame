package com.main_game.main_game_model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// new Fileを生成するために名前空間を入れる
import java.io.File;

import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.CardModel;
import com.main_game.main_game_model.player_model.*;
import com.asset_controller.RW_csv;

// MainGame's Model
final public class MainGameModel{
  private JButton resultBtn;
  private BasePlayer player;
  private BasePlayer rival;
  private int[] pl_deck = new int[20];
  private int[] ri_deck = new int[20];
  private RW_csv mainDeckdata;

  public MainGameModel() {
    resultBtn = new JButton("Go Result");

    // Player Deckの読み込みと確保
    mainDeckdata = new RW_csv( new File("assets/csv/main_deck.csv") );
    pl_deck = mainDeckdata.ReadCSV();
    // 相手のデッキを生成する 将来的には改善する あくまでダミーデータ
    for(int i = 0; i < pl_deck.length; i++) {
      ri_deck[i] = i%3 + 1;
    }

    player = new MyPlayer(pl_deck); // Player Modelを生成
    rival = new ComPlayer(ri_deck); // Rival Modelを生成
  }

  public JButton getResultBtn() { return resultBtn; }
  public BasePlayer getPlayer() { return player; }
  public BasePlayer getRival() { return rival; }
}
