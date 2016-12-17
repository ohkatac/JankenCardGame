package com.main_game.main_game_controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.MainGamePanel;

//Controller
final public class MainGameController implements ActionListener {
  MainGameModel model;
  MainGamePanel panel;
  JButton resultBtn;

  public MainGameController(MainGameModel model, MainGamePanel panel) 
  {
    this.model = model;
    this.panel = panel;

    resultBtn = model.getResultBtn();

    panel.add(resultBtn);
    resultBtn.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == model.getResultBtn()) {
      // リザルト画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      panel.GotoResult();
    }
  }
}