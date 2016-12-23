package com.main_game.card_model;
/* カードそのもののModel デッキ編集用とは別のモデルとする*/
import java.util.*;

public class CardModel {//全カードにおける継承元のクラス
  private String name;//カードの名前
  private int ID;//カードの識別用
  private Boolean flag;//カードを使用済みかどうかの確認

  public CardModel(int id) {
    this.ID = id;

    // 手札の種類をIDによって指定する。（文字列）
    switch(id) {
      case 1:
        name = "グー";
        break;
      case 2:
        name = "チョキ";
        break;
      case 3:
        name = "パー";
        break;
      case 4:
        name = "グーチー";
        break;
      case 5:
        name = "チーパー";
        break;
      case 6:
        name = "パーグー";
        break;
      case 7:
        name = "グーチーパー";
        break;
    }
    /*ここからは各種カードの設定、IDはグーを1,パーを2,チーを3,グーチーを4,チーパーを5,
    パーグーを6,グーチーパーを7とする。*/
  }

/*ここから先はゲームをするうえで必要となりそうな値を取得するためのMethod*/
  public String getName(){ return name; }
  public int getID(){ return ID; }
  public Boolean getFlag(){ return flag; }
  public void ChangeFlag() { flag = !flag; }
}