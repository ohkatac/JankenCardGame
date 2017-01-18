/*
  右側にあるnextBtnなどが配置されているJPanel
*/

package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;

public class MySidePanel extends JPanel {
  MainGameModel model;
  JButton resultBtn = null;
  JButton decideBtn = null;
  JButton nextBtn = null;

  public MySidePanel(MainGameModel model) {
    super();
    this.model = model;
    this.setOpaque(false); // 背景画像を表示するためにこのJPanelそのものを透明化する。

    this.setLayout( new GridLayout(8, 1) );

    resultBtn = model.getResultBtn();
    this.add(resultBtn);
    decideBtn = model.getDecideBtn();
    this.add(decideBtn);
    nextBtn = model.getNextBtn();
    this.add(nextBtn);
  }
}