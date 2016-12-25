package com.title;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;
import com.asset_controller.ImageButton;

// Title's Model & View & Controller
final public class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton start;
  JButton deckEdit;
  ImageButton samplebtn;

  public TitlePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく
    start = new JButton("ゲームスタート");
    deckEdit = new JButton("デッキ編集");

    samplebtn = new ImageButton(
      new String[] {
        "assets/img/button/pinkButton.png",
        "assets/img/button/grayButton.png",
        "assets/img/button/greenButton.png",
        "assets/img/button/grayButton.png"
      });
    this.add(samplebtn);

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