package com.localhost_controller;

import com.localhost_controller.localhost_model.CommServer.java;

public class Server {
  String signal;
  public Server(int port) {
    // CommServerオブジェクトのポート番号を指定して生成
    CommServer sv = new CommServer(port);
  }

  public void sendSignal() {
    sv.send(signal);
  }

  public void getSignal() {
    String accept = "";
    accept = sv.recv();
    if(accept == null) accept = "";
    return accept;
  }
}
