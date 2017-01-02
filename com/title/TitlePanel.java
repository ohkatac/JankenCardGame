package com.title;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;

// Title's Model & View & Controller
final public class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JLabel label;
  JButton start;
  JButton deckEdit;

  public TitlePanel(FrameController frameCont) {
    this.frameCont = frameCont;

    setLayout(new GridBagLayout());
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel p = new JPanel();
    p.setLayout(layout);

    label = new JLabel("じゃんけんゲーム", JLabel.CENTER);
    label.setFont(new Font("MS 明朝", Font.PLAIN, 30));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1.0d;
    layout.setConstraints(label,gbc);

    start = new JButton("ゲームスタート");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 0.5d;
    layout.setConstraints(start, gbc);

    deckEdit = new JButton("デッキ編集");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weighty = 0.5d;
    layout.setConstraints(deckEdit, gbc);

    start.addActionListener(this);
    deckEdit.addActionListener(this);

    p.add(label);
    p.add(start);
    p.add(deckEdit);

    add(p);

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

