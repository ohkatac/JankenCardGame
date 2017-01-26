/*
  MainGameのすべてのModelが入っているクラスです。 ここからgetterメソッドで目的のModelのインスタンスを取り出すことができます。
  MainGameのModelは
  どのカードを出すかの決定ボタン(decideBtn)
  次のバトルへ進むためのボタン(nextBtn)
  player, rivalモデル
  (playerモデルを生成するときにデッキデータが必要なため"assets/csv/main_deck.csv"から読み込み代入)
  バトル結果の詳細を表示するためのJLabel(battleCaption)

  以上のようになっており生成したらあとは外部からModelを取り出すだけのクラスです。
*/

package com.main_game.main_game_model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// new Fileを生成するために名前空間を入れる
import java.io.File;

import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.CardModel;
import com.main_game.main_game_model.player_model.*;
import com.asset_controller.*;

// MainGame's Model
final public class MainGameModel{
  private ImageButton resultBtn;
  private ImageButton decideBtn;
  private ImageButton nextBtn;
  private BasePlayer player;
  private BasePlayer rival;
  private JPanel battleCaption;

  // temporary data for pl, ri deck

  private int[] pl_deck;
  private int[] ri_deck;

  public MainGameModel(int[] pl_deck, int[] ri_deck) {
    // Player Deckの読み込みと確保
    this.pl_deck = pl_deck;
    this.ri_deck = ri_deck;

    resultBtn = new ImageButton(
      new String[] {
        "assets/img/edit_button/goResultButton.png", 
        "assets/img/edit_button/goResultButton_pressed.png", 
        "assets/img/edit_button/goResultButton_hover.png", 
        "assets/img/edit_button/goResultButton_unable.png"  
      }
    );
    decideBtn = new ImageButton(
      new String[] {
        "assets/img/edit_button/cardButton.png", 
        "assets/img/edit_button/cardButton_pressed.png", 
        "assets/img/edit_button/cardButton_hover.png", 
        "assets/img/edit_button/cardButton_unable.png"  
      }
    );
    nextBtn = new ImageButton(
      new String[] {
        "assets/img/edit_button/battleButton.png", 
        "assets/img/edit_button/battleButton_pressed.png", 
        "assets/img/edit_button/battleButton_hover.png", 
        "assets/img/edit_button/battleButton_unable.png"  
      }
    );

    player = new MyPlayer(pl_deck); // Player Modelを生成
    rival = new ComPlayer(ri_deck); // Rival Modelを生成
  }

  public ImageButton getResultBtn() { return resultBtn; }
  public ImageButton getDecideBtn() { return decideBtn; }
  public ImageButton getNextBtn() { return nextBtn; }
  public BasePlayer getPlayer() { return player; }
  public BasePlayer getRival() { return rival; }
  public JPanel getBattleCaption() { return battleCaption; }
}