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
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく
    
    JLabel label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
    label.setSize(300,100);
    label.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,12));
  
/*
  JPanel p = new JPanel();
  p.setLayout(null);
*/
  start = new JButton("ゲームスタート");
  start.setBounds(100,90,100,20);
  start.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,8));

  deckEdit = new JButton("デッキ編集");
  deckEdit.setBounds(100,115,100,20);
  deckEdit.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,8));
  /*
  p.add(start);
  p.add(deckEdit);
  */
  add(label);
  /*
  add(p,BorderLayout.CENTER);
  this.setVisible(true);
  */
    start.addActionListener(this);
    deckEdit.addActionListener(this);

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

