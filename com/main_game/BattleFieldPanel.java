/*
  MainGame画面の一番真ん中のバトルフィールドのViewをつかさどるクラス
  基本はMainGameModelから必要なモデルをgetterでとってきてそれを使用するという方針
*/

package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class BattleFieldPanel extends JPanel {
  // MainGameのModelがすべて入っているインスタンスを参照する変数。 ここからmodelを取り出していく
  MainGameModel model;

  // 自分のバトルフィールドと相手のを二分割するために二つの変数に分けてさらにBorderLayoutを適用させ分ける
  JPanel myBattleF;
  JPanel riBattleF;

  // 自分のカードと相手のカードの画像を表示するためのJLabel。このクラスに表示する画像を載せていく
  JLabel myCard;
  JLabel riCard;

  // カードを出すときカードの裏側の画像を表示するので避難用の変数として定義する。 
  //int a, int bのときaとbの中身を入れ替えるとき int tempを用意するがそれと同じ
  ImageIcon mycardIcon;
  ImageIcon ricardIcon;

  // 現在場にカードが出ているかを記憶するための変数
  Boolean isMycard;
  Boolean isRicard;

  // 各種Iconを格納する変数
  ImageIcon emptyIcon;
  ImageIcon backIcon;

  public BattleFieldPanel(MainGameModel model) {
    this.model = model;
    this.setOpaque(false); // 背景画像を表示するためにこのJPanelそのものを透明化する。

    myBattleF = new JPanel();
    myBattleF.setLayout( new FlowLayout() ); // とりあえず一番簡単なFlowLayoutに設定
    myBattleF.setOpaque(false); // 背景画像を表示するためにこのJPanelそのものを透明化する。
    riBattleF = new JPanel();
    riBattleF.setLayout( new FlowLayout() ); // とりあえず一番簡単なFlowLayoutに設定
    riBattleF.setOpaque(false); // 背景画像を表示するためにこのJPanelそのものを透明化する。

    this.setLayout( new BorderLayout() );
    this.add(myBattleF, BorderLayout.EAST); // 自分のバトルフィールドを右側に配置
    this.add(riBattleF, BorderLayout.WEST); // 相手のバトルフィールドを左側に配置

    isMycard = false;
    isRicard = false;

    // 一番初めはカードが何もない状態を表す画像を表示
    try {
      myCard = new JLabel(new ImageIcon(
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(
          "assets/img/card/origin/empty.png"
        ))
      ));
      myBattleF.add(myCard);

      riCard = new JLabel(new ImageIcon(
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(
          "assets/img/card/origin/empty.png"
        ))
      ));
      riBattleF.add(riCard);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

// 自分のフィールドにカードを設置するメソッド
  public void setMyCard( CardModel card ){
    isMycard = true; // フラグをtrueにする
    try {
      mycardIcon = new ImageIcon(
          ImageIO.read(getClass().getClassLoader().getResourceAsStream(
            card.getImgPath()
          ))
      ); // 自分が出すカードの画像をサブ用変数にコピーしておく
    } catch (IOException e) {
      e.printStackTrace();
    }

    myCard.setVisible(false); // いったん場のコンポーネントを見えなくする
    myBattleF.remove(myCard); // もともとあるmyCardコンポーネントを場から取り除いておく
    myCard = new JLabel( mycardIcon ); // 場に出しているカードの画像を生成
    myBattleF.add(myCard); // 自分のフィールドに指定されたカードを表示
    myCard.setVisible(true); // コンポーネントを再表示
  }

  public void setRivalCard( CardModel card ){
    isRicard = true;
    try {
      ricardIcon = new ImageIcon(
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(
          card.getImgPath()
        ))
      ); // 自分が出すカードの画像をサブ用変数にコピーしておく

      riCard.setVisible(false);
      riBattleF.remove(riCard);
      riCard = new JLabel(
        new ImageIcon(
          ImageIO.read(getClass().getClassLoader().getResourceAsStream(
            "assets/img/card/origin/back.png"
          ))
        )
      );
    } catch (IOException e) {
      e.printStackTrace();
    }
    riBattleF.add(riCard);
    riCard.setVisible(true);
  }

  public void openRivalCard() {
    riCard.setVisible(false);
    riCard = new JLabel( ricardIcon );
    riBattleF.add(riCard);
    riCard.setVisible(true);
  }

  public void RemoveCards() {
    isMycard = isRicard = false;
    mycardIcon = ricardIcon = null;

    try {
      // 場のカードを空カードに設定
      myCard.setVisible(false);
      myBattleF.remove(myCard);
      myCard = new JLabel(new ImageIcon(
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(
          "assets/img/card/origin/empty.png"
        ))
      ));
      myBattleF.add(myCard);
      myCard.setVisible(true);

      // 場のカードを空カードに設定
      riCard.setVisible(false);
      myBattleF.remove(riCard);
      riCard = new JLabel(new ImageIcon(
        ImageIO.read(getClass().getClassLoader().getResourceAsStream(
          "assets/img/card/origin/empty.png"
        ))
      ));
      riBattleF.add(riCard);
      riCard.setVisible(true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Boolean isSetCards() {
    if(isMycard && isRicard) return true;
    else return false;
  }
}