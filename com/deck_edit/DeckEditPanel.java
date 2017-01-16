package com.deck_edit;

import com.FrameController;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.CardIconBase;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import com.asset_controller.ImageButton;
import com.asset_controller.RW_csv;
import java.io.*;

// デッキ編集画面のView&Controller 操作に反応して画面上の再描画等を行う
final public class DeckEditPanel extends JPanel implements ActionListener, Observer {
        FrameController frameCont;//画面表示、遷移のための変数。詳細はFrameController.javaを参照
        JButton end;//タイトルへ戻るためのボタン
        ImageIcon BackGround;
        int width, height;//背景画像用に高さと幅を格納するための変数
        JPanel Additonal, ShowAndDelete, SaveAndLoad;//それぞれ、追加操作、表示・削除操作、セーブロード操作を担当
        JPanel CardList, Text;//ShowAndDeleteにてCardListはカードの表示、Textは使用方法とセーブ、ロードの通知を表示
        ImageButton Gu, Pa, Chi, G_C, C_P, P_G, ALL;//追加操作パネル上でのボタン 詳細はImageButton.javaを参照
        JButton Save, Load;//セーブ、ロードを行うボタン
        JLabel HowTo, Message;//HowToは使用方法、Messageはセーブ、ロード通知
        CardIconBase[] CardIcon;//デッキ内部のカード表示および削除ボタン担当　詳細はCardIconBase.javaを参照
        DeckEditorModel MyDeck;//デッキ編集操作に関するModel 必要な処理はここにすべて入っている 詳細はDeckEditorModel.java参照
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//追加時に必要な各種カードデータ


        public DeckEditPanel(FrameController frameCont) {         // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;

                BackGround=new ImageIcon("assets/img/background/tex_26.png");//背景画像用のImageIconを生成
                width=BackGround.getIconWidth();//高さ、幅を測定、確保
                height=BackGround.getIconHeight();

                /* 各種操作ごとのパネルの初期化とレイアウト設定*/
                this.setLayout(new BorderLayout());
                setOpaque(false);
                Additonal=new JPanel();

                Additonal.setLayout(new GridLayout(7,1));
                ShowAndDelete=new JPanel();
                ShowAndDelete.setLayout(new BorderLayout());
                SaveAndLoad=new JPanel();
                SaveAndLoad.setLayout(new GridLayout(2,1));

                /*追加操作部分に関するComponent*/
                Gu=new ImageButton(new String[] {
                        "assets/img/card/btnimg/Gu.png",
                        "assets/img/card/btnimg/Gu_pressed.png",
                        "assets/img/card/btnimg/Gu_hover.png",
                        "assets/img/card/btnimg/Gu_unable.png"
                }); Gu.addActionListener(this); Additonal.add(Gu);
                Pa=new ImageButton(new String[] {
                        "assets/img/card/btnimg/Pa.png",
                        "assets/img/card/btnimg/Pa_pressed.png",
                        "assets/img/card/btnimg/Pa_hover.png",
                        "assets/img/card/btnimg/Pa_unable.png"
                }); Pa.addActionListener(this); Additonal.add(Pa);
                Chi=new ImageButton(new String[] {
                        "assets/img/card/btnimg/Chi.png",
                        "assets/img/card/btnimg/Chi_pressed.png",
                        "assets/img/card/btnimg/Chi_hover.png",
                        "assets/img/card/btnimg/Chi_unable.png"
                }); Chi.addActionListener(this); Additonal.add(Chi);
                G_C=new ImageButton(new String[] {
                        "assets/img/card/btnimg/GuChi.png",
                        "assets/img/card/btnimg/GuChi_pressed.png",
                        "assets/img/card/btnimg/GuChi_hover.png",
                        "assets/img/card/btnimg/GuChi_unable.png"
                }); G_C.addActionListener(this); Additonal.add(G_C);
                C_P=new ImageButton(new String[] {
                        "assets/img/card/btnimg/ChiPa.png",
                        "assets/img/card/btnimg/ChiPa_pressed.png",
                        "assets/img/card/btnimg/ChiPa_hover.png",
                        "assets/img/card/btnimg/ChiPa_unable.png"
                }); C_P.addActionListener(this); Additonal.add(C_P);
                P_G=new ImageButton(new String[] {
                        "assets/img/card/btnimg/PaGu.png",
                        "assets/img/card/btnimg/PaGu_pressed.png",
                        "assets/img/card/btnimg/PaGu_hover.png",
                        "assets/img/card/btnimg/PaGu_unable.png"
                }); P_G.addActionListener(this); Additonal.add(P_G);
                ALL=new ImageButton(new String[] {
                        "assets/img/card/btnimg/All.png",
                        "assets/img/card/btnimg/All_pressed.png",
                        "assets/img/card/btnimg/All_hover.png",
                        "assets/img/card/btnimg/All_unable.png"
                }); ALL.addActionListener(this); Additonal.add(ALL);
                this.add(Additonal, BorderLayout.WEST);

                /*セーブとロードに関するComponent*/
                Save=new JButton("デッキをセーブ");
                Save.addActionListener(this); SaveAndLoad.add(Save);
                Load=new JButton("デッキをロード");
                Load.addActionListener(this); SaveAndLoad.add(Load);
                this.add(SaveAndLoad, BorderLayout.EAST);

                /*カード表記と削除に関するComponent*/
                MyDeck=new DeckEditorModel();//デッキを初期化
                MyDeck.addObserver(this);//監視対象に登録
                CardList=new JPanel();
                CardList.setOpaque(false);
                CardList.setLayout(new GridLayout(5,8));
                CardIcon=new CardIconBase[40];
                for(int i=0; i<40; i++) {
                        CardIcon[i]=new CardIconBase(i, MyDeck);//CardIconBaseごとに初期化
                        CardList.add(CardIcon[i]);//配置　基本的に表示の切り替えと反応のOnOffのみ行うので配置後の削除は行わない
                }
                Message=new JLabel("カードが存在しません");
                Message.setOpaque(false);
                Message.setHorizontalAlignment(JLabel.CENTER);
                if(MyDeck.CheckDeck()!=null) {//ここで、デッキにカードが一枚でも格納されている場合アイコンを表示する
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                CardIcon[i].setCardIcon();
                        }
                        MyDeck.Inform();
                        Message.setText("ロードしました");
                }
                Text=new JPanel();
                Text.setOpaque(false);
                Text.setLayout(new GridLayout(2,1));
                HowTo=new JLabel("右クリックで削除します");
                HowTo.setOpaque(false);
                HowTo.setHorizontalAlignment(JLabel.CENTER);
                Text.add(HowTo); Text.add(Message);
                ShowAndDelete.add(Text, BorderLayout.NORTH);
                ShowAndDelete.add(CardList, BorderLayout.CENTER);
                ShowAndDelete.setBorder(new EmptyBorder(2,2,2,0));//見やすくするためにBorderを設定
                this.add(ShowAndDelete, BorderLayout.CENTER);

