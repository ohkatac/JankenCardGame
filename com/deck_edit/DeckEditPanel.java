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

        public int AddCardtoDeck(CardBase_E Card, DeckEditorModel AddDeck){
                AddDeck.setCardToDeck(Card);
                setChanged();
                notifyObservers();

                return AddDeck.CheckDeck().size();
        }
}

class AddToDeck extends JPanel implements ActionListener {//デッキ編集:追加操作のviewとcontroller
        JButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
        //JLabel ex_Gu, ex_Chi, ex_Pa, ex_G_C, ex_C_P, ex_P_G, ex_ALL; //カード説明;
        DeckEditorModel MyDeck;//デッキにカードを追加するための変数
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//各種カードデータ
        AddOperation addOperation;
        int size;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

        public AddToDeck(DeckEditorModel MyDeck, AddOperation ado){//追加操作パネルのVCコンストラクタ デッキを値として引き渡す。
                Gu=new JButton("グー"); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
                Pa=new JButton("パー"); Pa.addActionListener(this);
                Chi=new JButton("チー"); Chi.addActionListener(this);
                G_C=new JButton("グーチー"); G_C.addActionListener(this);
                C_P=new JButton("チーパー"); C_P.addActionListener(this);
                P_G=new JButton("パーグー"); P_G.addActionListener(this);
                ALL=new JButton("グーチーパー"); ALL.addActionListener(this);

                this.MyDeck=MyDeck;//引き渡された値を代入
                addOperation=ado;
                size=0;
                /*ここからはカードの説明の記述　出来れば各カードクラスからデータを返してもらえるようにする
                   例えば,Gu.setDiscribe()として記述を返し、setTextで表示させる.*/
                /*ex_Gu=new JLabel(); ex_Chi=new JLabel(); ex_Pa=new JLabel();
                   ex_G_C=new JLabel(); ex_C_P=new JLabel(); ex_P_G=new JLabel();
                   ex_ALL=new JLabel();*/
                /*ここにsetTextを用いた説明の記述を表示する.もしくは上記の初期化宣言時に引数として文字を渡すようにする */

                this.setLayout(new GridLayout(7,1) );//レイアウト設定　今回はGridLayoutを用いる
                this.add(Gu); //AddCard.add(ex_Gu);
                this.add(Chi); //AddCard.add(ex_Chi);
                this.add(Pa); //AddCard.add(ex_Pa);
                this.add(G_C); //AddCard.add(ex_G_C);
                this.add(C_P); //AddCard.add(ex_C_P);
                this.add(P_G); //AddCard.add(ex_P_G);
                this.add(ALL); //AddCard.add(ex_ALL);
        }

        public void actionPerformed(ActionEvent e){
                /*デッキ追加時のカードの判定部分　switch文は定数のみでしか構成できないので長いがif文で構成*/
                if(size<20) {
                        if(e.getSource()==Gu) {
                                G=new Gu();
                                size=addOperation.AddCardtoDeck(G, MyDeck);
                        }
                        if(e.getSource()==Pa) {
                                P=new Pa();
                                size=addOperation.AddCardtoDeck(P, MyDeck);
                        }
                        if(e.getSource()==Chi) {
                                C=new Chi();
                                size=addOperation.AddCardtoDeck(C, MyDeck);
                        }
                        if(e.getSource()==G_C) {
                                GC=new G_C();
                                size=addOperation.AddCardtoDeck(GC, MyDeck);
                        }
                        if(e.getSource()==C_P) {
                                CP=new C_P();
                                size=addOperation.AddCardtoDeck(CP, MyDeck);
                        }
                        if(e.getSource()==P_G) {
                                PG=new P_G();
                                size=addOperation.AddCardtoDeck(PG, MyDeck);
                        }
                        if(e.getSource()==ALL) {
                                all=new ALL();
                                size=addOperation.AddCardtoDeck(all, MyDeck);
                        }
                }

        }
}

class DeleteOperation extends Observable {

        public int DeleteCardforDeck(int number, DeckEditorModel MyDeck){
                int i;

                MyDeck.deleteCard(number);
                System.out.println(number);
                setChanged();
                notifyObservers();
                i=MyDeck.CheckDeck().size();

                return i;
        }
}

class DeleteCardInDeck extends JPanel implements ActionListener {//削除操作を行うパネルのMVC
        int number;
        JButton delete;
        DeckEditorModel MyDeck;
        static JTextField delNumber;//リスト整理用に外部アクセスを可能としてある。
        DeleteOperation deleteOperation;
        ShowCardList showCardList;

