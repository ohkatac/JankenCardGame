package com.main_game.main_game_controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.MainGamePanel;

//Controller, ゲームのメインの流れはここに書いていく 主な流れはMainGameModel 
//Classであるmodelを受け取り、その中に入っているモデルたちを取り出してPanelに張り付けたりなどして扱っていく

final public class MainGameController implements ActionListener {
  MainGameModel model;
  MainGamePanel panel;
  JButton resultBtn;

  public MainGameController(MainGameModel model, MainGamePanel panel) 
  {
    this.model = model; // モデルを設定
    this.panel = panel;

    resultBtn = model.getResultBtn();

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