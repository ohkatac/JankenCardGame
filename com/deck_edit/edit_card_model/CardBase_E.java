package com.deck_edit.edit_card_model;
//Edit用のカードモデル。MainGame側のカードモデルと互換性をもたせること。
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class CardBase_E {
	private String name;//カードの名前
	private int ID;//カードの識別用,MainGame側と同様の値を振り分ける。
	private BufferedImage[] iconImages; //各種追加ボタンやパネルのアイコン生成時用
	private String Describe;//カード間の相性を記述する 必要になれば一応使えるようにはした

	public CardBase_E(){
		iconImages = new BufferedImage[3]; //継承先カード各種の初期化時に必ず行われる
	}

	public String getCardName(){
		return name;
	}
	public void setCardName(String CardName){
		name=CardName;
	}
	public int getID(){
		return ID;
	}
	public void setID(int ID){
		this.ID=ID;
	}
	public void setDescribe(String content){
		Describe=content;
	}
	public String getDescribe(){
		return Describe;
	}
	public void setPath(String NormalPath, String PressPath, String HoverPath){
		try {
			iconImages[0] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(NormalPath)); //通常時
			iconImages[1] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(PressPath)); //押したとき
			iconImages[2] = ImageIO.read(getClass().getClassLoader().getResourceAsStream(HoverPath)); //マウスが重なったとき
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage[] getIconImages() {
		return iconImages;
	} // 画像の配列を返す

}