                /*タイトルに戻るためのComponent*/
                end=new JButton("タイトルへ戻る");
                end.addActionListener(this);
                this.add(end, BorderLayout.NORTH);
                Additonal.setOpaque(false);
                ShowAndDelete.setOpaque(false);
                SaveAndLoad.setOpaque(false);


        }

        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == end) {
                        // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
                        // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
                        frameCont.showTitle(this);
                }
                if(MyDeck.CheckDeck()==null || MyDeck.CheckDeck().size()<40) {//追加操作ボタンに対する動作
                        if(e.getSource()==Gu) {
                                G=new Gu();
                                MyDeck.AddCardToDeck(G);
                        }
                        if(e.getSource()==Pa) {
                                P=new Pa();
                                MyDeck.AddCardToDeck(P);
                        }
                        if(e.getSource()==Chi) {
                                C=new Chi();
                                MyDeck.AddCardToDeck(C);
                        }
                        if(e.getSource()==G_C) {
                                GC=new G_C();
                                MyDeck.AddCardToDeck(GC);
                        }
                        if(e.getSource()==C_P) {
                                CP=new C_P();
                                MyDeck.AddCardToDeck(CP);
                        }
                        if(e.getSource()==P_G) {
                                PG=new P_G();
                                MyDeck.AddCardToDeck(PG);
                        }
                        if(e.getSource()==ALL) {
                                all=new ALL();
                                MyDeck.AddCardToDeck(all);
                        }
                }
                if(e.getSource()==Save && MyDeck.CheckDeck()!=null) { //セーブ、ロードボタンに対する操作
                        MyDeck.SaveDeck();
                        Message.setText("セーブしました");
                }
                if(e.getSource()==Load && MyDeck.CheckDeck()!=null) {
                        MyDeck.LoadDeck();
                        Message.setText("ロードしました");
                }
        }

        public void update(Observable o, Object arg){
                Boolean[] Flag;
                Gu.Enabled(); Chi.Enabled(); Pa.Enabled();//追加ボタン、リストアイコンの初期化
                G_C.Enabled(); C_P.Enabled(); P_G.Enabled();
                ALL.Enabled();
                MyDeck.CountChecker(); //ここでカウンターをチェック、フラグを更新する
                for(int i=0; i<40; i++) {
                        CardIcon[i].IconClear();
                }
                if(MyDeck.CheckDeck()!=null) {//ここでデッキ内にカードが存在する場合、アイコンの再配置、
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) { //及び追加ボタンの使用可否を判定する
                                CardIcon[i].setCardIcon();
                        }
                        Flag=MyDeck.getChekerList();//CheckerをここでFlagに代入、判定を行う
                        if(Flag[0]==false) G_C.Disabled();
                        if(Flag[1]==false) C_P.Disabled();
                        if(Flag[2]==false) P_G.Disabled();
                        if(Flag[3]==false) ALL.Disabled();
                        if(MyDeck.CheckDeck().size()>=40) {//最終的に40枚を超えた場合(超えることは直接データを
                                Gu.Disabled(); Chi.Disabled(); Pa.Disabled();//いじらないと起きないが)全てのボタンを使用不可にする
                                G_C.Disabled(); C_P.Disabled(); P_G.Disabled();
                                ALL.Disabled();
                        }
                }

        }
        public void paintComponent(Graphics g){
                g.drawImage(BackGround.getImage(), 0, 0, width, height, null);
                super.paintComponent(g);
        }
}