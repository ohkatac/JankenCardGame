package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_controller.DeleteOperation;
import com.deck_edit.edit_panel_parts.ShowCardListField;
import java.util.*;

public class ListUpdateDelete implements Observer {//削除操作に対するObserver. Observerパターンの都合により分離.
        ShowCardListField Showcardlist;
        DeleteOperation deloperation;
        String delList;//リストに表示する文
        DeckEditorModel MyDeck;

        public ListUpdateDelete(ShowCardListField sl, DeleteOperation delo, DeckEditorModel Deck){
                Showcardlist=sl;
                deloperation=delo;
                delList=" ";
                MyDeck=Deck;
                deloperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                if(MyDeck.CheckDeck()==null) {
                        Showcardlist.ChangeMessage(20, "カードが存在しません");
                }else{
                        for(int i=0; i<=20; i++) {
                                Showcardlist.ChangeMessage(i, " ");
                        }
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                delList=Integer.toString(i+1);
                                delList=delList+". "+MyDeck.getCard(i).getCardName();
                                Showcardlist.ChangeMessage(i, delList);
                                if(i==20) Showcardlist.ChangeMessage(20, "これ以上カードを追加できません");
                        }
                }
        }
}
