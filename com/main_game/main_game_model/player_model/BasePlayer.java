package com.main_game.player_model;

import java.util.*;
import com.main_game.card_model.*;

public class BasePlayer{
  private CardBase_G GameDeck[];//Edit側のデッキリストをベースにMainGame用に再編しなおしたものを代入する。
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
