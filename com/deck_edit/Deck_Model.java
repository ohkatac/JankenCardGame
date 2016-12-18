package com.deck_edit;

import java.util.*;
import com.deck_edit.edit_card_model.*;

class DeckEditorModel{
  private ArrayList<CardBase_E> MyDeck;

  public DeckEditorModel(){//デッキが存在しない場合や、デッキの内部が空の場合のコンストラクタ
    MyDeck=new ArrayList<CardBase_E>();
  }

  public DeckEditorModel(ArrayList<CardBase_E> currentDeck){//デッキが存在する場合のコンストラクタ
    MyDeck=currentDeck;
  }

  public void setCardToDeck(CardBase_E Card){//デッキ追加用のメソッド
    MyDeck.add(Card);
  }
  public CardBase_E getCard(int number){//デッキからカードの情報を取得するためのメソッド
    MyDeck.get(number);
  }
  public void deleteCard(int number){//デッキ内部のカードの削除用のメソッド
    MyDeck.remove(number);
  }

  public ArrayList<CardBase_E> CheckDeck(){//デッキが存在するかどうかの確認
    if(MyDeck.size()==0;)
      return null;
    else(MyDeck.size()!=0)
      return MyDeck;
  }
}
