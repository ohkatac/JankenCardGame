package com.main_game.main_game_model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;


// MainGame's Model
final public class MainGameModel{
  int[] data;
  JButton resultBtn;

  public MainGameModel() {
    resultBtn = new JButton("Go Result");
  }

  public JButton getResultBtn() { return resultBtn; }
}
