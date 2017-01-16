package com.deck_edit.edit_card_model;
//Edit用のカードモデル。MainGame側のカードモデルと互換性をもたせること。
import java.util.*;

public class CardBase_E {
        private String name;//カードの名前
        private int ID;//カードの識別用,MainGame側と同様の値を振り分ける。
        private String[] IconImagePath;//各種追加ボタンやパネルのアイコン生成時用
        private String Describe;//カード間の相性を記述する 必要になれば一応使えるようにはした

        public CardBase_E(){
                IconImagePath=new String[3]; //継承先カード各種の初期化時に必ず行われる
        }

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
        public void setDescribe(String content){
                Describe=content;
        }
        public String getDescribe(){
                return Describe;
        }
        public void setPath(String NormalPath, String PressPath, String HoverPath){
                IconImagePath[0]=NormalPath;//通常時
                IconImagePath[1]=PressPath;//押したとき
                IconImagePath[2]=HoverPath;//マウスが重なったとき
        }
        public String[] getPath(){
                return IconImagePath;
        }//パスが書かれた配列を返す

}
