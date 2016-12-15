package com.title;

// Title's Model & View & Controller
final class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton start;
  JButton deckEdit;

  public TitlePanel(FrameController frameCont) {
    this.frameCont = frameCont;
    setLayout(new FlowLayout());
    start = new JButton("ゲームスタート");
    deckEdit = new JButton("デッキ編集");

    start.addActionListener(this);
    deckEdit.addActionListener(this);

    this.add(start);
    this.add(deckEdit);
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