package com.deck_edit;

// DeckEdit's Model & View & Controller
final class DeckEditPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton end;

  public DeckEditPanel(FrameController frameCont) {
    this.frameCont = frameCont;
    setLayout(new FlowLayout());

    end = new JButton("タイトルへ進む");

    end.addActionListener(this);

    this.add(end);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == end) {
      frameCont.showTitle(this);
    }
  }
}