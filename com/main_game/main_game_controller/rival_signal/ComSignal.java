package com.main_game.main_game_controller.rival_signal;

import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

import com.main_game.main_game_controller.BasePhase;
import com.main_game.main_game_model.player_model.*;

public class ComSignal implements ActionListener{
  private BasePhase signalTo;
  private Timer timer;
  private BasePlayer rival;

  public ComSignal(BasePhase signalTo, BasePlayer rival) {
    this.signalTo = signalTo;
    this.rival = rival;
    timer = new Timer(1000, this);
  }

  public void actionPerformed(ActionEvent e) {
    // シグナルを送る処理
    Random rnd = new Random();
    if( signalTo.getId() == BasePhase.RIVAL) {
      signalTo.signalAction(rnd.nextInt( rival.getHands().size() ));
    // System.out.println("jjj");
    }
    else if( signalTo.getId() == BasePhase.FIRST) {
      signalTo.signalAction(rnd.nextInt(3) + 1);    }
  }

  public void startSignal() {
    timer.start();
  }

  public void destroySignal() {
    timer.stop();
  }
}