/*
  カードのモデルクラス
  コンストラクタで自分自身の画像(myImage, btLabelPath)を取得する。
*/

package com.main_game.main_game_model.card_model;
/* カードそのもののModel デッキ編集用とは別のモデルとする*/
import java.util.*;

import com.asset_controller.ImageButton;

public class CardModel {//全カードにおける継承元のクラス
  public static final int GU = 1, CHI = 2, PA = 3, GUCHI = 4, CHIPA = 5, PAGU = 6, ALL = 7;

  private String name;//カードの名前
  private int id;//カードの識別用
  private int cost;
  private Boolean flag;//カードを使用済みかどうかの確認
  private ImageButton myImage = null;
  private String btLabelPath; // 元画像のPath, 場に出すカードに使う画像

  public static final String[][] imgPath = {
    {     
          "assets/img/card/btnimg/Gu.png",
          "assets/img/card/btnimg/Gu_pressed.png",
          "assets/img/card/btnimg/Gu_hover.png",
          "assets/img/card/btnimg/Gu_unable.png",
          "assets/img/card/origin/Gu.png"
    },
    {
          "assets/img/card/btnimg/Chi.png",
          "assets/img/card/btnimg/Chi_pressed.png",
          "assets/img/card/btnimg/Chi_hover.png",
          "assets/img/card/btnimg/Chi_unable.png",
          "assets/img/card/origin/Chi.png"
    },
    {
          "assets/img/card/btnimg/Pa.png",
          "assets/img/card/btnimg/Pa_pressed.png",
          "assets/img/card/btnimg/Pa_hover.png",
          "assets/img/card/btnimg/Pa_unable.png",
          "assets/img/card/origin/Pa.png"
    },
    {
          "assets/img/card/btnimg/GuChi.png",
          "assets/img/card/btnimg/GuChi_pressed.png",
          "assets/img/card/btnimg/GuChi_hover.png",
          "assets/img/card/btnimg/GuChi_unable.png",
          "assets/img/card/origin/GuChi.png"
    },
    {
          "assets/img/card/btnimg/ChiPa.png",
          "assets/img/card/btnimg/ChiPa_pressed.png",
          "assets/img/card/btnimg/ChiPa_hover.png",
          "assets/img/card/btnimg/ChiPa_unable.png",
          "assets/img/card/origin/ChiPa.png"
    },
    {
          "assets/img/card/btnimg/PaGu.png",
          "assets/img/card/btnimg/PaGu_pressed.png",
          "assets/img/card/btnimg/PaGu_hover.png",
          "assets/img/card/btnimg/PaGu_unable.png",
          "assets/img/card/origin/PaGu.png"
    },
    {
          "assets/img/card/btnimg/All.png",
          "assets/img/card/btnimg/All_pressed.png",
          "assets/img/card/btnimg/All_hover.png",
          "assets/img/card/btnimg/All_unable.png",
          "assets/img/card/origin/All.png"
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
      btLabelPath = imgPath[id-1][4];
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
  public String getImgPath() { return btLabelPath; }
  public void EnableButton() { this.myImage.Enabled(); }  
  public void DisableButton() { this.myImage.Disabled(); }
}