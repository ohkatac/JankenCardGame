package com.localhost_model;

public interface Communicate {
  //.ポート開放
  public boolean open(int port);
  public boolean open(String host,int port);
  // データ送信
  public boolean send(String msg);
  // データ受信
  public String recv();
  // タイムアウトの設定
  public int setTimeout(int to);
  // ソケットのクローズ (通信終了)
  public void close();

  public int getMode();
  public void setMode(int mode);
}