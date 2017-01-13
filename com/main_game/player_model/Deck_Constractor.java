package com.main_game.player_model;
/* デッキをMainGame用に再構成を行うパッケージ*/
import java.util.*;
import com.main_game.card_model.*;

class ReDeckConst{

  public CardBase_G reConstDeck(int i){
    //ここに内容を記述
  }

  public void makeComDeck(CardBase_G ComGamedeck[]){//Com用のデッキリストを作成する
    ComGamedeck[0]=new Gu();
    ComGamedeck[1]=new Gu();
    ComGamedeck[2]=new Chi();
    ComGamedeck[3]=new Chi();
    ComGamedeck[4]=new Pa();
    ComGamedeck[5]=new Pa();
    ComGamedeck[6]=new G_C();
    ComGamedeck[7]=new G_C();
    ComGamedeck[8]=new C_P();
    ComGamedeck[9]=new C_P();
    ComGamedeck[10]=new P_G();
    ComGamedeck[11]=new P_G();
    ComGamedeck[12]=new ALL();
    ComGamedeck[13]=new ALL();
    ComGamedeck[14]=new Gu();
    ComGamedeck[15]=new Pa();
    ComGamedeck[16]=new G_C();
    ComGamedeck[17]=new C_P();
    ComGamedeck[18]=new P_G();
    ComGamedeck[19]=new Chi();
  }
}
/*Com用のデッキ内容は適当です。何かいい案あれば書き直します。*/
