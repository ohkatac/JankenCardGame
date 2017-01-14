package com.deck_edit.edit_panel_controller;

import com.deck_edit.DeckEditorModel;
import java.util.*;

public class DeleteOperation extends Observable {

        public void DeleteCardforDeck(int number, DeckEditorModel MyDeck){

                MyDeck.deleteCard(number);
                setChanged();
                notifyObservers();

        }
}
