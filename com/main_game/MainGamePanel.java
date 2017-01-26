/*
  MainGameのViewであるJPanelのクラス
  このJPanelが
MyFieldPanel myField;
MySidePanel mySide;
BattleFieldPanel battleField;
RivalFieldPanel rivalField;
RivalSidePanel rivalSide;
の5つのJPanelを持っており、これらをBorderLayoutでこのJPanelに配置している。

また、初めに作った画面遷移のフレームの性質上このクラスが次の画面へ行くためのメソッドを持っている。(このクラスがMainGameの中心)
*/

package com.main_game;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
// import for background image
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.FrameController;
import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;
import com.asset_controller.ImageButton;

// MainGame's View
final public class MainGamePanel extends JPanel {
  FrameController frameCont;

  MainGameModel gameModel;
  MainGameController gameController;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  // 手札置き場、デッキ置き場などのフィールドをJPanelとして定義、そのあとにBorderLayoutとして適用させていく。
  MyFieldPanel myField;
  MySidePanel mySide;
  BattleFieldPanel battleField;
  RivalFieldPanel rivalField;
  RivalSidePanel rivalSide;

  private int port;
  private Boolean isLocalhost;

  public MainGamePanel(FrameController frameCont, int[] ri_deck) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    gameModel = new MainGameModel(ri_deck);
    
    loadBackgroundImage();
    setMainGamePanel();

// MainGameの流れを制御するためのコントローラーを定義
    gameController = new MainGameController(gameModel, this);

  }

  public MainGamePanel(FrameController frameCont, int[] ri_deck, int port, Boolean isServer) {
    this.frameCont = frameCont;

    gameModel = new MainGameModel(ri_deck);

    loadBackgroundImage();
    setMainGamePanel();
    // Maingameの流れを制御するためのControllerを生成
    gameController = new MainGameController(gameModel, this, port, isServer);

  }

  private void loadBackgroundImage() {
    // MainGameの背景画像を取得 例外が発生したらコンソールにエラー内容を表示する。
    try {
      backgroundImage = ImageIO.read(new File("assets/img/background/maingame.png"));
    } catch (Exception e) {
      e.printStackTrace();
      backgroundImage = null;
    }
  }

  private void setMainGamePanel() {
    this.setLayout(new BorderLayout()); // それぞれのFieldを再現するためにBorderLayoutを使う

// 5つのフィールドをつかさどるViewであるJPanelのインスタンスの生成
    myField = new MyFieldPanel(gameModel, this);
    mySide = new MySidePanel(gameModel);
    battleField = new BattleFieldPanel(gameModel);
    rivalField = new RivalFieldPanel(gameModel, this);
    rivalSide = new RivalSidePanel(gameModel);

    this.add(myField, BorderLayout.SOUTH);
    this.add(mySide, BorderLayout.EAST);
    this.add(battleField, BorderLayout.CENTER);
    this.add(rivalField, BorderLayout.NORTH);
    this.add(rivalSide, BorderLayout.WEST);
  }

  // paintComponentによりJPanelを背景画像で上塗りする処理
  // Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;

    if(backgroundImage != null) {
      g2.drawImage(backgroundImage, 0, 0, this);
    }
  }

  public MyFieldPanel getMyField() { return myField;}
  public MySidePanel getMySide() { return mySide; }
  public BattleFieldPanel getBattleField() { return battleField; }
  public RivalFieldPanel getRivalField() { return rivalField; }
  public RivalSidePanel getRivalSide() { return rivalSide; }

// JPanelをリザルト画面に切り替えるメソッド
  public void GotoResult(){
    frameCont.showResult(this, gameModel.getPlayer().getLife(), gameModel.getRival().getLife() );
  }
}
