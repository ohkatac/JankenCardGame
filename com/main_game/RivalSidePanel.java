package com.main_game;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_model.player_model.*;
import com.main_game.main_game_model.card_model.*;

public class RivalSidePanel extends JPanel {
  MainGameModel model;
  JButton someBtn;
  JLabel captionValue;

  public RivalSidePanel(MainGameModel model) {
    super();
    this.model = model;

    this.setPreferredSize(new Dimension(200, 0));

    this.setLayout( new FlowLayout() );
  }

  public void showCaption(BasePlayer player, BasePlayer rival, CardModel plCard, CardModel riCard, int judge) {
    String resultText;
    if(captionValue != null) captionValue.setVisible(false);
    switch(judge) {
      case 0:
        resultText = "引き分け";
        break;
      case 1:
        resultText = "相手に"+plCard.getCost()+"Pointのダメージを与えた";
        break;
      case -1:
        resultText = "あなたは"+riCard.getCost()+"Pointのダメージを受けた";
        break;
      default:
        resultText = "";
    }
    captionValue = new JLabel("<html>"+
      "相手のカードは"+riCard.getName()+"<br>"+
      "自分のカードは"+plCard.getName()+"<br>"+
      resultText+"<br>"+
      "相手のライフ: "+rival.getLife()+"<br>"+
      "自分のライフ: "+player.getLife()+"<br>"+
      "</html>"
    );
    this.add(captionValue);
    captionValue.setVisible(true);
  }

  public void deleteCaption() {
    captionValue.setVisible(false);
  }

}
