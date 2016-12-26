package com.main_game.main_game_model.card_model;
/* カードそのもののModel デッキ編集用とは別のモデルとする*/
import java.util.*;

import com.asset_controller.ImageButton;

public class CardModel {//全カードにおける継承元のクラス
  private String name;//カードの名前
  private int ID;//カードの識別用
  private int cost;
  private Boolean flag;//カードを使用済みかどうかの確認
  private ImageButton myImage = null;

  public CardModel(int id) {
    this.ID = id;

    // 手札の種類をIDによって指定する。（文字列）
    switch(id) {
      case 1:
        name = "グー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/Gu.png",
          "assets/img/card/Gu.png",
          "assets/img/card/Gu.png",
          "assets/img/card/Gu.png"
        });
        cost = 1;
        break;
      case 2:
        name = "チョキ";
        myImage = new ImageButton(new String[] {
          "assets/img/card/Chi.png",
          "assets/img/card/Chi.png",
          "assets/img/card/Chi.png",
          "assets/img/card/Chi.png"
        });
        cost = 2;
        break;
      case 3:
        name = "パー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/Pa.png",
          "assets/img/card/Pa.png",
          "assets/img/card/Pa.png",
          "assets/img/card/Pa.png"
        });
        cost = 3;
        break;
      case 4:
        name = "グーチー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/GuChi.png",
          "assets/img/card/GuChi.png",
          "assets/img/card/GuChi.png",
          "assets/img/card/GuChi.png"
        });
        cost = 7;
        break;
      case 5:
        name = "チーパー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/ChiPa.png",
          "assets/img/card/ChiPa.png",
          "assets/img/card/ChiPa.png",
          "assets/img/card/ChiPa.png"
        });
        cost = 5;
        break;
      case 6:
        name = "パーグー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/PaGu.png",
          "assets/img/card/PaGu.png",
          "assets/img/card/PaGu.png",
          "assets/img/card/PaGu.png"
        });
        cost = 6;
        break;
      case 7:
        name = "グーチーパー";
        myImage = new ImageButton(new String[] {
          "assets/img/card/All.png",
          "assets/img/card/All.png",
          "assets/img/card/All.png",
          "assets/img/card/All.png"
        });
        cost = 9;
        break;
      default: 
        this.ID = 0;
    }
    /*ここからは各種カードの設定、IDはグーを1,パーを2,チーを3,グーチーを4,チーパーを5,
    パーグーを6,グーチーパーを7とする。*/
  }

/*ここから先はゲームをするうえで必要となりそうな値を取得するためのMethod*/
  public String getName(){ return name; }
  public int getID(){ return ID; }
  public Boolean getFlag(){ return flag; }
  public void ChangeFlag() { flag = !flag; }
  public void EnableButton() { this.myImage.Enabled(); }  
  public void DisableButton() { this.myImage.Disabled(); }
}