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
import com.asset_controller.ImageButton;
import com.asset_controller.RW_csv;
import java.io.*;

// デッキ編集画面のVC 操作に反応して画面上の再描画等を行う
final public class DeckEditPanel extends JPanel implements ActionListener, Observer {
        FrameController frameCont;
        JButton end;
        JPanel Additonal, ShowAndDelete, SaveAndLoad;
        JPanel CardList, Text;
        ImageButton Gu, Pa, Chi, G_C, C_P, P_G, ALL;
        JButton Save, Load;
        JLabel HowTo, Message;
        CardIconBase[] CardIcon;
        DeckEditorModel MyDeck;
        int countGC, countCP, countPG, countALL;
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;


        public DeckEditPanel(FrameController frameCont) {         // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;
                MyDeck=new DeckEditorModel();
                MyDeck.addObserver(this);


                this.setLayout(new BorderLayout());
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
                        "assets/img/card/btnimg/PaGu_unabled.png"
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
                CardList=new JPanel();
                CardList.setLayout(new GridLayout(5,8));
                CardIcon=new CardIconBase[40];
                for(int i=0; i<40; i++) {
                        CardIcon[i]=new CardIconBase(i, MyDeck);
                        CardList.add(CardIcon[i]);
                }
                Message=new JLabel("カードが存在しません");
                if(MyDeck.CheckDeck()!=null) {
                        int i=0;
                        for(CardBase_E Card: MyDeck.CheckDeck()) {
                                CardIcon[i].setCardIcon();
                                i++;
                        }
                        Message.setText("ロードしました");
                }
                Text=new JPanel();
                Text.setLayout(new GridLayout(2,1));
                HowTo=new JLabel("右クリックで削除します");
                Text.add(HowTo); Text.add(Message);
                ShowAndDelete.add(Text, BorderLayout.NORTH);
                ShowAndDelete.add(CardList, BorderLayout.CENTER);
                this.add(ShowAndDelete, BorderLayout.CENTER);

                /*タイトルに戻るためのComponent*/
                end=new JButton("タイトルへ戻る");
                end.addActionListener(this);
                this.add(end, BorderLayout.NORTH);


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
                Flag=MyDeck.getChekerList();
                Gu.Enabled(); Chi.Enabled(); Pa.Enabled();
                G_C.Enabled(); C_P.Enabled(); P_G.Enabled();
                ALL.Enabled();
                MyDeck.CountChecker();
                for(int i=0; i<40; i++) {
                        CardIcon[i].IconClear();
                }
                if(MyDeck.CheckDeck()!=null) {
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                CardIcon[i].setCardIcon();
                        }
                        Flag=MyDeck.getChekerList();
                        if(Flag[0]==false) G_C.Disabled();
                        if(Flag[1]==false) C_P.Disabled();
                        if(Flag[2]==false) P_G.Disabled();
                        if(Flag[3]==false) ALL.Disabled();
                        if(MyDeck.CheckDeck().size()>=40) {
                                Gu.Disabled(); Chi.Disabled(); Pa.Disabled();
                                G_C.Disabled(); C_P.Disabled(); P_G.Disabled();
                                ALL.Disabled();
                        }
                }

        }
}
