package com.deck_edit.edit_panel_model;

import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.asset_controller.RW_csv;
import java.io.*;

public class SavePanelModel {

        public void SaveDeck(int[] toSave, DeckEditorModel Deck, RW_csv save){
                int i=0;
                for(CardBase_E SaveCard: Deck.CheckDeck()) {
                        toSave[i]=SaveCard.getID();
                        i++;
                }
                save.WriteCSV(toSave);
        }
}
