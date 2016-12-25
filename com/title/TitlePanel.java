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
  ImageButton samplebtn;

  public TitlePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    setLayout(new FlowLayout()); // とりあえず一番単純なFlowLayout()に設定。 後で変更するのも視野に入れておく
    // ImageIcon icon1 = new ImageIcon("./assets/img/All.png");
    start = new JButton("ゲームスタート");
    deckEdit = new JButton("デッキ編集");

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

class ImageButton extends JButton implements MouseListener {
  private javax.swing.ImageIcon image; // 画像ファイルを扱うためのクラスの宣言
  int w, h; // 画像の横幅、縦幅を格納する変数
  private String path, pressPath, hoverPath, disablePath;
  // 通常、押された状態、覆いかぶさった状態、無効状態のボタンの画像のパス。

  public ImageButton(String[] imagePath) {
    // それぞれのPathを格納
    path = imagePath[0];
    pressPath = imagePath[1];
    hoverPath = imagePath[2];
    disablePath = imagePath[3];

    this.image = new javax.swing.ImageIcon(this.path); // 表示画像を通常状態に設定
    setOpaque(false); // JButton panelの背景を透明にする。
    this.setBorderPainted(false); // 枠線をなしにする。
    this.w = image.getIconWidth(); // 画像の横幅を格納。
    this.h = image.getIconHeight(); // 画像の縦幅を格納。
    setPreferredSize(new Dimension(w, h) ); // ボタンのPanelのサイズを決定(JButtonはJPanelを継承しているため)

    this.addMouseListener(this); // MouseListenerに追加
  }

// ボタンの描画
  public void paintComponent(Graphics g) {
    g.drawImage(image.getImage(), 0, 0, w, h, null);
  }

// ボタンの機能を有効化するメソッド
  public void Enebled() {
    this.setEnabled(true);
    image = new javax.swing.ImageIcon(path);
    repaint();
  }

 // ボタンの機能を無効化するメソッド
  public void Disabled() {
    this.setEnabled(false);
    image = new javax.swing.ImageIcon(disablePath);
    repaint();
  }

// ボタンの画像変化処理の記述
  public void mouseClicked(MouseEvent e) { }
  public void mousePressed(MouseEvent e) {
    image = new javax.swing.ImageIcon(pressPath);
    repaint();
  }
  public void mouseReleased(MouseEvent e) {
    if(image.toString() != path) image = new javax.swing.ImageIcon(hoverPath);
    repaint();
  }
  public void mouseEntered(MouseEvent e) {
    image = new javax.swing.ImageIcon(hoverPath);
    repaint();
  }
  public void mouseExited(MouseEvent e) {
    image = new javax.swing.ImageIcon(path);
    repaint();
  }
}