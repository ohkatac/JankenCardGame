package com.deck_edit;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardIconBase extends JLabel implements MouseListener {
        int number;
        DeckEditorModel MyDeck;//削除操作用
        CardBase_E Card;
        String[] ImagePath;
        ImageIcon Normal, Pressed, Hover;

        public CardIconBase(int number, DeckEditorModel Deck){
                this.number=number;
                MyDeck=Deck;
                ImagePath=new String[4];
                setIcon(null);
                Card=null;
        }


        public void setCardIcon(){ //アイコンをセットするメソッド
                Card=MyDeck.getCard(number);
                ImagePath=Card.getPath();
                Normal=new ImageIcon(ImagePath[0]);
                Pressed=new ImageIcon(ImagePath[1]);
                Hover=new ImageIcon(ImagePath[2]);
                setIcon(Normal);
                addMouseListener(this);
        }

        public void IconClear(){ //アイコンを解除するメソッド
                setIcon(null);
                Normal=null;
                Pressed=null;
                Hover=null;
                removeMouseListener(this);
        }

        public void mouseClicked(MouseEvent e){
                if(e.getButton()==MouseEvent.BUTTON3) {
                        MyDeck.deleteCard(number);
                }
        }

        public void mousePressed(MouseEvent e){
                setIcon(Pressed);
        }
        public void mouseReleased(MouseEvent e){
                setIcon(Hover);
        }
        public void mouseEntered(MouseEvent e){
                setIcon(Hover);
        }
        public void mouseExited(MouseEvent e){
                setIcon(Normal);
        }
}
