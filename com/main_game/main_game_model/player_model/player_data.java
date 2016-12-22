package com.main_game.player_model;

import java.util.*;
import com.main_game.card_model.*;

class BasePlayer{
  private CardBase GameDeck[];//Edit側のデッキリストをベースにMainGame用に再編しなおしたものを代入する。
  private int Life;//元々のライフ値を設定する
  private int currentLife;//現在のライフ値。初期値はLife。計算用に用いる。
  private ReDeckConst rDC;//デッキの構成を行うMethodの集まり

  public int getLife(){//現在のライフを返す
    return currentLife;
  }

  public void setLife(int currentLife, int damage){//ダメージ計算後のライフを設定、返す。
    this.currentLife=currentLife-damage;
    getLife();
  }
  /*さらにここにデッキの残数を返すMethodを追加する。*/
}

class myPlayer extends BasePlayer{

  public myPlayer(){
    Life=10;//適当に値を設定、確定したら変更します。
    currentLife=Life;
    GameDeck = new CardBase[20];//デッキ配列の宣言　とりあえず、枚数は20枚。
    // ここにEdit側のデッキリスト変数の宣言と、デッキリストの内容の取得を行う。
    for (int i=0; i<20; i++) {//デッキの再編
      GameDeck[i] = rDC.reConstDeck(i);
      //再編用の関数を用いる。これ自体はDeck_Constractor側で行う。
    }
  }
}

class comPlayer extends BasePlayer{

  public comPlayer(){
    Life=10;
    currentLife=Life;
    GameDeck=new CardBase[20];
    rDC.makeComDeck(ComGamedeck);//コンピューター用のデッキ編成Method。これもDeck_Constractor側で行う。
  }
}
