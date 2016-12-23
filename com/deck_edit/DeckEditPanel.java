package com.deck_edit;

import com.FrameController;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class AddOperation extends Observable {//追加操作のModel

        public void AddCardtoDeck(CardBase_E Card, ArrayList<CardBase_E> AddDeck){
                AddDeck.setCardToDeck(Card);
                setChanged();
                notifyObservers();
        }
}

class AddToDeck extends JPanel implements ActionListener {//デッキ編集:追加操作のviewとcontroller
        JButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
        //JLabel ex_Gu, ex_Chi, ex_Pa, ex_G_C, ex_C_P, ex_P_G, ex_ALL; //カード説明;
        JPanel AddCard;
        ArrayList<CardBase_E> MyDeck;//デッキにカードを追加するための変数
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//各種カードデータ
        AddOperation addOperation;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

        public AddToDeck(ArrayList<CardBase_E> MyDeck, AddOperation ado){//追加操作パネルのVCコンストラクタ デッキを値として引き渡す。
                Gu=new JButton("グー"); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
                Pa=new JButton("パー"); Pa.addActionListener(this);
                Chi=new JButton("チー"); Chi.addActionListener(this);
                G_C=new JButton("グーチー"); G_C.addActionListener(this);
                C_P=new JButton("チーパー"); C_P.addActionListener(this);
                P_G=new JButton("パーグー"); P_G.addActionListener(this);
                ALL=new JButton("グーチーパー"); ALL.addActionListener(this);

                this.MyDeck=MyDeck;//引き渡された値を代入
                addOperation=ado;
                /*ここからはカードの説明の記述　出来れば各カードクラスからデータを返してもらえるようにする
                   例えば,Gu.setDiscribe()として記述を返し、setTextで表示させる.*/
                /*ex_Gu=new JLabel(); ex_Chi=new JLabel(); ex_Pa=new JLabel();
                   ex_G_C=new JLabel(); ex_C_P=new JLabel(); ex_P_G=new JLabel();
                   ex_ALL=new JLabel();*/
                /*ここにsetTextを用いた説明の記述を表示する.もしくは上記の初期化宣言時に引数として文字を渡すようにする */

                AddCard.setLayout(new GridLayout(7,1));//レイアウト設定　今回はGridLayoutを用いる
                AddCard.add(Gu); //AddCard.add(ex_Gu);
                AddCard.add(Chi); //AddCard.add(ex_Chi);
                AddCard.add(Pa); //AddCard.add(ex_Pa);
                AddCard.add(G_C); //AddCard.add(ex_G_C);
                AddCard.add(C_P); //AddCard.add(ex_C_P);
                AddCard.add(P_G); //AddCard.add(ex_P_G);
                AddCard.add(ALL); //AddCard.add(ex_ALL);
        }

        public void actionPerformed(ActionEvent e){
                /*デッキ追加時のカードの判定部分　switch文は定数のみでしか構成できないので長いがif文で構成*/
                if(MyDeck.CheckDeck().size()<=20) {
                        if(e.getSource()==Gu) {
                                G=new Gu();
                                AdO.AddCardtoDeck(G, MyDeck);
                        }
                        if(e.getSource()==Pa) {
                                P=new Pa();
                                AdO.AddCardtoDeck(P, MyDeck);
                        }
                        if(e.getSource()==Chi) {
                                C=new Chi();
                                AdO.AddCardtoDeck(C, MyDeck);
                        }
                        if(e.getSource()==G_C) {
                                GC=new G_C();
                                AdO.AddCardtoDeck(GC, MyDeck);
                        }
                        if(e.getSource()==C_P) {
                                CP=new C_P();
                                AdO.AddCardtoDeck(CP, MyDeck);
                        }
                        if(e.getSource()==P_G) {
                                PG=new P_G();
                                AdO.AddCardtoDeck(PG, MyDeck);
                        }
                        if(e.getSource()==ALL) {
                                all=new ALL();
                                AdO.AddCardtoDeck(all, MyDeck);
                        }
                }else{//デッキ上限を超えた場合は追加をせずメッセージを表示する。
                        ShowCardList.List[20].setText("追加はできません");
                }
        }
}

class DeleteOperation extends Observable {

        public void DeleteCardforDeck(int number, ArrayList<CardBase_E> MyDeck){
                MyDeck.deleteCard(number);
                setChanged();
                notifyObservers();
        }
}

class DeleteCardInDeck extends JPanel implements ActionListener {//削除操作を行うパネルのMVC
        int number;
        JButton delete;
        ArrayList<CardBase_E> MyDeck;
        static JTextField delNumber;//リスト整理用に外部アクセスを可能としてある。
        JPanel DeleteCard;
        DeleteOperation deleteOperation;

