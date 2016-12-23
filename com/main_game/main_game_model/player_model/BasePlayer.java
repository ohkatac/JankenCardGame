package com.main_game.player_model;

import java.util.*;
import com.main_game.card_model.*;

public class BasePlayer{
  private CardBase_G GameDeck[];//Edit側のデッキリストをベースにMainGame用に再編しなおしたものを代入する。
  private final int Life = 50;//元々のライフ値を設定する
  private int currentLife;//現在のライフ値。初期値はLife。計算用に用いる。

  public int getLife(){//現在のライフを返す
    return currentLife;
  }

  public void MinusLife(int damage) {
    currentLife -= damage;
  }
  /*さらにここにデッキの残数を返すMethodを追加する。*/
}
