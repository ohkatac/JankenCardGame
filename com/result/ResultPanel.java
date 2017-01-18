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
  JLabel result_win;
  JLabel result_lose;
  JLabel m_life;
  JLabel r_life;
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
    
    if(my_life>ri_life){
      result_win = new JLabel("YOU WIN!!", JLabel.CENTER);
      result_win.setFont(new Font("Arial", Font.ITALIC, 70));
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weighty = 0.9d;
      gbc.gridwidth = 3; 
      layout.setConstraints(result_win, gbc);
      add(result_win);
    }else{
      result_lose = new JLabel("YOU LOSE...", JLabel.CENTER);
      result_lose.setFont(new Font("Arial", Font.ITALIC, 70));
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weighty = 0.9d;
      gbc.gridwidth = 3; 
      layout.setConstraints(result_lose, gbc);
      add(result_lose);
    }

    m_life = new JLabel("あなたの残りライフ : " + my_life, JLabel.CENTER);
    m_life.setFont(new Font("MS 明朝", Font.PLAIN, 20));
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(m_life, gbc);

    r_life = new JLabel("相手の残りライフ : " + ri_life, JLabel.CENTER);
    r_life.setFont(new Font("MS 明朝", Font.PLAIN, 20));
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(r_life, gbc);

    title = new JButton("タイトルに戻る");
    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.weighty = 0.5d;
    gbc.anchor = GridBagConstraints.CENTER;
    layout.setConstraints(title, gbc);

    title.addActionListener(this);


    add(r_life);
    add(m_life);
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
