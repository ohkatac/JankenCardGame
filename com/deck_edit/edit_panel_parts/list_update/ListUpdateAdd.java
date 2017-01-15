package com.deck_edit.edit_panel_parts.list_update;

import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.edit_panel_parts.*;
import com.deck_edit.edit_panel_model.AddPanelModel;;
import java.util.*;

/* 追加を行った際にListの表示を更新するView*/

public class ListUpdateAdd implements Observer {//追加操作に対するObserver. Observerパターンの都合により分離.
        ShowCardListPanel showCardList;//リスト操作用
        AddPanelModel addoperation;
        AddPanel add;
        DeckEditorModel MyDeck;
        Boolean[] Flag;

        public ListUpdateAdd(ShowCardListPanel sl, AddPanelModel ao, DeckEditorModel Deck, AddPanel Ap){
                showCardList=sl;
                addoperation=ao;
                add=Ap;
                MyDeck=Deck;
                addoperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                int i;
                i=MyDeck.CheckDeck().size();
                showCardList.AddCardPanel(i-1, MyDeck.getCard(i-1));
                MyDeck.CheckCard();
                Flag=MyDeck.getChekerList();
                add.ChangeButtonUsable(Flag);
        }

}
