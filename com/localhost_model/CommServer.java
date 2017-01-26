package com.localhost_model;

import java.net.*;
import java.io.*;

public class CommServer implements CommMethods {
  private ServerSocket serverS = null;
  private Socket clientS = null;
  private PrintWriter out = null;
  private BufferedReader in = null;
  private int port=0;

  public CommServer() {}
  public CommServer(int port) { open(port); }
  public CommServer(CommServer cs) { serverS=cs.getServerSocket(); open(cs.getPortNo()); }

  public ServerSocket getServerSocket() { return serverS; } 
  public int getPortNo() { return port; }

// サーバ用のソケット(通信路)のオープン
// サーバ用のソケットはクライアントからの接続待ち専用．
// ポート番号のみを指定する．
  public boolean open(int port){
    this.port=port;
    try{ 
      if (serverS == null) { serverS = new ServerSocket(port); }
    } catch (IOException e) {
      System.err.println("ポートにアクセスできません。");
      System.exit(1);
    }
    try{
      clientS = serverS.accept();
      out = new PrintWriter(clientS.getOutputStream(), true);
      in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
    } catch (IOException e) {
      System.err.println("Acceptに失敗しました。");
      System.exit(1);
    }
    return true;
  }

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
    } catch (SocketTimeoutException e){
      System.err.println("タイムアウトです．");
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
    } catch (SocketException e){
      System.err.println("タイムアウト時間を変更できません．");
      System.exit(1);
    }
    return to;
  }

  // ソケットのクローズ (通信終了)
  public void close(){
    try{
      in.close();  out.close();
      clientS.close();  serverS.close();
    } catch (IOException e) {
      System.err.println("ソケットのクローズに失敗しました。");
      System.exit(1);
    }
    in=null; out=null;
    clientS=null; serverS=null;
  }
}
