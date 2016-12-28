package com.main_game.main_game_model.card_model;
/* カードそのもののModel デッキ編集用とは別のモデルとする*/
import java.util.*;

import com.asset_controller.ImageButton;

public class CardModel {//全カードにおける継承元のクラス
  private String name;//カードの名前
  private int id;//カードの識別用
  private int cost;
  private Boolean flag;//カードを使用済みかどうかの確認
  private ImageButton myImage = null;

  public static final String[][] imgPath = {
    {     
          "assets/img/card/Gu.png",
          "assets/img/card/Gu_unable.png",
          "assets/img/card/Gu_unable.png",
          "assets/img/card/Gu_unable.png"
    },
    {
          "assets/img/card/Chi.png",
          "assets/img/card/Chi_unable.png",
          "assets/img/card/Chi_unable.png",
          "assets/img/card/Chi_unable.png"
    },
    {
          "assets/img/card/Pa.png",
          "assets/img/card/Pa_unable.png",
          "assets/img/card/Pa_unable.png",
          "assets/img/card/Pa_unable.png"
    },
    {
          "assets/img/card/GuChi.png",
          "assets/img/card/GuChi_unable.png",
          "assets/img/card/GuChi_unable.png",
          "assets/img/card/GuChi_unable.png"
    },
    {
          "assets/img/card/ChiPa.png",
          "assets/img/card/ChiPa_unable.png",
          "assets/img/card/ChiPa_unable.png",
          "assets/img/card/ChiPa_unable.png"
    },
    {
          "assets/img/card/PaGu.png",
          "assets/img/card/PaGu_unable.png",
          "assets/img/card/PaGu_unable.png",
          "assets/img/card/PaGu_unable.png"
    },
    {
          "assets/img/card/All.png",
          "assets/img/card/All_unable.png",
          "assets/img/card/All_unable.png",
          "assets/img/card/All_unable.png"
    }
  };

  public CardModel(int id) {
    this.id = id;

    // 手札の種類をIDによって指定する。（文字列）
    switch(id) {
      case 1:
        name = "グー";
        cost = 2;
        break;
      case 2:
        name = "チョキ";
        cost = 2;
        break;
      case 3:
        name = "パー";
        cost = 2;
        break;
      case 4:
        name = "グーチー";
        cost = 5;
        break;
      case 5:
        name = "チーパー";
        cost = 5;
        break;
      case 6:
        name = "パーグー";
        cost = 5;
        break;
      case 7:
        name = "グーチーパー";
        cost = 7;
        break;
      default: 
        name = "不正データ";
    }
    // イメージボタンの設定
    if( 0 < id && id < 8 ) {
      myImage = new ImageButton(new String[] {
        imgPath[id-1][0],
        imgPath[id-1][1],
        imgPath[id-1][2],
        imgPath[id-1][3]
      });
    }
    /*ここからは各種カードの設定、idはグーを1,パーを2,チーを3,グーチーを4,チーパーを5,
    パーグーを6,グーチーパーを7とする。*/
  }

/*ここから先はゲームをするうえで必要となりそうな値を取得するためのMethod*/
  public String getName(){ return name; }
  public int getID(){ return id; }
  public int getCost() { return cost; }
  public Boolean getFlag(){ return flag; }
  public void ChangeFlag() { flag = !flag; }
  public ImageButton getImageBtn() { return this.myImage; }
  public String getImgPath() { return imgPath[id-1][0]; }
  public void EnableButton() { this.myImage.Enabled(); }  
  public void DisableButton() { this.myImage.Disabled(); }
}