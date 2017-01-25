package com.game_select;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
// import for background image
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.FrameController;

// GameSelect's Model & View & Controller
final public class GameSelectPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JPanel[] data;
  JLabel comBattleLabel;
  JButton comBattleBtn;

  JLabel localLabel;
  JRadioButton[] isServer;
  JLabel portNumLabel;
  JTextField portNum;
  JButton localBattleBtn;

  String portTxt;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  public GameSelectPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる

    // Resultの背景画像を取得 例外が発生したらコンソールにエラー内容を表示する。
    try {
      backgroundImage = ImageIO.read(new File("assets/img/background/select_port.png"));
    } catch (Exception e) {
      e.printStackTrace();
      backgroundImage = null;
    }

    this.frameCont = frameCont;
    data = new JPanel[7];
    comBattleLabel = new JLabel("コンピュータ対戦");

    comBattleBtn = new JButton("コンピュータ対戦");
    comBattleBtn.addActionListener(this);

    localLabel = new JLabel("Localhostネットワーク対戦");

    isServer = new JRadioButton[2];
    isServer[0] = new JRadioButton("サーバーとして接続");
    isServer[1] = new JRadioButton("クライアントとして接続");

    portNumLabel = new JLabel("ポート番号");
    portNum = new JTextField("");
    portTxt = portNum.getText();

    localBattleBtn = new JButton("ネットワーク対戦");
    localBattleBtn.addActionListener(this);

    this.setLayout(new GridLayout(5, 1));
    for(int i = 0; i < data.length; i++) {
      data[i] = new JPanel();
      data[i].setOpaque(false);
      data[i].setLayout(new FlowLayout());
    }

    data[0].add(comBattleLabel);
    data[1].add(comBattleBtn);
    data[2].add(localLabel);
    data[3].add(isServer[0]);
    data[3].add(isServer[0]);
    data[4].add(portNumLabel);
    data[4].add(portNum);
    data[5].add(localBattleBtn);

    for(int i = 0; i < data.length; i++) {
      this.add(data[i]);
    }
  }

  // paintComponentによりJPanelを背景画像で上塗りする処理
  // Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;

    if(backgroundImage != null) {
      g2.drawImage(backgroundImage, 0, 0, this);
    }
  }

  public void actionPerformed(ActionEvent e) {
    if( e.getSource() == comBattleBtn ) {
      int[] ri_deck = new int[40];
      for(int i = 0; i < ri_deck.length; i++) {
        Random rnd = new Random();
        ri_deck[i] = rnd.nextInt(3)+1;
      }
      frameCont.showMainGame(this, ri_deck);
    }
    if( e.getSource() == localBattleBtn ) { // ネットワーク対戦用の処理
      // frameCont.showMainGame(this, ri_deck, port, false);

      // frameCont.showMainGame(this, ri_deck, port, false);
    }
  }
}
