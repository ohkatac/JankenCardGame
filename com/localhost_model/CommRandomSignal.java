package com.localhost_model;

import java.util.Random;
import java.awt.event.*;
import javax.swing.Timer;

import com.main_game.main_game_controller.BasePhase;
import com.main_game.main_game_model.player_model.*;

public class ComSignal implements ActionListener, CommMethods {
  private BasePhase signalTo;
  private Timer timer;
  private BasePlayer rival;

  public ComSignal(BasePhase signalTo, BasePlayer rival) {
    super();
    this.signalTo = signalTo;
    this.rival = rival;
    timer = new Timer(1000, this);
  }

  public void actionPerformed(ActionEvent e) {
    Random rnd = new Random();
    int index;
    String data = "";

    // シグナルを送る処理
    if( signalTo.getId() == BasePhase.FIRST) {
      data = String.valueOf( rnd.nextInt(3) + 1 );
    }

    else if( signalTo.getId() == BasePhase.RIVAL) {
      if(rnd.nextBoolean() && rival.getPoppingCard() != null ) {
        data = "decide";
      }
      else {
        index = rnd.nextInt(rival.getHands().size());
        data = String.valueOf(index);
      }
    }

    signalTo.signalAction(data);
  }

  public void startSignal() {
    timer.start();
  }

  public void stopSignal() {
    timer.stop();
  }
}