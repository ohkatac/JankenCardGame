package com.deck_edit.edit_panel_model;

import com.deck_edit.DeckEditorModel;
import java.util.*;

public class DeletePanelModel extends Observable {
        DeckEditorModel MyDeck;

        public void DeleteCardforDeck(int number, DeckEditorModel Deck){

                MyDeck=Deck;
                MyDeck.deleteCard(number);
                setChanged();
                notifyObservers();

        }
}