        public DeleteCardInDeck(ArrayList<CardBase_E> MyDeck, DeleteOperation dO){
                delete=new JButton("番目を削除");
                delNumber=new JTextField(2);
                DeleteCard=new JPanel();
                this.MyDeck=MyDeck;
                DeleteCard.setLayout(new GridLayout(1,2));
                DeleteCard.add(delete);
                DeleteCard.add(delNumber);
                delete.addActionListener(this);
                deleteOperation=dO;
        }

        public void actionPerformed(ActionEvent e){
                DO.DeleteCardforDeck(Integer.parseInt(delNumber.getText())+1);
        }
}

class ShowCardList extends JPanel implements Observer {
        DeleteOperation deleteOperation;
        AddOperation addOperation;
        JLabel[] List;//デッキ内部表示用のJLabel配列
        JLabel NoneDeck;//デッキが存在しない時の表示用ラベル
        String list;//リスト文字列格納用変数
        ArrayList<CardBase_E> MyDeck;

        public ShowCardList(AddOperation ao, DeleteOperation delO, ArrayList<CardBase_E> Deck){
                List=new JLabel[21];//20番目はメッセージ用
                for(int j=0; j<21; j++) {
                        List[j]=new JLabel();
                }
                list=new String();
                this.setLayout(new GridLayout(21,1));
                addOperation=ao; deleteOperation=delO;
                MyDeck=Deck;
                addOperation.addObserver(this); deleteOperation.addObserver(this);//それぞれの操作時にupdateを実行する。
                for(int j=0; j<20; j++) {
                        this.add(List[j]);
                }
                if(MyDeck.CheckDeck()!=null) {//初期化時にデッキが存在する場合。
                        for(int j=0; j<MyDeck.CheckDeck().size(); j++) {
                                list=Integer.toString(j+1);
                                list=list+". "+MyDeck.getCard(j).getCardName();
                                List[j].setText(list);
                                if(j==19) {
                                        List[20].setText("カードをこれ以上追加できません.");//最大に達した場合のメッセージ
                                }
                        }
                }else if(MyDeck.CheckDeck()==null) {
                        List[0].setText("カードが存在しません".);
                        //デッキが存在しない場合メッセージを表示
                }
        }

        public void update(Observable O, Object arg){
                int i;

                if(O.getClass().getSimpleName()==addOperation.getClass().getSimpleName()) {//updateメソッドの引数として渡しているクラス名で判定する.
                        i=MyDeck.CheckDeck().size();
                        list=Integer.toString(i);
                        list=list+". "+DeckEditPanel.EditDeck.getCard(i-1).getCardName();
                        List[i-1].setText(list);
                        if(i==20) {
                                List[21].setText("カードをこれ以上追加できません.")
                        }
                }else if(O.getClass().getSimpleName()==deleteOperation.getClass().getSimpleName()) {
                        List[20].setText("");//メッセージの表記を消す。
                        i=MyDeck.CheckDeck().size();
                        if(i==0) {//0枚の時はないことを知らせる。
                                List[i].setText("カードが存在しません.");
                        }
                        for(int n=Integer.parseInt(DeleteCardInDeck.delNumber.getText())-1; n<i; n++) {//削除項目以降の再表記
                                list=Integer.toString(n);
                                list=n+". "+DeckEditPanel.EditDeck.getCard(n).getCardName();
                                List[n].setText(list);
                        }
                }
        }
}

// DeckEdit's Model & View & Controller
final public class DeckEditPanel extends JPanel implements ActionListener {
        FrameController frameCont;
        JButton end;
        DeckEditorModel EditDeck;//デッキ編集を行うのでstatic
        AddOperation Addoperation;
        DeleteOperation Deleteoperation;
        AddToDeck addtodeck;
        DeleteCardInDeck deletecardindeck;
        ShowCardList showcardlist;


        public DeckEditPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;
                EditDeck=new DeckEditorModel();//デッキ編集済みかどうかの判定は実際必要。FrameController側で判定値を作っておくべきかも。
                Deleteoperation=new DeleteOperation();
                Addoperation=new AddOperation();
                addtodeck=new AddToDeck(Addoperation, EditDeck);
                deletecardindeck=new DeleteCardInDeck(Deleteoperation, EditDeck);
                showcardlist=new ShowCardList(Addoperation, Deleteoperation, EditDeck);
                end = new JButton("タイトルへ進む");
                this.setLayout(new BorderLayout());//BorderLayoutに設定しなおした。(沢畑)
                this.add(end, BorderLayout.NORTH);
                this.add(addtodeck, BorderLayout.WEST);
                this.add(deletecardindeck, BorderLayout.SOUTH);
                this.add(showcardlist, BorderLayout.CENTER);

                end.addActionListener(this);


        }

        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == end) {
                        // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
                        // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
                        frameCont.showTitle(this);
                }
        }
}
