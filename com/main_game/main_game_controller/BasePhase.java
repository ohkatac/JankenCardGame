package com.main_game.main_game_controller;

public class BasePhase {
  public static final int PLAYER = 0;
  public static final int RIVAL = 1;
  public static final int BATTLE = 2;
  public static final int FIRST = 3;
  protected int id;

  public BasePhase(int id) {
    this.id = id;
  }

  public int getId() { return id; }

  public void startThisPhase() {}

  public void endThisPhase() {}

  public void signalAction(int index) {}
}