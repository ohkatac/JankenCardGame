package com.deck_edit.edit_panel_model;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import java.util.*;

public class AddPanelModel extends Observable {//追加操作のModel

        public void AddCardtoDeck(CardBase_E Card, DeckEditorModel AddDeck){
                AddDeck.setCardToDeck(Card);
                setChanged();
                notifyObservers();

        }
}
