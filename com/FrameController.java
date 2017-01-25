package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import for Read and Write File
import java.io.File;

import com.deck_edit.*;
import com.game_select.GameSelectPanel;
import com.main_game.MainGamePanel;
import com.main_game_2.MainGamePanel2;
import com.result.*;
import com.title.*;
import com.explain.*;

// GameMode Controller : TitlePanel, DeckEditPanel, MainGamePanel, ResultPanel
// このController兼JFrameをMainで生成して、その上に画面を担うJPanelを載せていく
public final class FrameController extends JFrame{

    private TitlePanel titlePanel; 
    private DeckEditPanel editPanel; 
    private GameSelectPanel gameSelectPanel;
    private MainGamePanel gamePanel; 
    private MainGamePanel2 gamePanel2;
    private ResultPanel resultPanel; 
    private ExplainFrame explainFrame; 


    public FrameController() {

        // それぞれの画面のパネルを生成
        titlePanel = new TitlePanel(this);

        // 初めは必ずタイトル画面を開くのでコンストラクタで
        this.add(titlePanel);
        // JPanelを見える状態にする
        titlePanel.setVisible(true);

        // 画面サイズを決定、 後で変更も考慮
        this.setSize(800,800);
        // バツボタンを押せばjavaプログラムが終了するようにする。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void showTitle(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      titlePanel = new TitlePanel(this);
      // JPanelを追加
      this.add(titlePanel);
      // JPanelを見える状態にする
      this.titlePanel.setVisible(true);
    }

    public void showDeckEdit(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      editPanel = new DeckEditPanel(this);
      // JPanelを追加
      this.add(editPanel);
      // JPanelを見える状態にする
      this.editPanel.setVisible(true);
    }

    public void showGameSelect(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      gameSelectPanel = new GameSelectPanel(this);
      // JPanelを追加
      this.add(gameSelectPanel);
      // JPanelを見える状態にする
      this.gameSelectPanel.setVisible(true);
    }

    public void showMainGame(JPanel currentPanel, int[] ri_deck) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      gamePanel = new MainGamePanel(this, ri_deck);
      // JPanelを追加
      this.add(gamePanel);
      // JPanelを見える状態にする
      this.gamePanel.setVisible(true);
    }

    public void showMainGame(JPanel currentPanel, int[] ri_deck, int port, Boolean isServer) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      gamePanel = new MainGamePanel(this, ri_deck, port, isServer);
      // JPanelを追加
      this.add(gamePanel);
      // JPanelを見える状態にする
      this.gamePanel.setVisible(true);
    }

    public void showMainGame2(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      gamePanel2 = new MainGamePanel2(this);
      // JPanelを追加
      this.add(gamePanel2);
      // JPanelを見える状態にする
      this.gamePanel2.setVisible(true);
    }

    public void showResult(JPanel currentPanel, int myLife, int riLife) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      currentPanel = null;
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      resultPanel = new ResultPanel(this, myLife, riLife);
      // JPanelを追加
      this.add(resultPanel);
      // JPanelを見える状態にする
      this.resultPanel.setVisible(true);
    }

    public void showExplain() {
      explainFrame = new ExplainFrame();
    }
 
}
