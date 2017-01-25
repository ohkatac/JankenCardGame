package com.title;

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
final public class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel label;
  JButton start_1;
  JButton start_2;
  JButton deckEdit;
  JButton explain;
  ImageButton samplebtn;
  RW_csv mainDeck = null;
  Boolean gameBtnFlag;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  public TitlePanel(FrameController frameCont) {
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

    label = new JLabel("Janken Fight!!", JLabel.CENTER);
    label.setFont(new Font("Arial", Font.ITALIC, 70));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1.0d;
    layout.setConstraints(label, gbc);
    
    start_1 = new JButton("1人で遊ぶ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(start_1, gbc);

    start_2 = new JButton("2人で遊ぶ");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(start_2, gbc);

    deckEdit = new JButton("デッキ編集");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(deckEdit, gbc);

    explain = new JButton("遊び方説明");
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weighty = 0.4d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(explain, gbc);

// デッキデータのcsvファイルの中身を取り出す。
    mainDeck = new RW_csv( new File("assets/csv/main_deck.csv") );
    int[] checkData = mainDeck.ReadCSV();

// デッキデータが不正ならゲーム画面に進めないようにする(gameButtonを使えなくする)
    for(int i=0; i<=39; i++){
      if(checkData[i] < 1 || checkData[i] > 7 || checkData.length != 40) start_1.setEnabled(false); 
      if(checkData[i] < 1 || checkData[i] > 7 || checkData.length != 40) start_2.setEnabled(false); 
    }

    start_1.addActionListener(this);
    start_2.addActionListener(this);
    deckEdit.addActionListener(this);
    explain.addActionListener(this);

    add(label);
    add(start_1);
    add(start_2);
    add(deckEdit);
    add(explain);
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
    if (e.getSource() == start_1) {
      frameCont.showMainGame(this);
    }
    else if(e.getSource() == start_2){ 
      frameCont.showMainGame2(this);
    }
    else if (e.getSource() == deckEdit) {
      frameCont.showDeckEdit(this);
    }
    else if (e.getSource() == explain) {
      frameCont.showExplain();
    }
  }

}

