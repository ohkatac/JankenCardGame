package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_parts.*;
import com.deck_edit.edit_panel_model.LoadPanelModel;
import java.util.*;

public class ListUpdateLoad implements Observer {
        ShowCardListPanel showcardlist;
        LoadPanelModel loadOperation;
        AddPanel AP;
        DeckEditorModel MyDeck;
        Boolean[] Flag;

        public ListUpdateLoad(ShowCardListPanel sl, LoadPanelModel lo, DeckEditorModel Deck, AddPanel Ap){
                showcardlist=sl;
                loadOperation=lo;
                AP=Ap;
                MyDeck=Deck;
                loadOperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                showcardlist.ResetPanel();
                MyDeck.CheckCard();
                if(MyDeck.CheckDeck()==null) {
                        showcardlist.ChangeMessage("カードが存在しません");
                }else{
                        for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                                showcardlist.AddCardPanel(i, MyDeck.getCard(i));
                        }
                }
                Flag=MyDeck.getChekerList();
                AP.ChangeButtonUsable(Flag);
        }
}
