package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_parts.CardPanelBase;
import com.deck_edit.edit_panel_model.DeletePanelModel;
import javax.swing.*;
import java.awt.*;

public class ShowCardListPanel extends JPanel {        //デッキ内部のカードリストの表記、削除を行うview＆controller
        JPanel Text;//主に操作方法と、メッセージ表記を行うパネル
        JPanel CardList;//カードリストを表示するパネル
        JLabel Describe;//カードの相性を表記するラベル
        JLabel HowTo;//操作方法を記述
        JLabel Message;       //デッキの状態を記述
        CardPanelBase[] Card;//カードを表記
        DeckEditorModel MyDeck;
        DeletePanelModel DeleteOperation;


        public ShowCardListPanel(DeckEditorModel Deck, DeletePanelModel delM ){
                int i=0;
                this.setLayout(new GridLayout(3,1));
                MyDeck=Deck;
                DeleteOperation=delM;
                Card=new CardPanelBase[40];
                Text=new JPanel(); CardList=new JPanel();
                HowTo=new JLabel("カードの上で右クリックで削除します");
                Message=new JLabel(" ");
                Text.setLayout(new GridLayout(2,1));
                Text.add(HowTo); Text.add(Message);
                CardList.setLayout(new GridLayout(8, 5, 10, 10));
                if(MyDeck.CheckDeck()!=null) {
                        for(CardBase_E DeckCard: MyDeck.CheckDeck()) {
                                Card[i]=new CardPanelBase(DeckCard, DeleteOperation, this, MyDeck, i);
                                CardList.add(Card[i]);
                                if(i==39) Message.setText("これ以上カードを追加できません");
                                i++;
                        }
                }else{
                        Message.setText("カードが存在しません");
                }

                Describe=new JLabel();
                this.add(Text); this.add(CardList); this.add(Describe);
        }


        public void ChangeMessage(String Message){
                this.Message.setText(Message);
        }

        public void ChangeDescribe(String content){
                this.Describe.setText(content);
        }

        public void AddCardPanel(int number, CardBase_E DeckCard){
                CardPanelBase AddCardPanel;
                AddCardPanel=new CardPanelBase(DeckCard, DeleteOperation, this, MyDeck, number);
                Card[number]=AddCardPanel;
                CardList.add(Card[number]);
        }

        public void ResetPanel(){
                CardList.removeAll();
        }


}
