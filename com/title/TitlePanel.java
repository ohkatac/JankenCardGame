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

  public TitlePanel(FrameController frameCont) {
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
  /*
  setLayout(new GridBagLayout());

  GridBagLayout layout = new GridBagLayout();
  JPanel p = new JPanel();
  p.setLayout(layout);

  GridBagConstraints gbc = new GridBagConstraints();

  JLabel label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
  gbc.gridx = 0;
  gbc.gridy = 0;
  gbc.gridheight = 2;
  gbc.weightx = 1.0;
  gbc.weighty = 1.0;
  layout.setConstraints(label,gbc);

  start = new JButton("ゲームスタート");
  gbc.gridx = 1;
  gbc.gridy = 0;
  gbc.gridheight = 1;
  gbc.weightx = 1.0;
  gbc.weighty = 1.0;
  layout.setConstraints(start, gbc);

  deckEdit = new JButton("デッキ編集");
  gbc.gridx = 1;
  gbc.gridy = 1;
  gbc.weightx = 1.0;
  gbc.weighty = 1.0;
  layout.setConstraints(deckEdit, gbc);

  start.addActionListener(this);
  deckEdit.addActionListener(this);

  this.add(label);
  this.add(start);
  this.add(deckEdit);
  */
  /*
  JLabel label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
  label.setSize(300,100);
  label.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,12));

  JPanel p = new JPanel();
  p.setLayout(null);

  JButton start = new JButton("Start");
  start.setBounds(100,90,100,20);
  start.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,8));

  JButton deckEdit = new JButton("Edit");
  deckEdit.setBounds(100,115,100,20);
  deckEdit.setFont(new Font("Times New Roman",Font.BOLD | Font.ITALIC,8));

  p.add(start);
  p.add(deckEdit);

  start.addActionListener(this);
  deckEdit.addActionListener(this);

  add(label);
  add(p,BorderLayout.CENTER);

  this.setVisible(true);

  */

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start){
      frameCont.showMainGame(this);
    }
    else if (e.getSource() == deckEdit) {
      frameCont.showDeckEdit(this);
    }
  }
}

