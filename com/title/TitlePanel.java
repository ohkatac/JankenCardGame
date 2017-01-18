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
  JLabel label;
  JButton start_1;
  JButton start_2;
  JButton deckEdit;
  JButton explain;
  ImageButton samplebtn;
  RW_csv mainDeck = null;
  Boolean gameBtnFlag;

  public TitlePanel(FrameController frameCont) {
    this.frameCont = frameCont;

    setLayout(new GridBagLayout());
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    setLayout(layout);

    label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
    label.setFont(new Font("MS 明朝", Font.PLAIN, 30));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1.0d;
    layout.setConstraints(label, gbc);
    
    start_1 = new JButton("1人で遊ぶ");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(start_1, gbc);

    start_2 = new JButton("2人で遊ぶ");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(start_2, gbc);

    deckEdit = new JButton("デッキ編集");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0.2d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(deckEdit, gbc);

    explain = new JButton("遊び方説明");
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weighty = 0.4d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(explain, gbc);

// デッキデータのcsvファイルの中身を取り出す。
    mainDeck = new RW_csv( new File("assets/csv/main_deck.csv") );
    int[] checkData = mainDeck.ReadCSV();

// デッキデータが不正ならゲーム画面に進めないようにする(gameButtonを使えなくする)
    for(int i=0; i<=39; i++){
      if(checkData[i] < 1 || checkData[i] > 7 || checkData.length != 40) start_1.setEnabled(false); 
      if(checkData[i] < 1 || checkData[i] > 7 || checkData.length != 40) start_2.setEnabled(false); 
    }

    start_1.addActionListener(this);
    start_2.addActionListener(this);
    deckEdit.addActionListener(this);
    explain.addActionListener(this);

    add(label);
    add(start_1);
    add(start_2);
    add(deckEdit);
    add(explain);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start_1) {
      frameCont.showMainGame(this);
    }
    else if(e.getSource() == start_2){ //通信対戦用のボタン
    }
    else if (e.getSource() == deckEdit) {
      frameCont.showDeckEdit(this);
    }
    else if (e.getSource() == explain) {
      frameCont.showExplain();
    }
  }

}

