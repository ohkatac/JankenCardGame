
class comPlayer extends BasePlayer{

  public comPlayer(){
    Life=10;
    currentLife=Life;
    GameDeck=new CardBase_G[20];
    rDC.makeComDeck(ComGamedeck);//コンピューター用のデッキ編成Method。これもDeck_Constractor側で行う。
  }
}
