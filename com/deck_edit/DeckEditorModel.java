package com.deck_edit;

import java.util.*;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import com.asset_controller.RW_csv;
import java.util.Comparator;
import java.util.Collections;
import java.io.*;
//デッキ編集画面のModelを担当 ここに編集に必要な操作がすべて入っている 基本的に
//デッキに操作を加えるメソッドには監視側に通知を行うようになっている
public class DeckEditorModel extends Observable {
        ArrayList<CardBase_E> MyDeck; //デッキそのもの
        Boolean[] Checker;//主にグーチー、チーパー、パーグー、グーチーパーの枚数制限をかけるための変数
        int countGC, countCP, countPG, countALL;//内部にある制限対象カードの枚数を格納するための変数
        int[] CardList;//カードリストをcsvに書き込むための配列
        RW_csv DeckManager;//セーブ、ロード用の操作のためのクラス
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//カード追加時に必要となる各種カードデータ
//Checker[0]:グーチー
//Checker[1]:チーパー
//Checker[2]:パーグー
//Checker[3]:グーチーパー
//各種カードの判定に対応する配列の要素。それぞれ、5枚、5枚、5枚、3枚を上限値として判定を行う。
        public DeckEditorModel(){//コンストラクタ. 各種変数の初期化を行う
                MyDeck=new ArrayList<CardBase_E>(0);
                Checker=new Boolean[4];
                CardList=new int[40];
                countGC=0; countCP=0; countPG=0; countALL=0;
                for(int i=0; i<4; i++) {
                        Checker[i]=true;
                }
                for(int i=0; i<40; i++) {
                        CardList[i]=0;//ダミーIDを初期値とする.
                }
                DeckManager=new RW_csv(new File("assets/csv/main_deck.csv"));
                ReConstarctDeck();//初期化時に再構成。カードリストがなければ初期値のまま、存在すれば更新する
                CountChecker();//一度カウンターをチェック
        }


        public void AddCardToDeck(CardBase_E Card){//デッキ追加用のメソッド
                if(Card.getID()==4) countGC++; //対象となるカードに合わせてカウンター増加
                if(Card.getID()==5) countCP++;
                if(Card.getID()==6) countPG++;
                if(Card.getID()==7) countALL++;
                MyDeck.add(Card);
                setChanged();
                notifyObservers();
        }
        public CardBase_E getCard(int number){//デッキからカードの情報を取得するためのメソッド
                return MyDeck.get(number);
        }
        public void deleteCard(int number){//デッキ内部のカードの削除用のメソッド
                int ID=getCard(number).getID();
                if(ID==4) countGC--; //対象となるカードに合わせてカウンター減少
                if(ID==5) countCP--;
                if(ID==6) countPG--;
                if(ID==7) countALL--;
                MyDeck.remove(number);
                setChanged();
                notifyObservers();
        }
        public void CountChecker(){//カウンターをチェックするためのメソッド　対象の値に合わせてBoolean型の値を変化させる
                if(countGC>=5) Checker[0]=false; else Checker[0]=true;
                if(countCP>=5) Checker[1]=false; else Checker[1]=true;
                if(countPG>=5) Checker[2]=false; else Checker[2]=true;
                if(countALL>=3) Checker[3]=false; else Checker[3]=true;
                //一定値以上の場合false(=追加不可能)とする
        }
        public Boolean[] getChekerList(){
                return Checker;//現在のChekerを返す
        }

        public ArrayList<CardBase_E> CheckDeck(){//デッキが存在するかどうかの確認
                if(MyDeck.size()==0) {
                        return null;//存在しない
                }
                return MyDeck;//デッキそのものを値として返す。
        }

        public void CardSort(){//ソートを行うメソッド デッキシャッフル機能が搭載されれば実装予定
                Collections.sort(MyDeck, new CardIDComparator());
                setChanged();
                notifyObservers();
        }


        public void ChangeList(){//デッキ内部のカードデータをIDを元にint型配列に落とす
                if(CheckDeck()!=null) {
                        int i=0;
                        for(CardBase_E Card: MyDeck) {
                                CardList[i]=Card.getID();
                                i++;
                        }
                }
        }

        public void SaveDeck(){//セーブ操作
                ChangeList();
                DeckManager.WriteCSV(CardList);
        }
        public void LoadDeck(){//ロード操作
                int[] fromLoad;
                fromLoad=DeckManager.ReadCSV();
                if(CheckDeck()!=null) {//内部にカードが残っている場合、一度中身をリセット
                        MyDeck.clear();
                }
                if(fromLoad!=null) {
                        for(int i=0; i<fromLoad.length; i++) {
                                switch(fromLoad[i]) { //IDの値で判定

                                case 1:
                                        G=new Gu();
                                        AddCardToDeck(G);
                                        break;
                                case 2:
                                        P=new Pa();
                                        AddCardToDeck(P);
                                        break;
                                case 3:
                                        C=new Chi();
                                        AddCardToDeck(C);
                                        break;
                                case 4:
                                        GC=new G_C();
                                        AddCardToDeck(GC);
                                        break;
                                case 5:
                                        CP=new C_P();
                                        AddCardToDeck(CP);
                                        break;
                                case 6:
                                        PG=new P_G();
                                        AddCardToDeck(PG);
                                        break;
                                case 7:
                                        all=new ALL();
                                        AddCardToDeck(all);
                                        break;
                                }
                        }
                        for(int i=0; i<40; i++) {//一度データリストを初期化
                                CardList[i]=0;
                        }
                        ChangeList();//再度落とし込む
                }
                setChanged();
                notifyObservers();
        }

        public void ReConstarctDeck(){//初期化時に行う再構成メソッド 基本的にはLoadDeckと同じだが、
                int[] Initialize;         //全てが0の場合ロードを行わない, 通知を行わないという点で異なる
                Boolean Exists;
                Initialize=DeckManager.ReadCSV();
                Exists=true;
                if(Initialize!=null) {
                        for(int i=0; i<Initialize.length && Initialize[i]==0; i++) {
                                if(i==Initialize.length-1) {
                                        Exists=false;
                                }
                        }
                        if(Exists==true) {
                                for(int i=0; i<Initialize.length; i++) {
                                        switch(Initialize[i]) {

                                        case 1:
                                                G=new Gu();
                                                AddCardToDeck(G);
                                                break;
                                        case 2:
                                                P=new Pa();
                                                AddCardToDeck(P);
                                                break;
                                        case 3:
                                                C=new Chi();
                                                AddCardToDeck(C);
                                                break;
                                        case 4:
                                                GC=new G_C();
                                                AddCardToDeck(GC);
                                                break;
                                        case 5:
                                                CP=new C_P();
                                                AddCardToDeck(CP);
                                                break;
                                        case 6:
                                                PG=new P_G();
                                                AddCardToDeck(PG);
                                                break;
                                        case 7:
                                                all=new ALL();
                                                AddCardToDeck(all);
                                                break;
                                        }
                                }
                        }
                }
        }

        public void Inform(){
                //ただ何もせず通知を行うメソッド　初期化時に制限対象の追加ボタンを動作させないために宣言した
                setChanged();
                notifyObservers();
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
