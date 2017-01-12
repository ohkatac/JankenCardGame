package com.deck_edit;

import com.FrameController;
import com.deck_edit.DeckEditorModel;
import com.deck_edit.edit_card_model.*;
import com.deck_edit.edit_card_model.various_card.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.asset_controller.ImageButton;
import com.asset_controller.RW_csv;
import java.io.*;

class AddOperation extends Observable {//追加操作のModel

        public void AddCardtoDeck(CardBase_E Card, DeckEditorModel AddDeck){
                AddDeck.setCardToDeck(Card);
                setChanged();
                notifyObservers();

        }
}

class AddToDeck extends JPanel implements ActionListener {//デッキ編集:追加操作のviewとcontroller
        ImageButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
        JLabel ex_Gu, ex_Chi, ex_Pa, ex_G_C, ex_C_P, ex_P_G, ex_ALL; //カード説明;
        DeckEditorModel MyDeck;//デッキにカードを追加するための変数
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;//各種カードデータ
        AddOperation addOperation;
        ShowCardList showCardList;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

        public AddToDeck(DeckEditorModel MyDeck, AddOperation ado, ShowCardList sc){//追加操作パネルのVCコンストラクタ デッキを値として引き渡す。
                Gu=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
                Pa=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Pa.addActionListener(this);
                Chi=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); Chi.addActionListener(this);
                G_C=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); G_C.addActionListener(this);
                C_P=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); C_P.addActionListener(this);
                P_G=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); P_G.addActionListener(this);
                ALL=new ImageButton(new String[] {
                        "assets/img/button/redButton.png",
                        "assets/img/button/pinkButton.png",
                        "assets/img/button/greenButton.png",
                        "asstes/img/button/blackButton.png"
                }); ALL.addActionListener(this);

                this.MyDeck=MyDeck;//引き渡された値を代入
                addOperation=ado;
                showCardList=sc;
                /*ここはカードの名前の記述*/
                ex_Gu=new JLabel("グー"); ex_Chi=new JLabel("チー"); ex_Pa=new JLabel("パー");
                ex_G_C=new JLabel("グーチー"); ex_C_P=new JLabel("チーパー"); ex_P_G=new JLabel("パーグー");
                ex_ALL=new JLabel("グーチーパー");


                this.setLayout(new GridLayout(7,2));//レイアウト設定　今回はGridLayoutを用いる
                this.add(ex_Gu); this.add(Gu); //AddCard.add(ex_Gu);
                this.add(ex_Chi); this.add(Chi); //AddCard.add(ex_Chi);
                this.add(ex_Pa); this.add(Pa); //AddCard.add(ex_Pa);
                this.add(ex_G_C); this.add(G_C); //AddCard.add(ex_G_C);
                this.add(ex_C_P); this.add(C_P); //AddCard.add(ex_C_P);
                this.add(ex_P_G); this.add(P_G); //AddCard.add(ex_P_G);
                this.add(ex_ALL); this.add(ALL); //AddCard.add(ex_ALL);
        }

        public void actionPerformed(ActionEvent e){
                /*デッキ追加時のカードの判定部分　switch文は定数のみでしか構成できないので長いがif文で構成*/
                if(showCardList.getDeckSize()<20) {
                        if(e.getSource()==Gu) {
                                G=new Gu();
                                addOperation.AddCardtoDeck(G, MyDeck);
                        }
                        if(e.getSource()==Pa) {
                                P=new Pa();
                                addOperation.AddCardtoDeck(P, MyDeck);
                        }
                        if(e.getSource()==Chi) {
                                C=new Chi();
                                addOperation.AddCardtoDeck(C, MyDeck);
                        }
                        if(e.getSource()==G_C) {
                                GC=new G_C();
                                addOperation.AddCardtoDeck(GC, MyDeck);
                        }
                        if(e.getSource()==C_P) {
                                CP=new C_P();
                                addOperation.AddCardtoDeck(CP, MyDeck);
                        }
                        if(e.getSource()==P_G) {
                                PG=new P_G();
                                addOperation.AddCardtoDeck(PG, MyDeck);
                        }
                        if(e.getSource()==ALL) {
                                all=new ALL();
                                addOperation.AddCardtoDeck(all, MyDeck);
                        }
                }
                showCardList.setDeckSize(MyDeck.CheckDeck().size());
        }
}

class DeleteOperation extends Observable {

        public void DeleteCardforDeck(int number, DeckEditorModel MyDeck){

                MyDeck.deleteCard(number);
                setChanged();
                notifyObservers();

        }
}

class DeleteCardInDeck extends JPanel implements ActionListener {//削除操作を行うパネルのMVC
        int number;
        JButton delete;
        DeckEditorModel MyDeck;
        static JTextField delNumber;//リスト整理用に外部アクセスを可能としてある。
        DeleteOperation deleteOperation;
        ShowCardList showCardList;

