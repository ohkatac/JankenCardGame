package com.title;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// Title's Model & View & Controller
final public class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton start;
  JButton deckEdit;

  public TitlePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;

    setLayout(new GridBagLayout());
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    //JPanel p = new JPanel();
    //p.setLayout(layout);

    JLabel label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
    gbc.gridx = 0;
    gbc.gridy = 0;
    //gbc.gridheight = 1;
    //gbc.weightx = 1.0;
    //gbc.weighty = 1.0;
    //layout.setConstraints(label,gbc);

    start = new JButton("ゲームスタート");
    gbc.gridx = 1;
    gbc.gridy = 0;
    //gbc.gridheight = 1;
    //gbc.weightx = 1.0;
    //gbc.weighty = 1.0;
    //layout.setConstraints(start, gbc);

    deckEdit = new JButton("デッキ編集");
    gbc.gridx = 1;
    gbc.gridy = 1;
    //gbc.weightx = 1.0;
    //gbc.weighty = 1.0;
    //layout.setConstraints(deckEdit, gbc);

    start.addActionListener(this);
    deckEdit.addActionListener(this);

    this.add(label);
    this.add(start);
    this.add(deckEdit);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start){
      // メインゲーム画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showMainGame(this);
    }
    else if (e.getSource() == deckEdit) {
      // デッキ編集画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showDeckEdit(this);
    }
  }
}