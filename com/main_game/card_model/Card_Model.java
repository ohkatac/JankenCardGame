package com.main_game.card_model;
/* カードそのもののModel デッキ編集用とは別のモデルとする*/
import java.util.*;

class CardBase {//全カードにおける継承元のクラス
  private String name;//カードの名前
  private int ID;//カードの識別用
  private Boolean flag;//カードを使用済みかどうかの確認

/*ここから先はゲームをするうえで必要となりそうな値を取得するためのMethod*/
  public String getName(){
    return name;
  }
  public int getID(){
    return ID;
  }
  public Boolean getFlag(){
    return flag;
  }
}
/*ここからは各種カードの設定、IDはグーを1,パーを2,チーを3,グーチーを4,チーパーを5,
パーグーを6,グーチーパーを7とする。変更するかも*/
class Gu extends CardBase{

  public Gu(){
    name=new String("グー");
    ID=1;
    flag=new Boolean(true);//デッキ追加時にはすべてのカードが"未使用(=true)"となるようにする。
  }
}

class Pa extends CardBase{

  public Pa(){
    name=new String("パー");
    ID=2;
    flag=new Boolean(true);
  }
}

class Chi extends CardBase{

  public Chi(){
    name=new String("チー");
    ID=3;
    flag=new Boolean(true);
  }
}

class G_C extends CardBase{

  public G_C(){
    name=new String("グーチー");
    ID=4;
    flag=new Boolean(true);
  }
}

class C_P extends CardBase{

  public C_P(){
    name=new String("チーパー");
    ID=5;
    flag=new Boolean(true);
  }
}

class P_G extends CardBase{

  public P_G(){
    name=new String("パーグー");
      ID=6;
      flag=new Boolean(true);
  }
}

class ALL extends CardBase{

  public ALL(){
    name=new String("グーチーパー");
    ID=7;
    flag=new Boolean(true);
  }
}