        public DeleteCardInDeck(DeckEditorModel MyDeck, DeleteOperation dO, ShowCardList sc){
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
                number=MyDeck.CheckDeck().size();
                if(showCardList.getDeckSize()>0) {
                        deleteOperation.DeleteCardforDeck(j-1, MyDeck);
                        showCardList.setDeckSize(MyDeck.CheckDeck().size());
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
                        if(i==20) Showcardlist.List[20].setText("これ以上カードを追加できません");
                }
                if(MyDeck.CheckDeck().size()==0) Showcardlist.List[0].setText("カードが存在しません");
        }
}

class ListUpdate_Load implements Observer {
        ShowCardList showcardlist;
        LoadOperation loadOperation;
        String loadList;
        DeckEditorModel MyDeck;

        public ListUpdate_Load(ShowCardList sl, LoadOperation lo, DeckEditorModel Deck){
                showcardlist=sl;
                loadOperation=lo;
                loadList=" ";
                MyDeck=Deck;
                loadOperation.addObserver(this);
        }

        public void update(Observable O, Object arg){
                for(int i=0; i<=20; i++) {
                        showcardlist.List[i].setText(" ");
                }
                for(int i=0; i<MyDeck.CheckDeck().size(); i++) {
                        if(MyDeck.CheckDeck().size()==0) {
                                showcardlist.List[0].setText("カードが存在しません");
                                break;
                        }
                        loadList=Integer.toString(i+1);
                        loadList=loadList+". "+MyDeck.getCard(i).getCardName();
                        showcardlist.List[i].setText(loadList);
                }
        }
}

class ShowCardList extends JPanel {        //デッキ内部のカードリストの表記を行うパネル
        static JLabel[] List;        //デッキ内部表示用のJLabel配列
        JLabel NoneDeck;        //デッキが存在しない時の表示用ラベル
        String list;        //リスト文字列格納用変数
        DeckEditorModel MyDeck;
        int DeckSize;

        public ShowCardList(DeckEditorModel Deck){
                List=new JLabel[21];        //20番目はメッセージ用
                for(int j=0; j<21; j++) {
                        List[j]=new JLabel();
                }
                list=new String();
                DeckSize=0;
                this.setLayout(new GridLayout(21,1));
                MyDeck=Deck;
                for(int j=0; j<21; j++) {
                        this.add(List[j]);
                }
                if(MyDeck.CheckDeck()!=null) {        //初期化時にデッキが存在する場合。
                        for(int j=0; j<MyDeck.CheckDeck().size(); j++) {
                                list=Integer.toString(j+1);
                                list=list+". "+MyDeck.getCard(j).getCardName();
                                List[j].setText(list);
                                if(j==19) {
                                        List[20].setText("カードをこれ以上追加できません.");        //最大に達した場合のメッセージ
                                }
                        }
                }else if(MyDeck.CheckDeck()==null) {
                        List[0].setText("カードが存在しません.");
                        //デッキが存在しない場合メッセージを表示
                }
        }

        public void setDeckSize(int size){
                DeckSize=size;
                for(CardBase_E Card:MyDeck.CheckDeck()) {
                        System.out.println(Card.getCardName());
                }
        }

        public int getDeckSize(){
                return DeckSize;
        }


}

class SaveOperation {

        public void SaveDeck(int[] toSave, DeckEditorModel Deck, RW_csv save, int size){
                int i=0;
                for(CardBase_E SaveCard: Deck.CheckDeck()) {
                        toSave[i]=SaveCard.getID();
                        i++;
                }
                save.WriteCSV(toSave);
        }
}

class LoadOperation extends Observable {

        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;

        public void LoadDeck(DeckEditorModel Deck, RW_csv Load, int size){
                int[] fromLoad=Load.ReadCSV();
                if(Deck.CheckDeck()!=null) {
                        Deck.CheckDeck().clear();
                }
                for(int i=0; i<fromLoad.length; i++) {
                        switch(fromLoad[i]) {

                        case 1:
                                G=new Gu();
                                Deck.setCardToDeck(G);
                                break;
                        case 2:
                                P=new Pa();
                                Deck.setCardToDeck(P);
                                break;
                        case 3:
                                C=new Chi();
                                Deck.setCardToDeck(C);
                                break;
                        case 4:
                                GC=new G_C();
                                Deck.setCardToDeck(GC);
                                break;
                        case 5:
                                CP=new C_P();
                                Deck.setCardToDeck(CP);
                                break;
                        case 6:
                                PG=new P_G();
                                Deck.setCardToDeck(PG);
                                break;
                        case 7:
                                all=new ALL();
                                Deck.setCardToDeck(all);
                                break;
                        }
                }
                setChanged();
                notifyObservers();
        }
}

class SaveAndLoadPanel extends JPanel implements ActionListener {
        /*csvファイルからデータを読み込み,デッキを生成、もしくはデッキデータをセーブするパネル部分のview&controller*/
        int[] DeckData;
        JButton Save, Load;
        RW_csv DeckManager;
        DeckEditorModel MyDeck;
        SaveOperation saveOperation;
        LoadOperation loadOperation;
        ShowCardList showCardList;

