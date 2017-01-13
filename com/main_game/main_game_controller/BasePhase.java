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

// 拡張性を高めるため固定のフェイズのみではなくBasePhaseに定義しておく
  public int getId() { return id; }

// 拡張性を高めるため固定のフェイズのみではなくBasePhaseに定義しておく
  public void startThisPhase() {}

// 拡張性を高めるため固定のフェイズのみではなくBasePhaseに定義しておく 
  public void endThisPhase() {}

// 拡張性を高めるため固定のフェイズのみではなくBasePhaseに定義しておく
  public void signalAction(String data) {}
}