package com.main_game;

// MainGame's Model & View & Controller
final class MainGamePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton result;

  public MainGamePanel(FrameController frameCont) {
    this.frameCont = frameCont;
    setLayout(new FlowLayout());

    result = new JButton("Resultへ進む");
    result.addActionListener(this);
    this.add(result);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == result) {
      frameCont.showResult(this);
    }
  }

}