package com.result;

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

// Result's Model & View & Controller
final public class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel result;
  JLabel life;
  JLabel card;
  JButton title;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  public ResultPanel(FrameController frameCont, int my_life, int ri_life) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;

    // Resultの背景画像を取得 例外が発生したらコンソールにエラー内容を表示する。
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

    //JLabel resultのところに場合分けをしてYou win, You loseみたいにできればなお良い
    //結果のデータをどうやって表示するかはこれから。

    result = new JLabel("結果発表", JLabel.CENTER);
    result.setFont(new Font("MS 明朝", Font.PLAIN, 30));
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weighty = 0.9d;
    gbc.gridwidth = 5; 
    layout.setConstraints(result, gbc);

    life = new JLabel("残りライフ : ", JLabel.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 0.3d;
    layout.setConstraints(life, gbc);

    card = new JLabel("残り枚数 : ", JLabel.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weighty = 0.3d;
    layout.setConstraints(card, gbc);

    title = new JButton("タイトルへ進む");
    gbc.gridx = 4;
    gbc.gridy = 3;
    gbc.weighty = 0.3d;
    layout.setConstraints(title, gbc);

    title.addActionListener(this);

    add(result);
    add(life);
    add(card);
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
    if (e.getSource() == title) {
      frameCont.showTitle(this);
    }
  }
}
