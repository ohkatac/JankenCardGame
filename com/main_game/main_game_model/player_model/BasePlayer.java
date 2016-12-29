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
  public ArrayList<CardModel> getDeck() { return deck;}

  // 手札の配列を返すメソッド
  public ArrayList<CardModel> getHands() { return hands; }

  // 手札から一枚取り出す。 indexは0~4
  public CardModel PopCard(int index) {
    CardModel c = new CardModel(hands.get(index).getID());
    for(CardModel m : hands) {
      m.EnableButton();
    }
    hands.get(index).DisableButton();
    return c;
  }

  // 手札からカードを一枚捨てるメソッド
  public void RemoveHandsCard(int removeIndex) { hands.remove(removeIndex); }

  // デッキからカードを一枚ドローして手札に加える
  public CardModel DrawCard() {
    CardModel value;
    if(deck.size() > 0){
      value = new CardModel(deck.get(0).getID());
      hands.add(value);
      deck.remove(0);
      return value;
    }
    return null;
  }

  // プレイヤーが生きているかどうかを判定するメソッド
  public Boolean isAlive() {
    if(alive == false || deck.size() == 0) return false; 
    else return true;
  }
}
