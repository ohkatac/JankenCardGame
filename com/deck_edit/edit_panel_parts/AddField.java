package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_controller.AddOperation;
import com.deck_edit.edit_panel_parts.ShowCardListField;
import com.asset_controller.ImageButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddField extends JPanel implements ActionListener {//デッキ編集:追加操作のviewとcontroller
        ImageButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
        JLabel ex_Gu, ex_Chi, ex_Pa, ex_G_C, ex_C_P, ex_P_G, ex_ALL; //カード説明;
        DeckEditorModel MyDeck;//デッキにカードを追加するための変数
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//各種カードデータ
        AddOperation addOperation;
        ShowCardListField showCardList;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

        public AddField(DeckEditorModel MyDeck, AddOperation ado, ShowCardListField sc){//追加操作パネルのVCコンストラクタ デッキを値として引き渡す。
                Gu=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
                Pa=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Pa.addActionListener(this);
                Chi=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Chi.addActionListener(this);
                G_C=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); G_C.addActionListener(this);
                C_P=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); C_P.addActionListener(this);
                P_G=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); P_G.addActionListener(this);
                ALL=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); ALL.addActionListener(this);

                this.MyDeck=MyDeck;//引き渡された値を代入
                addOperation=ado;
                showCardList=sc;
                /*ここはカードの名前の記述*/
                ex_Gu=new JLabel("グー"); ex_Chi=new JLabel("チー"); ex_Pa=new JLabel("パー");
                ex_G_C=new JLabel("グーチー"); ex_C_P=new JLabel("チーパー"); ex_P_G=new JLabel("パーグー");
                ex_ALL=new JLabel("グーチーパー");


                this.setLayout(new GridLayout(7,2));//レイアウト設定　今回はGridLayoutを用いる
                this.add(ex_Gu); this.add(Gu); //AddCard.add(ex_Gu);
                this.add(ex_Chi); this.add(Chi); //AddCard.add(ex_Chi);
                this.add(ex_Pa); this.add(Pa); //AddCard.add(ex_Pa);
                this.add(ex_G_C); this.add(G_C); //AddCard.add(ex_G_C);
                this.add(ex_C_P); this.add(C_P); //AddCard.add(ex_C_P);
                this.add(ex_P_G); this.add(P_G); //AddCard.add(ex_P_G);
                this.add(ex_ALL); this.add(ALL); //AddCard.add(ex_ALL);
        }

        public void actionPerformed(ActionEvent e){
                /*デッキ追加時のカードの判定部分　switch文は定数のみでしか構成できないので長いがif文で構成*/
                if(showCardList.getDeckSize()<20) {
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
                showCardList.setDeckSize(MyDeck.CheckDeck().size());
        }
}
