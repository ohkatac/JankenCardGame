package com.deck_edit;

import com.FrameController;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.edit_panel_parts.*;
import com.deck_edit.edit_panel_parts.list_update.*;
import com.deck_edit.edit_panel_controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.asset_controller.ImageButton;
import com.asset_controller.RW_csv;
import java.io.*;

// DeckEdit's Model & View & Controller
final public class DeckEditPanel extends JPanel implements ActionListener {
        FrameController frameCont;
        JButton end;
        DeckEditorModel EditDeck;
        AddOperation Addoperation;
        DeleteOperation Deleteoperation;
        ListUpdateAdd Listupdate_a;
        ListUpdateDelete Listupdate_d;
        ListUpdateLoad Listupdate_l;
        AddField addtodeck;
        DeleteField deletecardindeck;
        ShowCardListField showcardlist;
        SaveOperation saveOperation;
        LoadOperation loadOperation;
        SaveAndLoadField saveAndloadPanel;

/* デッキ判定に関する変数の宣言*/
        RW_csv DeckManege;
        int[] Inisialize;
        Boolean DeckExists;
        ArrayList<CardBase_E> ExistsDeck;
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;


        public DeckEditPanel(FrameController frameCont) {         // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;

                DeckManege=new RW_csv(new File("assets/csv/main_deck.csv"));
                Inisialize=DeckManege.ReadCSV();
                DeckExists=true;
                if(Inisialize==null) {
                        EditDeck=new DeckEditorModel();
                        DeckExists=false;
                }else{
                        for(int j=0; j<Inisialize.length && Inisialize[j]==0; j++) {
                                if(j==Inisialize.length-1) {
                                        DeckExists=false;
                                        EditDeck=new DeckEditorModel();
                                }
                        }
                        if(DeckExists==true) {
                                ExistsDeck=new ArrayList<CardBase_E>();
                                DeckReCreate(ExistsDeck, Inisialize);
                                EditDeck=new DeckEditorModel(ExistsDeck);
                        }
                }
                Deleteoperation=new DeleteOperation();
                Addoperation=new AddOperation();
                saveOperation=new SaveOperation();
                loadOperation=new LoadOperation();
                showcardlist=new ShowCardListField(EditDeck);
                addtodeck=new AddField(EditDeck, Addoperation, showcardlist);
                deletecardindeck=new DeleteField(EditDeck, Deleteoperation, showcardlist);
                saveAndloadPanel=new SaveAndLoadField(EditDeck, showcardlist, saveOperation, loadOperation);
                Listupdate_l=new ListUpdateLoad(showcardlist, loadOperation, EditDeck);
                Listupdate_a=new ListUpdateAdd(showcardlist, Addoperation, EditDeck);
                Listupdate_d=new ListUpdateDelete(showcardlist, Deleteoperation, EditDeck);
                end = new JButton("タイトルへ進む");
                this.setLayout(new BorderLayout());        //BorderLayoutに設定しなおした。(沢畑)
                this.add(end, BorderLayout.NORTH);
                this.add(addtodeck, BorderLayout.WEST);
                this.add(deletecardindeck, BorderLayout.SOUTH);
                this.add(showcardlist, BorderLayout.CENTER);
                this.add(saveAndloadPanel, BorderLayout.EAST);

                end.addActionListener(this);


        }

        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == end) {
                        // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
                        // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
                        frameCont.showTitle(this);
                }
        }

        public void DeckReCreate(ArrayList<CardBase_E> Deck, int[] List){
                for(int i=0; i<List.length; i++) {
                        switch(List[i]) {

                        case 1:
                                G=new Gu();
                                Deck.add(G);
                                break;
                        case 2:
                                P=new Pa();
                                Deck.add(P);
                                break;
                        case 3:
                                C=new Chi();
                                Deck.add(C);
                                break;
                        case 4:
                                GC=new G_C();
                                Deck.add(GC);
                                break;
                        case 5:
                                CP=new C_P();
                                Deck.add(CP);
                                break;
                        case 6:
                                PG=new P_G();
                                Deck.add(PG);
                                break;
                        case 7:
                                all=new ALL();
                                Deck.add(all);
                                break;
                        }
                }
        }
}
