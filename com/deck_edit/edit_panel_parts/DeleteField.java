package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_parts.ShowCardListField;
import com.deck_edit.edit_panel_controller.DeleteOperation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DeleteField extends JPanel implements ActionListener {//削除操作を行うパネルのMVC
        int number;
        JButton delete;
        DeckEditorModel MyDeck;
        JTextField delNumber;
        DeleteOperation deleteOperation;
        ShowCardListField showCardList;

        public DeleteField(DeckEditorModel MyDeck, DeleteOperation dO, ShowCardListField sc){
                delete=new JButton("番目を削除");
                delNumber=new JTextField(2);
                this.MyDeck=MyDeck;
                this.setLayout(new GridLayout(1,2));
                this.add(delNumber);
                this.add(delete);
                delete.addActionListener(this);
                deleteOperation=dO;
                showCardList=sc;
        }

        public void actionPerformed(ActionEvent e){
                int j;
                j=Integer.parseInt(delNumber.getText());
                if(showCardList.getDeckSize()>0) {
                        deleteOperation.DeleteCardforDeck(j-1, MyDeck);
                        if(MyDeck.CheckDeck()==null) {
                                showCardList.setDeckSize(0);
                        }else{
                                showCardList.setDeckSize(MyDeck.CheckDeck().size());
                        }
                }
        }
}
