public class myPlayer extends BasePlayer{

public myPlayer(){
  Life=10;//適当に値を設定、確定したら変更します。
  currentLife=Life;
  GameDeck = new CardBase_G[20];//デッキ配列の宣言　とりあえず、枚数は20枚。
  // ここにEdit側のデッキリスト変数の宣言と、デッキリストの内容の取得を行う。
  for (int i=0; i<20; i++) {//デッキの再編
    GameDeck[i] = rDC.reConstDeck(i);
    //再編用の関数を用いる。これ自体はDeck_Constractor側で行う。
  }
}