package com.main_game.main_game_model.player_model;

import java.util.ArrayList;

import javax.swing.JLabel;

import com.main_game.main_game_model.card_model.CardModel;

public class BasePlayer{
  protected ArrayList<CardModel> deck; //Edit側のデッキリストをベースにMainGame用に再編しなおしたものを代入する
  protected ArrayList<CardModel> hands; // 手札を格納する変数
  protected int life;//元々のライフ値を設定する
  protected int currentLife;//現在のライフ値。初期値はLife。計算用に用いる。
  protected JLabel imgLabel; // プレイヤーの画像を表示するクラス
  protected Boolean alive = true;

  public BasePlayer(int life) {
    this.life = life;
    currentLife = life;
    deck = new ArrayList<CardModel>();
    hands = new ArrayList<CardModel>();
  }

  public JLabel getIconLabel(){ return imgLabel; } // IconのJLabelを返す
  public int getLife(){ return currentLife; } //現在のライフを返す 
  public void Damage(int damage) { currentLife -= damage; }
  // 現在のデッキのインスタンスを返すメソッド
  public CardModel[] getDeck() {
    CardModel[] data = new CardModel[deck.size()];
    for(int i = 0; i < deck.size(); i++) {
      data[i] = deck.get(i);
    }
    return data;
  }
  // 手札から一枚取り出す。 indexは0~4
  public CardModel PushCard(int index) {
    CardModel c = new CardModel(deck.get(index).getID());
    deck.remove(index);
    return c;
  }
  // デッキから5枚カードを引いて手札に加える
  public void Draw5Cards() {
    for(int i = 0; i < 5; i++) {
      hands.add(deck.get(0));
      deck.remove(0);
    }
  }
  // デッキからカードを一枚ドローして手札に加える
  public void Draw1Card() {
    if(deck.size() == 0){
      hands.add(deck.get(0));
      deck.remove(0);
    }
  }
  // プレイヤーが生きているかどうかを判定するメソッド
  public Boolean isAlive() {
    if(alive == false || deck.size() == 0) return false; 
    else return true;
  }
}
