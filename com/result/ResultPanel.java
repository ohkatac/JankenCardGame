package com.result;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// Result's Model & View & Controller
final public class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton title;

  public ResultPanel(FrameController frameCont, int my_life, int ri_life) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく

    title = new JButton("タイトルへ進む");
    title.addActionListener(this);

    this.add(title);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showTitle(this);
    }
  }
}
