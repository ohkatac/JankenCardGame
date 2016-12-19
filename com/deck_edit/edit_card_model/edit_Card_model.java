package com.deck_edit.edit_card_model;
//Edit用のカードモデル。MainGame側のカードモデルと互換性をもたせること。
import java.util.*;

class CardBase_E {
  private String name;//カードの名前
  private int ID;//カードの識別用,MainGame側と同様の値を振り分ける。
  /*必要があればカードごとにデッキに組み込める上限を設定するための値を設定*/

  public String getName(){
    return name;
  }
  public int getID(){
    return ID;
  }
/*  public int getCost(){
    return cost;
  }上限値のために必要な値を返すMethod*/
}

class Gu extends CardBase_E{

  public Gu(){
    name=new String("グー");
    ID=1;
  }
}

class Pa extends CardBase_E{

  public Pa(){
    name=new String("パー");
    ID=2;
  }
}

class Chi extends CardBase_E{

  public Chi(){
    name=new String("チー");
    ID=3;
  }
}

class G_C extends CardBase_E{

  public G_C(){
    name=new String("グーチー");
    ID=4;
  }
}

class C_P extends CardBase_E{

  public C_P(){
    name=new String("チーパー");
    ID=5;
  }
}

class P_G extends CardBase_E{

  public P_G(){
    name=new String("パーグー");
      ID=6;
  }
}

class ALL extends CardBase_E{

  public ALL(){
    name=new String("グーチーパー");
    ID=7;
  }
}
