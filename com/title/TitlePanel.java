package com.title;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;

import com.FrameController;
import com.asset_controller.*;

// Title's Model & View & Controller
final public class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton start;
  JButton deckEdit;
  ImageButton samplebtn;
  RW_csv mainDeck = null;
  Boolean gameBtnFlag;

  public TitlePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく

    start = new JButton("ゲームスタート");
    deckEdit = new JButton("デッキ編集");

// デッキデータのcsvファイルの中身を取り出す。
    mainDeck = new RW_csv( new File("assets/csv/main_deck.csv") );
    int[] checkData = mainDeck.ReadCSV();

// デッキデータが不正ならゲーム画面に進めないようにする(gameButtonを使えなくする)
    if(checkData == null || checkData.length != 20) start.setEnabled(false); 

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