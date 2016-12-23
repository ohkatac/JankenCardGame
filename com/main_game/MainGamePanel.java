package com.main_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// MainGame's Model & View & Controller
final public class MainGamePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton result;

  public MainGamePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく

    result = new JButton("Resultへ進む");
    result.addActionListener(this);
    this.add(result);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == result) {
      // リザルト画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
      // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
      frameCont.showResult(this);
    }
  }

}
