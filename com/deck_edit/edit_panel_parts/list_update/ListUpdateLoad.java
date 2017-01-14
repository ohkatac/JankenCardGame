package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_parts.ShowCardListField;
import com.deck_edit.edit_panel_controller.LoadOperation;
import java.util.*;

public class ListUpdateLoad implements Observer {
        ShowCardListField showcardlist;
        LoadOperation loadOperation;
        String loadList;
        DeckEditorModel MyDeck;

        public ListUpdateLoad(ShowCardListField sl, LoadOperation lo, DeckEditorModel Deck){
                showcardlist=sl;
                loadOperation=lo;
                loadList=" ";
                MyDeck=Deck;
                loadOperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                if(MyDeck.CheckDeck()==null) {
                        showcardlist.ChangeMessage(0, "カードが存在しません");
                }else{
                        for(int i=0; i<=20; i++) {
                                showcardlist.ChangeMessage(i, " ");
                        }
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                loadList=Integer.toString(i+1);
                                loadList=loadList+". "+MyDeck.getCard(i).getCardName();
                                showcardlist.ChangeMessage(i, loadList);
                        }
                }
        }
}
