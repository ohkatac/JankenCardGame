package com.deck_edit.edit_panel_model;

import com.deck_edit.DeckEditorModel;
import java.util.*;

public class DeletePanelModel extends Observable {

        public void DeleteCardforDeck(int number, DeckEditorModel MyDeck){

                MyDeck.deleteCard(number);
                setChanged();
                notifyObservers();

        }
}
