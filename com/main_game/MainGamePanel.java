package com.main_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;
import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_controller.MainGameController;

// MainGame's View
final public class MainGamePanel extends JPanel {
  FrameController frameCont;

  MainGameModel gameModel;
  MainGameController gameController;

  JButton resultBtn = null;

  public MainGamePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく

    gameModel = new MainGameModel();
    gameController = new MainGameController(gameModel, this);

    resultBtn = gameModel.getResultBtn();
    this.add(resultBtn);
  }

// JPanelをリザルト画面に切り替えるメソッド
  public void GotoResult(){
    frameCont.showResult(this);
  }
}