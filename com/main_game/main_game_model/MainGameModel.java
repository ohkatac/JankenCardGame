package com.main_game.main_game_model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_controller.MainGameController;


// MainGame's Model
final public class MainGameModel implements ActionListener {
  int[] data;
  JButton resultBtn;

  public MainGameModel() {
    resultBtn = new JButton("Go Result");
    
  }

  public JButton getResultBtn() { return resultBtn; }
}
