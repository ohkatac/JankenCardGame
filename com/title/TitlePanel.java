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
  JButton start;
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
    gbc.weighty = 0.9d;
    layout.setConstraints(label, gbc);
    
    start = new JButton("ゲームスタート");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.3d;
    gbc.anchor = GridBagConstraints.SOUTH;
    layout.setConstraints(start, gbc);

    deckEdit = new JButton("デッキ編集");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0.3d;
    gbc.anchor = GridBagConstraints.CENTER;
    layout.setConstraints(deckEdit, gbc);

    explain = new JButton("遊び方説明");
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weighty = 0.3d;
    gbc.anchor = GridBagConstraints.NORTH;
    layout.setConstraints(explain, gbc);

// デッキデータのcsvファイルの中身を取り出す。
    mainDeck = new RW_csv( new File("assets/csv/main_deck.csv") );
    int[] checkData = mainDeck.ReadCSV();

// デッキデータが不正ならゲーム画面に進めないようにする(gameButtonを使えなくする)
    if(checkData == null || checkData.length != 20) start.setEnabled(false); 

    start.addActionListener(this);
    deckEdit.addActionListener(this);
    explain.addActionListener(this);

    add(label);
    add(start);
    add(deckEdit);
    add(explain);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start) {
      frameCont.showMainGame(this);
    }
    else if (e.getSource() == deckEdit) {
      frameCont.showDeckEdit(this);
    }
    else if (e.getSource() == explain) {

    }
  }

}

