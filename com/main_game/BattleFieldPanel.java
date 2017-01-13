package com.main_game;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class BattleFieldPanel extends JPanel {
  MainGameModel model;
  JPanel myBattleF;
  JPanel riBattleF;
  JLabel myCard;
  JLabel riCard;
  ImageIcon mycardIcon;
  ImageIcon ricardIcon;
  String myIconPath;
  String riIconPath;
  Boolean isMycard;
  Boolean isRicard;

  public BattleFieldPanel(MainGameModel model) {
    this.model = model;

    myBattleF = new JPanel();
    myBattleF.setLayout( new FlowLayout() ); // とりあえず一番簡単なFlowLayoutに設定
    riBattleF = new JPanel();
    riBattleF.setLayout( new FlowLayout() ); // とりあえず一番簡単なFlowLayoutに設定

    this.setLayout( new BorderLayout() );
    this.add(myBattleF, BorderLayout.EAST); // 自分のバトルフィールドを右側に配置
    this.add(riBattleF, BorderLayout.WEST); // 相手のバトルフィールドを左側に配置

    isMycard = false;
    isRicard = false;

    // 
    myCard = new JLabel(new ImageIcon("assets/img/card/origin/empty.png"));
    myBattleF.add(myCard);
    riCard = new JLabel(new ImageIcon("assets/img/card/origin/empty.png"));
    riBattleF.add(riCard);

  }

  public void setMyCard( CardModel card ){
    isMycard = true;
    myIconPath = card.getImgPath();
    mycardIcon = new ImageIcon(myIconPath);

    myCard.setVisible(false);
    myBattleF.removeAll();
    myCard = new JLabel( mycardIcon );
    myBattleF.add(myCard);
    myCard.setVisible(true);
  }

  public void setRivalCard( CardModel card ){
    isRicard = true;
    riIconPath = card.getImgPath();
    ricardIcon = new ImageIcon(riIconPath);

    riCard.setVisible(false);
    riCard = new JLabel( new ImageIcon("assets/img/card/origin/back.png") );
    riBattleF.add(riCard);
    riCard.setVisible(true);
  }

  public void openRivalCard() {
    riCard.setVisible(false);
    riCard = new JLabel( ricardIcon );
    riBattleF.add(riCard);
    riCard.setVisible(true);
  }

  public void RemoveCards() {
    isMycard = isRicard = false;
    mycardIcon = ricardIcon = null;
    
    // 場のカードを空カードに設定
    myCard.setVisible(false);
    myCard = new JLabel(new ImageIcon("assets/img/card/origin/empty.png"));
    myBattleF.add(myCard);
    myCard.setVisible(true);

    // 場のカードを空カードに設定
    riCard.setVisible(false);
    riCard = new JLabel(new ImageIcon("assets/img/card/origin/empty.png"));
    riBattleF.add(riCard);
    riCard.setVisible(true);
  }

  public void setIsMycard(Boolean b) { isMycard = b; }
  public void setIsRicard(Boolean b) { isRicard = b; }

  public Boolean isSetCards() {
    if(isMycard && isRicard) return true;
    else return false;
  }
}