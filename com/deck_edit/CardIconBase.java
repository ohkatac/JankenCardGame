package com.deck_edit;

import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.deck_edit.DeckEditorModel;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//  デッキ編集画面上のカードリストのアイコン兼削除ボタンを担当するView&Contorller ただし再描画や
//  画像のクリアを行うメソッドはここでは直接呼び出されない
public class CardIconBase extends JLabel implements MouseListener {
        int number;//デッキ内での番号
        DeckEditorModel MyDeck;//削除操作用
        CardBase_E Card;//アイコンのパスを確保するため
        String[] ImagePath;//アイコンとなる画像のパス群
        ImageIcon Normal, Pressed, Hover;//それぞれの状態に対応するアイコン
        //Normal: 通常時に表示する画像
        //Pressed: 押しているときに表示する画像
        //Hover: マウスが上に来た時に表示する画像

        public CardIconBase(int number, DeckEditorModel Deck){
                this.number=number; //デッキ内での番号を格納
                MyDeck=Deck; //削除操作を行うので引数を格納、保存
                ImagePath=new String[4];
                setIcon(null);//念のため、JLabelのアイコンを再初期化
                Card=null;//一度内部をnullにしておく
                setOpaque(false);
        }


        public void setCardIcon(){ //アイコンをセットするメソッド
                Card=MyDeck.getCard(number);//カード情報を格納
                ImagePath=Card.getPath();//対応する画像のパスを格納 詳細はCardBase_E.javaを参照
                Normal=new ImageIcon(ImagePath[0]);//ImageIconコンストラクタを実行
                Pressed=new ImageIcon(ImagePath[1]);
                Hover=new ImageIcon(ImagePath[2]);
                setIcon(Normal);//通常時アイコンをセット
                addMouseListener(this);//操作対象に加える
        }

        public void IconClear(){ //アイコンを解除するメソッド
                setIcon(null);//JLabelのImageIconの値を初期化 非表示にする
                Card=null;//アイコン表示に必要な値を一度すべてクリアする
                ImagePath=null;
                Normal=null;
                Pressed=null;
                Hover=null;
                removeMouseListener(this);//操作対象から除外する
        }

        public void mouseClicked(MouseEvent e){
                if(e.getButton()==MouseEvent.BUTTON3) { //右クリックかどうかを判定
                        MyDeck.deleteCard(number); //削除操作を呼び出す
                }
        }
/* 上に来たときや押されたとき等の表示の変化 */
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
