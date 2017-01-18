package com.result;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// Result's Model & View & Controller
final public class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel result_win;
  JLabel result_lose;
  JLabel life;
  JLabel card;
  JButton title;

  public ResultPanel(FrameController frameCont, int my_life, int ri_life) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;

    setLayout(new GridBagLayout()); 
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    setLayout(layout);
    
    if(my_life>ri_life){
      result_win = new JLabel("YOU WIN!!", JLabel.CENTER);
      result_win.setFont(new Font("Arial", Font.PLAIN, 30));
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weighty = 0.9d;
      gbc.gridwidth = 3; 
      layout.setConstraints(result_win, gbc);
      add(result_win);
    }else{
      result_lose = new JLabel("YOU LOSE...", JLabel.CENTER);
      result_lose.setFont(new Font("Arial", Font.PLAIN, 30));
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weighty = 0.9d;
      gbc.gridwidth = 3; 
      layout.setConstraints(result_lose, gbc);
      add(result_lose);
    }

    life = new JLabel("あなたの残りライフ : " + my_life, JLabel.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(life, gbc);

    card = new JLabel("相手の残りライフ : " + ri_life, JLabel.CENTER);
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(card, gbc);

    title = new JButton("タイトルへ進む");
    gbc.gridx = 2;
    gbc.gridy = 3;
    gbc.weighty = 0.5d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(title, gbc);

    title.addActionListener(this);

    add(life);
    add(card);
    add(title);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      frameCont.showTitle(this);
    }
  }
}
