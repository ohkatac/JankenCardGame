package com.game_select;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
// import for background image
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

// import for URL, URI
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

import com.FrameController;

// import for network
import com.asset_controller.RW_csv;
import com.localhost_model.*;

// GameSelect's Model & View & Controller
final public class GameSelectPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JPanel[] data;
  JLabel comBattleLabel;
  JButton comBattleBtn;

  JLabel localLabel;

  ButtonGroup radioGroup;
  JRadioButton[] isServer;

  JLabel portNumLabel;
  JTextField portNum;
  JButton localBattleBtn;

  JButton gotoTitle;

  String portTxt;

  BufferedImage backgroundImage = null; // 背景画像のインスタンスを保存するための変数

  public GameSelectPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる

    // Resultの背景画像を取得 例外が発生したらコンソールにエラー内容を表示する。
		try {
      backgroundImage = ImageIO.read(new File(
				getClass().getClassLoader().getResource(
					"assets/img/background/select_port.png"
				).toURI()
			));
    } catch (URISyntaxException e) {
			e.printStackTrace();
      backgroundImage = null;
		} catch (IOException e) {
      e.printStackTrace();
      backgroundImage = null;
    }

    this.frameCont = frameCont;

    data = new JPanel[8];
    for(int i = 0; i < data.length; i++) {
      data[i] = new JPanel();
      data[i].setOpaque(false);
      data[i].setLayout(new FlowLayout());
    }

    comBattleLabel = new JLabel("コンピュータ対戦");

    comBattleBtn = new JButton("コンピュータ対戦");
    comBattleBtn.addActionListener(this);

    localLabel = new JLabel("Localhostネットワーク対戦");

    isServer = new JRadioButton[2];
    isServer[0] = new JRadioButton("サーバーとして接続");
    isServer[1] = new JRadioButton("クライアントとして接続");

    radioGroup = new ButtonGroup();
    radioGroup.add(isServer[0]);
    radioGroup.add(isServer[1]);

    portNumLabel = new JLabel("ポート番号");
    portNum = new JTextField(10);
    portTxt = portNum.getText();

    localBattleBtn = new JButton("ネットワーク対戦");
    localBattleBtn.addActionListener(this);

    gotoTitle = new JButton("タイトル画面へ戻る");
    gotoTitle.addActionListener(this);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    for(int i = 0; i < data.length; i++) {
      this.add(data[i]);
    }

    data[0].add(comBattleLabel);
    data[1].add(comBattleBtn);
    data[2].add(localLabel);
    data[3].add(isServer[0]);
    data[3].add(isServer[1]);
    data[4].add(portNumLabel);
    data[4].add(portNum);
    data[5].add(localBattleBtn);
    data[6].add(gotoTitle);
  }

  // paintComponentによりJPanelを背景画像で上塗りする処理
  // Override
  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;

    if(backgroundImage != null) {
      g2.drawImage(backgroundImage, 0, 0, this);
    }
  }

  public void actionPerformed(ActionEvent e) {
    if( e.getSource() == comBattleBtn ) {
      // 自分のデッキの取得及びシャッフル
      RW_csv mainDeck = new RW_csv("assets/csv/main_deck.csv");
      int my_deck[] = mainDeck.ReadCSV();

      ArrayList<Integer> temp = new ArrayList<Integer>();
      for(int i = 0; i < my_deck.length; i++) {
        temp.add(my_deck[i]);
      }
      for(int i = 0; i < my_deck.length; i++) {
        Random r = new Random();
        int rnd_i = r.nextInt(temp.size());
        my_deck[i] = temp.get( rnd_i );
        temp.remove( rnd_i );
      }
      //

      // 相手のデッキを自動生成
      int[] ri_deck = new int[40];
      for(int i = 0; i < ri_deck.length; i++) {
        Random rnd = new Random();
        ri_deck[i] = rnd.nextInt(3)+1;
      }
      //

      frameCont.showMainGame(this, my_deck, ri_deck);
    }
    if( e.getSource() == localBattleBtn ) { // ネットワーク対戦用の処理

      String p_txt = portNum.getText();
      int port = Integer.parseInt(p_txt);

      if ( port < 1025 && port > 65536 ) return; // 1025～65536 内のポート番号でないならば何も処理をしない。

      RW_csv mainDeck = new RW_csv("assets/csv/main_deck.csv");
      String pl_deckData = mainDeck.readString();
      String ri_deckData;

      if(isServer[0].isSelected()) { // サーバーとして接続
        CommServer cs = new CommServer(port);

        ri_deckData = cs.recv(); // 接続が完了するまで待つ
        cs.send(pl_deckData);

        int[] pl_deck = splitCSVtoInt(pl_deckData);
        int[] ri_deck = splitCSVtoInt(ri_deckData);

        frameCont.showMainGame(this, pl_deck, ri_deck, cs, true);
      }//

      else if(isServer[1].isSelected()) { // クライアントとして接続
        CommClient cl = new CommClient("localhost", port);

        while(true) {
          cl.send(pl_deckData);
          ri_deckData = cl.recv();
          if(ri_deckData != null) break;
        }

        int[] pl_deck = splitCSVtoInt(pl_deckData);
        int[] ri_deck = splitCSVtoInt(ri_deckData);

        frameCont.showMainGame(this, pl_deck, ri_deck, cl, false);
      } //

    }

    if( e.getSource() == gotoTitle ) {
      frameCont.showTitle(this);
    }
  }

  private int[] splitCSVtoInt(String data) {
    String[] temp = data.split(",", 0); // csvファイルの行を','で分割して代入する

    int[] intData = new int[temp.length]; // 入ったデータ分の個数int型配列を確保

    for(int i = 0; i < temp.length; i++) {
      intData[i] = Integer.parseInt(temp[i]); // 文字列を数値に直しつつint型配列に代入
    }

    return intData;
  }
}