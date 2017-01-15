package com.deck_edit;

import java.util.*;
import com.deck_edit.edit_card_model.*;
import java.util.Comparator;
import java.util.Collections;

public class DeckEditorModel {
        private ArrayList<CardBase_E> MyDeck;
        private Boolean[] Checker;//主にグーチー、チーパー、パーグー、グーチーパーの枚数制限をかけるための変数
        private int countGC, countCP, countPG, countALL;
//Checker[0]:グーチー
//Checker[1]:チーパー
//Checker[2]:パーグー
//Checker[3]:グーチーパー
//各種カードの判定に対応する配列の要素。それぞれ、5枚、5枚、5枚、3枚を上限値として判定を行う。
        public DeckEditorModel(){//デッキが存在しない場合や、デッキの内部が空の場合のコンストラクタ
                MyDeck=new ArrayList<CardBase_E>(0);
                Checker=new Boolean[4];
                for(int i=0; i<4; i++) {
                        Checker[i]=true;
                }
        }

        public DeckEditorModel(ArrayList<CardBase_E> currentDeck){//デッキが存在する場合のコンストラクタ
                MyDeck=currentDeck;
                Checker=new Boolean[4];
                for(int i=0; i<4; i++) {
                        Checker[i]=true;
                }
                CheckCard();
        }

        public void setCardToDeck(CardBase_E Card){//デッキ追加用のメソッド
                MyDeck.add(Card);
        }
        public CardBase_E getCard(int number){//デッキからカードの情報を取得するためのメソッド
                return MyDeck.get(number);
        }
        public void deleteCard(int number){//デッキ内部のカードの削除用のメソッド
                MyDeck.remove(number);
        }

        public ArrayList<CardBase_E> CheckDeck(){//デッキが存在するかどうかの確認
                if(MyDeck.size()==0) {
                        return null;//存在しない
                }
                return MyDeck;//デッキそのものを値として返す。
        }

        public void CardSort(){//ソートを行うメソッド
                Collections.sort(MyDeck, new CardIDComparator());
        }

        public void CheckCard(){ //デッキの内部のカードの制限を確認するためのメソッド。
                countGC=0; countCP=0; countPG=0; countALL=0;
                for(CardBase_E Card: MyDeck) {
                        if(Card.getID()==4) countGC++;
                        if(Card.getID()==5) countCP++;
                        if(Card.getID()==6) countPG++;
                        if(Card.getID()==7) countALL++;
                }
                if(countGC==5) Checker[0]=false;
                if(countCP==5) Checker[1]=false;
                if(countPG==5) Checker[2]=false;
                if(countALL==3) Checker[3]=false;
        }

        public Boolean[] getChekerList(){ //引数を持たない場合の判定値を返すメソッド。返し値はBoolean型配列。
                return Checker;
        }
        public Boolean getChekerList(int ID){ //引数を持つ場合の判定値を返す。引数(4～7)に対応して返す値を変える。返し値はBoolean型
                return Checker[ID-4];

        }

}

class CardIDComparator implements Comparator<CardBase_E>{ //ソート用クラス
        public int compare(CardBase_E a, CardBase_E b){
                int ID1=a.getID();
                int ID2=b.getID();

                if(ID1>ID2) {
                        return 1;
                }else if(ID1==ID2) {
                        return 0;
                }else{
                        return -1;
                }
        }
}
