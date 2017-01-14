package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_parts.ShowCardListField;
import com.deck_edit.edit_panel_controller.SaveOperation;
import com.deck_edit.edit_panel_controller.LoadOperation;
import com.asset_controller.RW_csv;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SaveAndLoadField extends JPanel implements ActionListener {
        /*csvファイルからデータを読み込み,デッキを生成、もしくはデッキデータをセーブするパネル部分のview&controller*/
        int[] DeckData;
        JButton Save, Load;
        RW_csv DeckManager;
        DeckEditorModel MyDeck;
        SaveOperation saveOperation;
        LoadOperation loadOperation;
        ShowCardListField showCardList;

        public SaveAndLoadField(DeckEditorModel Deck, ShowCardListField sc, SaveOperation so, LoadOperation lo){
                DeckData=new int[20];         //最大カード格納数を配列のサイズとする.
                MyDeck=Deck;
                saveOperation=so;
                loadOperation=lo;
                showCardList=sc;
                for(int i=0; i<20; i++) {
                        DeckData[i]=0;        //初期化時にデータを受け取る配列内部の値をカードIDとして設定していない"0(ダミー)"にする.
                }
                DeckManager =new RW_csv(new File("assets/csv/main_deck.csv"));
                this.setLayout(new GridLayout(2,1));
                Save=new JButton("デッキをセーブ"); this.add(Save);
                Load=new JButton("デッキをロード"); this.add(Load);
                Save.addActionListener(this); Load.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e){
                /*セーブ,ロード,各操作をObserverパターンのために分離*/
                if(e.getSource()==Save && showCardList.getDeckSize()>0)
                        saveOperation.SaveDeck(DeckData, MyDeck, DeckManager);
                if(e.getSource()==Load) {
                        loadOperation.LoadDeck(MyDeck, DeckManager, showCardList.getDeckSize());
                        showCardList.setDeckSize(MyDeck.CheckDeck().size());
                }
        }
}
