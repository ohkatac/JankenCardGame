package com.deck_edit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.FrameController;
import com.deck_edit.edit_card_model.*;

class AddToDeck_MVC extends Observable implements ActionListener{//デッキ編集:追加操作のviewとcontroller
  private JButton Gu, Chi, Pa, G_C, C_P, P_G, ALL;//デッキ追加操作ボタン
  private JLabel ex_Gu, ex_Chi, ex_Pa, ex_G_C, ex_C_P, ex_P_G, ex_ALL; //カード説明;
  private JPanel AddCard;
/*追加操作と削除操作はデッキ内容表示のクラスからObservableとする。*/

  public AddtoDeck_VC(){//追加操作パネルのMVCコンストラクタ
    Gu=new JButton("グー"); Gu.addActionListener(this);//各種ボタンの設定とActionListenerへの追加
    Pa=new JButton("パー"); Pa.addActionListener(this);
    Chi=new JButton("チー"); Chi.addActionListener(this);
    G_C=new JButton("グーチー"); G_C.addActionListener(this);
	  C_P=new JButton("チーパー"); C_P.addActionListener(this);
	  P_G=new JButton("パーグー"); P_G.addActionListener(this);
	  ALL=new JButton("グーチーパー"); ALL.addActionListener(this);

    Add= new JPanel();

	/*ここからはカードの説明の記述　出来れば各カードクラスからデータを返してもらえるようにする
	 例えば,Gu.setDiscribe()として記述を返し、setTextで表示させる.*/
	  ex_Gu=new JLabel(); ex_Chi=new JLabel(); ex_Pa=new JLabel();
    ex_G_C=new JLabel(); ex_C_P=new JLabel(); ex_P_G=new JLabel();
    ex_ALL=new JLabel();
	/*ここにsetTextを用いた説明の記述を表示する.もしくは上記の初期化宣言時に引数として文字を渡すようにする */

    AddCard.setLayout(new GirdLayout(7,2));//レイアウト設定　今回はGridLayoutを用いる
    AddCard.add(Gu); AddCard.add(ex_Gu);
    AddCard.add(Chi); AddCard.add(ex_Chi);
    AddCard.add(Pa); AddCard.add(ex_Pa);
    AddCard.add(G_C); AddCard.add(ex_G_C);
    AddCard.add(C_P); AddCard.add(ex_C_P);
    AddCard.add(P_G); AddCard.add(ex_P_G);
    AddCard.add(ALL); AddCard.add(ex_ALL);
    }

  public void ActionPerformed(ActionEvent e){

    if(DeckEditPanel.EditDeck.MyDeck.size()<=20){
	   switch(e){/*押されたボタンごとにデッキに追加するカードを判別
     上限を設定する場合はその条件文を書く。*/
     case Gu:
     DeckEditPanel.EditDeck.setCardToDeck(new Gu());
     break;

     case Pa:
     DeckEditPanel.EditDeck.setCardToDeck(new Pa());
     break;

     case Chi:
     DeckEditPanel.EditDeck.setCardToDeck(new Chi());
     break;

     case G_C:
     DeckEditPanel.EditDeck.setCardToDeck(new G_C());
     break;

     case C_P:
     DeckEditPanel.EditDeck.setCardToDeck(new C_P());
     break;

     case P_G:
     DeckEditPanel.EditDeck.setCardToDeck(new P_G());
     break;

     case ALL:
     DeckEditPanel.EditDeck.setCardToDeck(new ALL());
     break;
     }
     setChanged();
     notifyObserevers();
   }else{//デッキ上限を超えた場合は追加をせずメッセージを表示する。
     ShowCardList.List[20].setText("追加はできません");
   }
  }
}

class DeleteCardInDeck extends Observable implements ActionListener{//削除操作を行うパネルのMVC
  private int number;
  private JButton delete;
  private JTextFiled delNumber;
  private JPanel DeleteCard;

  public DeleteCardInDeck(){
    delte=new JButton("番目を削除");
    delNumber=new JTextFiled(2);
    DeleteCard=new JPanel;
    DeleteCard.setLayout(new GirdLayout(1,2));
    DeleteCard.add(delete);
    DeleteCard.add(delNumber);
    delete.addActionListener(this);
  }

