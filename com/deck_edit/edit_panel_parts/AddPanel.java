package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_model.AddPanelModel;
import com.deck_edit.edit_panel_parts.ShowCardListPanel;
import com.asset_controller.ImageButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddPanel extends JPanel implements ActionListener {//デッキ編集:追加操作のviewとcontroller
        ImageButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
        DeckEditorModel MyDeck;//デッキにカードを追加するための変数
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//各種カードデータ
        JLabel HowTo;
        AddPanelModel addOperation;
        ShowCardListPanel showCardList;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

        public AddPanel(DeckEditorModel MyDeck, AddPanelModel ado, ShowCardListPanel sc){//追加操作パネルのVCコンストラクタ デッキを値として引き渡す。

                G=new Gu(); P=new Pa(); C=new Chi(); GC=new G_C(); CP=new C_P(); PG=new P_G(); all=new ALL();

                Gu=new ImageButton(G.getPath()); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
                Pa=new ImageButton(P.getPath()); Pa.addActionListener(this);
                Chi=new ImageButton(C.getPath()); Chi.addActionListener(this);
                G_C=new ImageButton(GC.getPath()); G_C.addActionListener(this);
                C_P=new ImageButton(CP.getPath()); C_P.addActionListener(this);
                P_G=new ImageButton(PG.getPath()); P_G.addActionListener(this);
                ALL=new ImageButton(all.getPath()); ALL.addActionListener(this);
                HowTo=new JLabel("左クリックで追加");
                this.MyDeck=MyDeck;//引き渡された値を代入
                addOperation=ado;
                showCardList=sc;


                this.setLayout(new GridLayout(8,1));//レイアウト設定　今回はGridLayoutを用いる
                this.add(HowTo);
                this.add(Gu);
                this.add(Chi);
                this.add(Pa);
                this.add(G_C);
                this.add(C_P);
                this.add(P_G);
                this.add(ALL);
        }

        public void ChangeButtonUsable(Boolean[] Check){ //カードの追加が可能か否かに関するメソッド。
                if(MyDeck.CheckDeck()!=null && MyDeck.CheckDeck().size()>=40) {
                        Gu.Disabled(); Pa.Disabled(); Chi.Disabled();
                        G_C.Disabled(); C_P.Disabled(); P_G.Disabled();
                        ALL.Disabled();
                }else{
                        Gu.Enabled(); Chi.Enabled(); Pa.Enabled();
                        if(Check[0]==false) {
                                G_C.Disabled();
                        }else{
                                G_C.Enabled();
                        }
                        if(Check[1]==false) {
                                C_P.Disabled();
                        }else{
                                C_P.Enabled();
                        }
                        if(Check[2]==false) {
                                P_G.Disabled();
                        }else{
                                P_G.Enabled();
                        }
                        if(Check[3]==false) {
                                ALL.Disabled();
                        }else{
                                ALL.Enabled();
                        }
                }
        }

        public void actionPerformed(ActionEvent e){
                /*デッキ追加時のカードの判定部分　switch文は定数のみでしか構成できないので長いがif文で構成*/
                if(MyDeck.CheckDeck()==null || MyDeck.CheckDeck().size()<40) {
                        if(e.getSource()==Gu) {
                                G=new Gu();
                                addOperation.AddCardtoDeck(G, MyDeck);
                        }
                        if(e.getSource()==Pa) {
                                P=new Pa();
                                addOperation.AddCardtoDeck(P, MyDeck);
                        }
                        if(e.getSource()==Chi) {
                                C=new Chi();
                                addOperation.AddCardtoDeck(C, MyDeck);
                        }
                        if(e.getSource()==G_C) {
                                GC=new G_C();
                                addOperation.AddCardtoDeck(GC, MyDeck);
                        }
                        if(e.getSource()==C_P) {
                                CP=new C_P();
                                addOperation.AddCardtoDeck(CP, MyDeck);
                        }
                        if(e.getSource()==P_G) {
                                PG=new P_G();
                                addOperation.AddCardtoDeck(PG, MyDeck);
                        }
                        if(e.getSource()==ALL) {
                                all=new ALL();
                                addOperation.AddCardtoDeck(all, MyDeck);
                        }
                }

        }


}
