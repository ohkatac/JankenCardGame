package com.result;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// Result's Model & View & Controller
final public class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel result;
  JLabel life;
  JLabel card;
  JButton title;

  public ResultPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;

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

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      frameCont.showTitle(this);
    }
  }
}