  public ActionPerformed(ActionEvent e){
    DeckEditPanel.EditDeck.deleteCard(Integer.parseInt(delNumber.getValue()-1));
    setChanged();
    notifyObserevers();
  }
}

class ShowCardList extends Jpanel implements Observer{
  private DeleteCardInDeck DCD;
  private AddToDeck_MVC ATD;
  private JLabel[] List;//デッキ内部表示用のJLabel配列
  private JLabel NoneDeck;//デッキが存在しない時の表示用ラベル
  private String list;

  public ShowCardList(DeleteCardInDeck dcd, AddToDeck_MVC atd){
    List=new JLabel[21];//20番目はメッセージ用
    for(int j=0; j<21; j++){
      List[j]=new JLabel();
    }
    list=new String();
    this.setLayout(new GirdLayout(21,1));
    DCD=dcd; ATD=atd;
    DCD.addObserever(this); ATD.addObserever(this);//それぞれの操作時にupdateを実行する。
    for(int j=0; i<20; i++){
      this.add(List[j]);
    }
    if(DeckEditPanel.EditDeck.CheckDeck()!=null){//初期化時にデッキが存在する場合さらに追加のコンストラクタを用いる。
      ShowCardList(DeckEditPanel.EditDeck.CheckDeck(),DCD,ATD);
    }else if(DeckEditPanel.EditDeck.CheckDeck().size()==null){
      //デッキが存在しない場合メッセージを表示
      List[0].setText("カードが存在しません");
    }
  }
  public ShowCardList(EditDeck currentDeck, DeleteCardInDeck deletecardindeck, AddToDeck_MVC addtodeck){
    for(int j=0; j<currentDeck.CheckDeck().size(); j++){
      list=Integer.toString(j+1);
      list=list+". "+currentDeck.getCard(j).getName();
      List[j].setText(list);
      if(j==19){
        List[20].setText("追加できません")
      }
    }
  }

  public update(Observable o, Object arg){
    int i;

    if(o.getclass()==AddToDeck_MVC){//updateメソッドの引数として元のクラスを渡しているのか不明なので後で変更の可能性大
      i=DeckEditPanel.EditDeck.MyDeck.size();
      list=Integer.toString(i);
      list=list+". "+DeckEditPanel.EditDeck.getCard(i-1).getName();
      List[i-1].setText(list);
    }else if(o.getclass()==DeleteCardInDeck){
      List[20].setText("");//メッセージの表記を消す。
      i=DeckEditPanel.EditDeck.MyDeck.size();
      if(i==0){//0枚の時はないことを知らせる。
        List[i].setText("カードが存在しません");
      }
      for(int n=Integer.parseInt(delNumber.getValue())-1; n<i; n++){//削除項目以降の再表記
        list=Integr.toString(n);
        list=n+". "+DeckEditPanel.EditDeck.getCard(n).getName();
        List[n].setText(list);
      }
    }
  }
}

// DeckEdit's Model & View & Controller
final public class DeckEditPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton end;
  DeckEditorModel EditDeck;
  AddToDeck_MVC addtodeck;
  DeleteCardInDeck deletecardindeck;
  ShowCardList showcardlist;

  public DeckEditPanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
    this.frameCont = frameCont;
    if(EditDeck.CheckDeck()==null)
      EditDeck=new DeckEditorModel();
    else if(EditDeck.CheckDeck()!=null)
      EditDeck=new DeckEditorModel(EditDeck.CheckDeck());
    addtodeck=new AddToDeck_MVC();
    deletecardindeck=new DeleteCardInDeck();
    showcardlist=new ShowCardList(addtodeck, deletecardindeck);
    end = new JButton("タイトルへ進む");
    this.setLayout(new BorderLayout());//BorderLayoutに設定しなおした。(沢畑)
    this.add(end, BorderLayout.NORTH);
    this.add(addtodeck.AddCard, BorderLayout.WEST);
    this.add(deletecardindeck.DeleteCard, BorderLayout.SOUTH);
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
