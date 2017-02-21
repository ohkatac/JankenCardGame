package com.localhost_model;

import java.lang.Thread;

import com.main_game.main_game_controller.BasePhase;

public class RunnableComm implements Runnable {
  public static final int SEND = 1, REC = 2;
  private Communicate comm;
  private BasePhase callbackTo;
  private int mode = 0;
  private String sendMessage = "";
  private String recMessage = "";

  private Boolean isAlive;

  public RunnableComm(Communicate comm, BasePhase basePhase) {
    this.comm = comm;
    callbackTo = basePhase;
    isAlive = true;
  }

  public void setMode(int mode) { this.mode = mode; }
  public int getMode() { return mode; }

  public void setSendMessage(String data) { this.sendMessage = data; }

  public String getRecMessage() { return recMessage; }

  public void setIsAlive(Boolean isAlive) { this.isAlive = isAlive; }

  // OVerride
  public void run() {
    if(mode == SEND) {
      comm.send(sendMessage);
    }
    else if(mode == REC) {
      while(true) {
        recMessage = comm.recv();
        if(!isAlive) break;
        sendMessage = "ack";
        comm.send(sendMessage);
        callbackTo.receiveSignalAction(recMessage);
      }
    }
  }
}