package com.localhost_controller;

import com.localhost_controller.localhost_model.CommClient;

public class Client {
  CommClient cl;
  String signal;

  public Client(int port) {
    cl = new CommClient("localhost", port);
    signal = "";
  }

  public void sendSignal(String sig){
    cl.send(sig);
  }

  public String getSignal() {
    String s = cl.recv();
    if(s == null) s = "";
    return s;
  }
}
