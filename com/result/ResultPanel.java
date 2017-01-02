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

    setLayout(new GridBagLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel p = new JPanel();
    p.setLayout(layout);

    //JLabel resultのところに場合分けをしてYou win, You loseみたいにできればなお良い

    result = new JLabel("結果発表", JLabel.CENTER);
    result.setFont(new Font("MS 明朝", Font.PLAIN, 30));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 0.9d; 
    layout.setConstraints(result, gbc);

    life = new JLabel("残りライフ : ", JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.3d;
    layout.setConstraints(life, gbc);

    card = new JLabel("残り枚数 : ", JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0.3d;
    layout.setConstraints(card, gbc);

    title = new JButton("タイトルへ進む");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0.3d;
    gbc.anchor = GridBagConstraints.EAST;
    layout.setConstraints(title, gbc);

    title.addActionListener(this);

    p.add(result);
    p.add(life);
    p.add(card);
    p.add(title);

    add(p);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showTitle(this);
    }
  }
}
