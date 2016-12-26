package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import for Read and Write File
import java.io.File;

import com.deck_edit.*;
import com.main_game.MainGamePanel;
import com.result.*;
import com.title.*;

// GameMode Controller : TitlePanel, DeckEditPanel, MainGamePanel, ResultPanel
// このController兼JFrameをMainで生成して、その上に画面を担うJPanelを載せていく
public final class FrameController extends JFrame{

    private TitlePanel titlePanel; // タイトル画面のJPanel
    private DeckEditPanel editPanel; // 編集画面のJPanel
    private MainGamePanel gamePanel; // メインゲーム画面のJPanel
    private ResultPanel resultPanel; // リザルト画面のJPanel


    public FrameController() {

        // それぞれの画面のパネルを生成
        titlePanel = new TitlePanel(this);

        // 初めは必ずタイトル画面を開くのでコンストラクタで
        this.add(titlePanel);
        // JPanelを見える状態にする
        titlePanel.setVisible(true);

        // 画面サイズを決定、 後で変更も考慮
        this.setSize(800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void showTitle(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
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
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      editPanel = new DeckEditPanel(this);
      // JPanelを追加
      this.add(editPanel);
      // JPanelを見える状態にする
      this.editPanel.setVisible(true);
    }

    public void showMainGame(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      gamePanel = new MainGamePanel(this);
      // JPanelを追加
      this.add(gamePanel);
      // JPanelを見える状態にする
      this.gamePanel.setVisible(true);
    }

    public void showResult(JPanel currentPanel) {
      // 現在JFrame上に表示してあるJPanelを削除
      currentPanel.setVisible(false);
      // 画面のJPanelのインスタンスを新しく生成(JPanelを使いまわすと状態が残ってしまい初期化が面倒なので一回破棄してから新しく生成することにした。)
      resultPanel = new ResultPanel(this);
      // JPanelを追加
      this.add(resultPanel);
      // JPanelを見える状態にする
      this.resultPanel.setVisible(true);
    }

}