package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_model.DeletePanelModel;
import com.deck_edit.edit_panel_parts.ShowCardListPanel;
import java.util.*;

/* 削除操作時にListの表示を更新するView*/

public class ListUpdateDelete implements Observer {//削除操作に対するObserver. Observerパターンの都合により分離.
        ShowCardListPanel Showcardlist;
        DeletePanelModel deloperation;
        String delList;//リストに表示する文
        DeckEditorModel MyDeck;

        public ListUpdateDelete(ShowCardListPanel sl, DeletePanelModel delo, DeckEditorModel Deck){
                Showcardlist=sl;
                deloperation=delo;
                delList=" ";
                MyDeck=Deck;
                deloperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                if(MyDeck.CheckDeck()==null) {//削除操作後カードがない場合はないことを知らせる。
                        Showcardlist.ChangeMessage(20, "カードが存在しません");
                }else{
                        for(int i=0; i<=20; i++) {
                                Showcardlist.ChangeMessage(i, " ");
                        }
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                delList=Integer.toString(i+1);
                                delList=delList+". "+MyDeck.getCard(i).getCardName();
                                Showcardlist.ChangeMessage(i, delList);
                                if(i==20) Showcardlist.ChangeMessage(20, "これ以上カードを追加できません"); //
                        }
                }
        }
}
