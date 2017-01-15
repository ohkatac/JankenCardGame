package com.deck_edit.edit_panel_parts;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_panel_model.DeletePanelModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* ShowCardListPanel上で表示するIconのベースとなるVCクラス。引数はCardBase_Eと
   削除操作を行うためにDeletePanelModelを用いる*/

public class CardPanelBase extends JLabel implements MouseListener {
        String[] ImagePath;//アイコン画像ファイルへのパス
        CardBase_E DeckData;
        DeckEditorModel MyDeck;
        ImageIcon Normal, Pressed, Hover;//各種状態の画像パス
        ShowCardListPanel showCardlistPanel;
        DeletePanelModel DeleteOperation;
        int number;//パネル番号(=デッキ内の順番)
        /* ここに説明記述を行うパネルのオブジェクト変数を宣言する */

        public CardPanelBase(CardBase_E Data, DeletePanelModel dpm, ShowCardListPanel sc,  DeckEditorModel Deck, int number){ //さらに引数として説明記述を行うパネルのオブジェクトを入れる
                DeckData=Data;
                MyDeck=Deck;
                DeleteOperation=dpm;
                showCardlistPanel=sc;
                this.number=number;
                ImagePath=DeckData.getPath();
                Normal=new ImageIcon(ImagePath[0]);
                Pressed=new ImageIcon(ImagePath[1]);
                Hover=new ImageIcon(ImagePath[2]);
                //さらに説明記述を行うパネルのオブジェクトを代入
                this.setIcon(Normal);//ラベルに画像を表示
                addMouseListener(this);
        }


        public void mouseClicked(MouseEvent e){
                if(e.getButton()==MouseEvent.BUTTON2)
                        DeleteOperation.DeleteCardforDeck(number, MyDeck);
                showCardlistPanel.ChangeMessage("カードを削除しました");
        }

        public void mouseEntered(MouseEvent e){
                showCardlistPanel.ChangeDescribe(DeckData.getDescribe());
                this.setIcon(Hover);
        }

        public void mouseExited(MouseEvent e){
                showCardlistPanel.ChangeDescribe(" ");
                this.setIcon(Normal);
        }

        public void mousePressed(MouseEvent e){
                this.setIcon(Pressed);
        }

        public void mouseReleased(MouseEvent e){
                this.setIcon(Normal);
        }
}