        public SaveAndLoadPanel(DeckEditorModel Deck, ShowCardList sc, SaveOperation so, LoadOperation lo){
                DeckData=new int[20];         //最大カード格納数を配列のサイズとする.
                MyDeck=Deck;
                saveOperation=so;
                loadOperation=lo;
                showCardList=sc;
                for(int i=0; i<20; i++) {
                        DeckData[i]=0;        //初期化時にデータを受け取る配列内部の値をカードIDとして設定していない"0(ダミー)"にする.
                }
                DeckManager =new RW_csv(new File("assets/csv/main_deck.csv"));
                this.setLayout(new GridLayout(2,1));
                Save=new JButton("デッキをセーブ"); this.add(Save);
                Load=new JButton("デッキをロード"); this.add(Load);
                Save.addActionListener(this); Load.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e){
                /*セーブ,ロード,各操作をObserverパターンのために分離*/
                if(e.getSource()==Save && showCardList.getDeckSize()>0)
                        saveOperation.SaveDeck(DeckData, MyDeck, DeckManager, showCardList.getDeckSize());
                if(e.getSource()==Load) {
                        loadOperation.LoadDeck(MyDeck, DeckManager, showCardList.getDeckSize());
                        showCardList.setDeckSize(MyDeck.CheckDeck().size());
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
        ListUpdate_Load Listupdate_l;
        AddToDeck addtodeck;
        DeleteCardInDeck deletecardindeck;
        ShowCardList showcardlist;
        SaveOperation saveOperation;
        LoadOperation loadOperation;
        SaveAndLoadPanel saveAndloadPanel;

/* デッキ判定に関する変数の宣言*/
        RW_csv DeckManege;
        int[] Inisialize;
        Boolean DeckExists;
        ArrayList<CardBase_E> ExistsDeck;
        Gu G; Pa P; Chi C; G_C GC; C_P CP; P_G PG; ALL all;


        public DeckEditPanel(FrameController frameCont) {         // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;

                DeckManege=new RW_csv(new File("assets/csv/main_deck.csv"));
                Inisialize=DeckManege.ReadCSV();
                DeckExists=true;
                if(Inisialize==null) {
                        EditDeck=new DeckEditorModel();
                        DeckExists=false;
                }else{
                        for(int j=0; j<Inisialize.length && Inisialize[j]==0; j++) {
                                if(j==Inisialize.length-1) {
                                        DeckExists=false;
                                        EditDeck=new DeckEditorModel();
                                }
                        }
                        if(DeckExists==true) {
                                ExistsDeck=new ArrayList<CardBase_E>();
                                DeckReCreate(ExistsDeck, Inisialize);
                                EditDeck=new DeckEditorModel(ExistsDeck);
                        }
                }
                Deleteoperation=new DeleteOperation();
                Addoperation=new AddOperation();
                saveOperation=new SaveOperation();
                loadOperation=new LoadOperation();
                showcardlist=new ShowCardList(EditDeck);
                addtodeck=new AddToDeck(EditDeck, Addoperation, showcardlist);
                deletecardindeck=new DeleteCardInDeck(EditDeck, Deleteoperation, showcardlist);
                saveAndloadPanel=new SaveAndLoadPanel(EditDeck, showcardlist, saveOperation, loadOperation);
                Listupdate_l=new ListUpdate_Load(showcardlist, loadOperation, EditDeck);
                Listupdate_a=new ListUpdate_Add(showcardlist, Addoperation, EditDeck);
                Listupdate_d=new ListUpdate_Del(showcardlist, Deleteoperation, EditDeck);
                end = new JButton("タイトルへ進む");
                this.setLayout(new BorderLayout());        //BorderLayoutに設定しなおした。(沢畑)
                this.add(end, BorderLayout.NORTH);
                this.add(addtodeck, BorderLayout.WEST);
                this.add(deletecardindeck, BorderLayout.SOUTH);
                this.add(showcardlist, BorderLayout.CENTER);
                this.add(saveAndloadPanel, BorderLayout.EAST);

                end.addActionListener(this);


        }

        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == end) {
                        // タイトル画面への切り替え処理、大元のFrameControllerの中のメソッドを使う。
                        // 現在表示しているJPanelを破棄するため自分自身のインスタンス(this)を渡す。
                        frameCont.showTitle(this);
                }
        }

        public void DeckReCreate(ArrayList<CardBase_E> Deck, int[] List){
                for(int i=0; i<List.length; i++) {
                        switch(List[i]) {

                        case 1:
                                G=new Gu();
                                Deck.add(G);
                                break;
                        case 2:
                                P=new Pa();
                                Deck.add(P);
                                break;
                        case 3:
                                C=new Chi();
                                Deck.add(C);
                                break;
                        case 4:
                                GC=new G_C();
                                Deck.add(GC);
                                break;
                        case 5:
                                CP=new C_P();
                                Deck.add(CP);
                                break;
                        case 6:
                                PG=new P_G();
                                Deck.add(PG);
                                break;
                        case 7:
                                all=new ALL();
                                Deck.add(all);
                                break;
                        }
                }
        }
}
