package com.deck_edit.edit_panel_controller;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.asset_controller.RW_csv;
import java.util.*;
import java.io.*;

public class LoadOperation extends Observable {

        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;

        public void LoadDeck(DeckEditorModel Deck, RW_csv Load, int size){
                int[] fromLoad=Load.ReadCSV();
                if(Deck.CheckDeck()!=null) {
                        Deck.CheckDeck().clear();
                }
                for(int i=0; i<fromLoad.length; i++) {
                        switch(fromLoad[i]) {

                        case 1:
                                G=new Gu();
                                Deck.setCardToDeck(G);
                                break;
                        case 2:
                                P=new Pa();
                                Deck.setCardToDeck(P);
                                break;
                        case 3:
                                C=new Chi();
                                Deck.setCardToDeck(C);
                                break;
                        case 4:
                                GC=new G_C();
                                Deck.setCardToDeck(GC);
                                break;
                        case 5:
                                CP=new C_P();
                                Deck.setCardToDeck(CP);
                                break;
                        case 6:
                                PG=new P_G();
                                Deck.setCardToDeck(PG);
                                break;
                        case 7:
                                all=new ALL();
                                Deck.setCardToDeck(all);
                                break;
                        }
                }
                setChanged();
                notifyObservers();
        }
}
