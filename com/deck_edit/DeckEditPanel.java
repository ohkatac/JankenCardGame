package com.deck_edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// DeckEdit's Model & View & Controller
final public class DeckEditPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton end;

  public DeckEditPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく

    end = new JButton("タイトルへ進む");

    end.addActionListener(this);

    this.add(end);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == end) {
      // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showTitle(this);
    }
  }
}