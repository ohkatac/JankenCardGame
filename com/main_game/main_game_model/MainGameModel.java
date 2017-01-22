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
  private JButton resultBtn;
  private JButton decideBtn;
  private JButton nextBtn;
  private BasePlayer player;
  private BasePlayer rival;
  private JPanel battleCaption;

  // temporary data for pl, ri deck
  private int[] pl_deck = new int[20];
  private int[] ri_deck = new int[20];

  Boolean isLocalhost = false;

  public MainGameModel(Boolean isLocalhost) {
    this.isLocalhost = isLocalhost;

    resultBtn = new JButton("Go Result");
    decideBtn = new JButton("カードを出す");
    nextBtn = new JButton("次のバトルへ進む");

    // Player Deckの読み込みと確保
    RW_csv mainDeckdata = new RW_csv( new File("assets/csv/main_deck.csv") );
    pl_deck = mainDeckdata.ReadCSV();

    // 相手のデッキを生成する 将来的には改善する あくまでダミーデータ
    for(int i = 0; i < ri_deck.length; i++) {
      ri_deck[i] = i%3 + 1;
    }

    player = new MyPlayer(pl_deck); // Player Modelを生成
    rival = new ComPlayer(ri_deck); // Rival Modelを生成
  }

  public JButton getResultBtn() { return resultBtn; }
  public JButton getDecideBtn() { return decideBtn; }
  public JButton getNextBtn() { return nextBtn; }
  public BasePlayer getPlayer() { return player; }
  public BasePlayer getRival() { return rival; }
  public JPanel getBattleCaption() { return battleCaption; }
  public Boolean getIsLocalhost() { return isLocalhost; }
}
