package com.localhost_model;

import java.net.*;
import java.io.*;

public class CommClient implements Communicate {
  Socket clientS = null;
  BufferedReader in = null;
  PrintWriter out = null;

  public static final int SEND = 0, RECEIVE = 1;
  private int mode = -1;

  public CommClient() {}
  public CommClient(String host,int port) { open(host,port); }

  public int getMode() { return mode; }
  public void setMode(int mode) { this.mode = mode; }

  // クライアントソケット(通信路)のオープン　
  // 接続先のホスト名とポート番号が必要
  public boolean open(String host,int port) {
    try {
      clientS = new Socket(InetAddress.getByName(host), port);
      in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
      out = new PrintWriter(clientS.getOutputStream(), true);
    } catch (UnknownHostException e) {
      System.err.println("ホストに接続できません。");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("IOコネクションを得られません。");
      System.exit(1);
    }
    return true;
  }

  public boolean open(int port) { return false; }

  // データ送信
  public boolean send(String msg){
    if (out == null) { return false; }
    out.println(msg);
    return true;
  }

  // データ受信
  public String recv(){
    String msg=null;
    if (in == null) { return null; }
    try{
      msg=in.readLine();
    } catch (SocketTimeoutException e) {
        return null;
    } catch (IOException e) {
      System.err.println("受信に失敗しました。");
      System.exit(1);
    }
    return msg;
  }

  // タイムアウトの設定
  public int setTimeout(int to){
    try{
      clientS.setSoTimeout(to);
    } catch (SocketException e) {
      System.err.println("タイムアウト時間を変更できません．");
      System.exit(1);
    }
    return to;
  }

  // ソケットのクローズ (通信終了)
  public void close(){
    try{
      in.close();  out.close();
      clientS.close();
    } catch (IOException e) {
        System.err.println("ソケットのクローズに失敗しました。");
        System.exit(1);
    }
    in=null; out=null;
    clientS=null;
  }
}
