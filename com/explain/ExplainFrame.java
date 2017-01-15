package com.explain;

import javax.swing.*;
import java.awt.*;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

final public class ExplainFrame extends JFrame implements ActionListener{

  JPanel cardPanel;
  CardLayout layout;

  public static void main(String[] args){
    ExplainFrame frame = new ExplainFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10,10,900,750);
    frame.setVisible(true);
  }

  public ExplainFrame(){

  //1ページ目
    JPanel card1 = new JPanel();
    JLabel label = new JLabel(
      "<html><body><h3>＜遊び方説明＞<h3>"+
      "<h4>〇概要<h4>"+
      "　7種類のじゃんけんカードで40枚のデッキを作り、カードを用いてじゃんけんをする2人対戦型ゲーム。<br />"+
      "　先に20ポイントある相手のライフポイントを0にするか、相手のデッキにカードが無くなると勝ちになります。<br />"+
      "<h4>〇カード紹介<h4>"+
      "　・カードの種類<br />"+
      "　　グー、チョキ、パー、グーチー、チーパー、パーグー、グーチョキパーの計7種類のカードがあります。<br />"+
      "　　カードの相性は次のページで説明します。<br />"+
      "　・点数計算<br />"+
      "　　カードの種類によって、勝った時相手に与えるダメージが変わります。<br />"+
      "　　(1)グー、チョキ、パー : 2point<br />"+
      "　　(2)グーチー、チーパー、パーグー : 5point<br />"+
      "　　(3)グーチョキパー : 7point<br />"+
      "<h4>〇ゲームの進め方<h4>"+
      "　１．ゲームを始める前に、[デッキ編集]でデッキを作ります。<br />"+
      "　　　デッキは全部で40枚で、グーチーパーが1枚、グーチー、チーパー、パーグーがそれぞれ3枚ずつまでの枚数制限があります。<br />"+
      "　２．デッキ編集が終了したら、ホーム画面に戻り、[ゲームスタート]でゲーム開始です。<br />"+
      "　３．ゲーム画面の説明です。<br />"+
      "　　　画面下に自分のデッキと5枚の手札、画面中央にバトルフィールド、画面上に相手のデッキと5枚の手札があります。<br />"+
      "　４．始めにドローフェイズです。デッキから1枚ドローし、自分の手札が6枚になります。<br />"+
      "　５．次にバトルフェイズです。自分の手札から1枚、じゃんけんをするカードを手札からフィールドに出します。<br />"+
      "　　　1枚選んだあとに[カードを出す]でお互いのカードを開示します。<br />"+
      "　　　勝った場合は相手にダメージ、負けた場合は自分にダメージが与えられます。<br />"+
      "　　　ダメージ計算が終わった後は、[次のバトルへ進む]で、4.に戻ります。<br />"+
      "　６．バトルを続け、先に相手のライフポイントを0にするか、相手のデッキにカードが無くなると勝ちになります。<br />"+
      "　７．最後に[Go result]で、ゲーム結果を見ることができます。</body></html>");
    card1.add(label,BorderLayout.NORTH);

  //2ページ目
    JPanel card2 = new JPanel();
    card2.add(new JButton("button"));

  //新しくパネル生成
    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);

    cardPanel.add(card1);
    cardPanel.add(card2);

  //カード移動用ボタン追加
    JButton prevButton = new JButton("前ページへ");
    prevButton.addActionListener(this);
    prevButton.setActionCommand("Prev");

    JButton nextButton = new JButton("次ページへ");
    nextButton.addActionListener(this);
    nextButton.setActionCommand("Next");

    JPanel btnPanel = new JPanel();
    btnPanel.add(prevButton);
    btnPanel.add(nextButton);

    add(cardPanel, BorderLayout.CENTER);
    add(btnPanel, BorderLayout.PAGE_END);

  //スクロール追加
    JScrollPane scrollpane = new JScrollPane(label);
    scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    cardPanel.add(scrollpane);
  }

  public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();

    if (cmd.equals("Prev")){
      layout.previous(cardPanel);
    }else if (cmd.equals("Next")){
      layout.next(cardPanel);
    }
  }

}