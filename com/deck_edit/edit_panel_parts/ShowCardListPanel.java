package com.deck_edit.edit_panel_parts;

import com.deck_edit.DeckEditorModel;
import javax.swing.*;
import java.awt.*;

public class ShowCardListPanel extends JPanel {        //デッキ内部のカードリストの表記を行うview
        static JLabel[] List;        //デッキ内部表示用のJLabel配列
        JLabel NoneDeck;        //デッキが存在しない時の表示用ラベル
        String list;        //リスト文字列格納用変数
        DeckEditorModel MyDeck;
        int DeckSize; //デッキの大きさを格納する。

        public ShowCardListPanel(DeckEditorModel Deck){
                List=new JLabel[21];        //20番目はメッセージ用
                for(int j=0; j<21; j++) {
                        List[j]=new JLabel();
                }
                list=new String();
                DeckSize=0;
                this.setLayout(new GridLayout(21,1));
                MyDeck=Deck;
                for(int j=0; j<21; j++) {
                        this.add(List[j]);
                }
                if(MyDeck.CheckDeck()!=null) {        //初期化時にデッキが存在する場合。
                        for(int j=0; j<MyDeck.CheckDeck().size(); j++) {
                                list=Integer.toString(j+1);
                                list=list+". "+MyDeck.getCard(j).getCardName();
                                List[j].setText(list);
                                DeckSize=MyDeck.CheckDeck().size();
                                if(j==19) {
                                        List[20].setText("カードをこれ以上追加できません.");        //最大に達した場合のメッセージ
                                }
                        }
                }else if(MyDeck.CheckDeck()==null) {
                        List[20].setText("カードが存在しません.");
                        //デッキが存在しない場合メッセージを表示
                }
        }

        public void setDeckSize(int size){ //各種操作時に必要となるデッキの大きさの値を格納する.
                DeckSize=size;
        }

        public int getDeckSize(){//デッキの大きさを返す.
                return DeckSize;
        }

        public void ChangeMessage(int number, String Message){
                List[number].setText(Message);
        }


}
