package com.main_game_2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import for background image
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.FrameController;
import com.asset_controller.*;

// Title's Model & View & Controller
final public class MainGamePanel2 extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel label;
  JButton server;
  JButton client;
  JTextField text;
  ImageButton samplebtn;
  ImageButton title;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  public MainGamePanel2(FrameController frameCont) {
    this.frameCont = frameCont;

    // Titleの背景画像を取得 例外が発生したらコンソールにエラー内容を表示する。
    try {
      backgroundImage = ImageIO.read(new File("assets/img/background/title.png"));
    } catch (Exception e) {
      e.printStackTrace();
      backgroundImage = null;
    }

    setLayout(new GridBagLayout());
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    setLayout(layout);

    label = new JLabel("ポート番号", JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 0.9d;
    layout.setConstraints(label, gbc);

    text = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 0;
    layout.setConstraints(text, gbc);

    server = new JButton("サーバーとして接続");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(server, gbc);

    client = new JButton("クライアントとして接続");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(client, gbc);

    title = new ImageButton(
      new String[] {
        "assets/img/edit_button/toTitleButton2.png", 
        "assets/img/edit_button/toTitleButton2_pressed.png", 
        "assets/img/edit_button/toTitleButton2_hover.png", 
        "assets/img/edit_button/toTitleButton2_unable.png"  
      }
    );
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0.5d;
    gbc.anchor = GridBagConstraints.CENTER;
    layout.setConstraints(title, gbc);

    server.addActionListener(this);
    client.addActionListener(this);
    title.addActionListener(this);

    add(label);
    add(server);
    add(client);
    add(text);
    add(title);
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
    if (e.getSource() == server) {

    }
    else if(e.getSource() == client){

    }
    else if(e.getSource() == title){
      frameCont.showTitle(this);
    }
  }

}

