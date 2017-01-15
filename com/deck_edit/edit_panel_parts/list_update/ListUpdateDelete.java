package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_model.DeletePanelModel;
import com.deck_edit.edit_panel_parts.*;
import java.util.*;

/* 削除操作時にListの表示を更新するView*/

public class ListUpdateDelete implements Observer {//削除操作に対するObserver. Observerパターンの都合により分離.
        ShowCardListPanel Showcardlist;
        DeletePanelModel deloperation;
        AddPanel add;
        String delList;//リストに表示する文
        DeckEditorModel MyDeck;
        Boolean[] Flag;

        public ListUpdateDelete(ShowCardListPanel sl, DeletePanelModel delo, DeckEditorModel Deck, AddPanel Ap){
                Showcardlist=sl;
                add=Ap;
                deloperation=delo;
                delList=" ";
                MyDeck=Deck;
                deloperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                Showcardlist.ResetPanel();
                MyDeck.CheckCard();
                if(MyDeck.CheckDeck()==null) {
                        Showcardlist.ChangeMessage("カードが存在しません");
                }else{
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                Showcardlist.AddCardPanel(i, MyDeck.getCard(i));
                        }
                }
                Flag=MyDeck.getChekerList();
                add.ChangeButtonUsable(Flag);

        }
}
