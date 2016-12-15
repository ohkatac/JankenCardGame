package com.result;

// Result's Model & View & Controller
final class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton title;

  public ResultPanel(FrameController frameCont) {
    this.frameCont = frameCont;
    setLayout(new FlowLayout());

    title = new JButton("タイトルへ進む");
    title.addActionListener(this);

    this.add(title);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      frameCont.showTitle(this);
    }
  }
}