        public DeleteCardInDeck(DeckEditorModel MyDeck, DeleteOperation dO){
                delete=new JButton("番目を削除");
                delNumber=new JTextField(2);
                this.MyDeck=MyDeck;
                this.setLayout(new GridLayout(1,2));
                this.add(delNumber);
                this.add(delete);
                delete.addActionListener(this);
                deleteOperation=dO;
                number=0;
        }

        public void actionPerformed(ActionEvent e){
                int j;
                j=Integer.parseInt(delNumber.getText());
                number=MyDeck.CheckDeck().size();
                if(number>0) {
                        number=deleteOperation.DeleteCardforDeck(j-1, MyDeck);
                }
        }
}

class ListUpdate_Add implements Observer {//追加操作に対するObserver. Observerパターンの都合により分離.
        ShowCardList showCardList;//リスト操作用
        AddOperation addoperation;
        String addList;//リストに表示する文
        DeckEditorModel MyDeck;

        public ListUpdate_Add(ShowCardList sl, AddOperation ao, DeckEditorModel Deck){
                showCardList=sl;
                addoperation=ao;
                addList=" ";
                MyDeck=Deck;
                addoperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                int i;
                i=MyDeck.CheckDeck().size();
                if(i==20) {
                        showCardList.List[i].setText("これ以上カードを追加できません.");
                }
                addList=Integer.toString(i);
                addList=addList+". "+MyDeck.getCard(i-1).getCardName();
                showCardList.List[i-1].setText(addList);

        }

}

class ListUpdate_Del implements Observer {//削除操作に対するObserver. Observerパターンの都合により分離.
        ShowCardList Showcardlist;
        DeleteOperation deloperation;
        String delList;//リストに表示する文
        DeckEditorModel MyDeck;

        public ListUpdate_Del(ShowCardList sl, DeleteOperation delo, DeckEditorModel Deck){
                Showcardlist=sl;
                deloperation=delo;
                delList=" ";
                MyDeck=Deck;
                deloperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                for(int i=0; i<=20; i++) {
                        Showcardlist.List[i].setText(" ");
                }
                for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                        delList=Integer.toString(i+1);
                        delList=delList+". "+MyDeck.getCard(i).getCardName();
                        Showcardlist.List[i].setText(delList);
                        System.out.println(MyDeck.getCard(i).getCardName());
                }
        }
}

class ShowCardList extends JPanel {
        static JLabel[] List;//デッキ内部表示用のJLabel配列
        JLabel NoneDeck;//デッキが存在しない時の表示用ラベル
        String list;//リスト文字列格納用変数
        DeckEditorModel MyDeck;

        public ShowCardList(DeckEditorModel Deck){
                List=new JLabel[21];//20番目はメッセージ用
                for(int j=0; j<21; j++) {
                        List[j]=new JLabel();
                }
                list=new String();
                this.setLayout(new GridLayout(21,1));
                MyDeck=Deck;
                for(int j=0; j<21; j++) {
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
                        List[0].setText("カードが存在しません.");
                        //デッキが存在しない場合メッセージを表示
                }
        }


}

// DeckEdit's Model & View & Controller
final public class DeckEditPanel extends JPanel implements ActionListener {
        FrameController frameCont;
        JButton end;
        DeckEditorModel EditDeck;
        AddOperation Addoperation;
        DeleteOperation Deleteoperation;
        ListUpdate_Add Listupdate_a;
        ListUpdate_Del Listupdate_d;
        AddToDeck addtodeck;
        DeleteCardInDeck deletecardindeck;
        ShowCardList showcardlist;


        public DeckEditPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;
                EditDeck=new DeckEditorModel();//デッキ編集済みかどうかの判定は実際必要。FrameController側で判定値を作っておくべきかも。
                Deleteoperation=new DeleteOperation();
                Addoperation=new AddOperation();
                addtodeck=new AddToDeck(EditDeck, Addoperation);
                showcardlist=new ShowCardList(EditDeck);
                deletecardindeck=new DeleteCardInDeck(EditDeck, Deleteoperation);
                Listupdate_a=new ListUpdate_Add(showcardlist, Addoperation, EditDeck);
                Listupdate_d=new ListUpdate_Del(showcardlist, Deleteoperation, EditDeck);
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
