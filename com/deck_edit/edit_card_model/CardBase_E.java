package com.deck_edit.edit_card_model;
//Edit用のカードモデル。MainGame側のカードモデルと互換性をもたせること。
import java.util.*;

public class CardBase_E {
        private String name;//カードの名前
        private int ID;//カードの識別用,MainGame側と同様の値を振り分ける。
        /*必要があればカードごとにデッキに組み込める上限を設定するための値を設定*/

        public String getCardName(){
                return name;
        }
        public void setCardName(String CardName){
                name=CardName;
        }
        public int getID(){
                return ID;
        }
        public void setID(int ID){
                this.ID=ID;
        }
/*  public int getCost(){
    return cost;
   }上限値のために必要な値を返すMethod*/
}
