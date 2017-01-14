package com.main_game;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import com.FrameController;
import com.main_game.main_game_model.MainGameModel;
import com.main_game.main_game_controller.MainGameController;
import com.main_game.main_game_model.card_model.*;
import com.main_game.main_game_model.player_model.*;
import com.asset_controller.ImageButton;

// MainGame's View
final public class MainGamePanel extends JPanel {
        FrameController frameCont;

        MainGameModel gameModel;
        MainGameController gameController;

// 手札置き場、デッキ置き場などのフィールドをJPanelとして定義、そのあとにBorderLayoutとして適用させていく。
        MyFieldPanel myField;
        MySidePanel mySide;
        BattleFieldPanel battleField;
        RivalFieldPanel rivalField;
        RivalSidePanel rivalSide;

        public MainGamePanel(FrameController frameCont) { // FrameControllerでPanelを管理するために引数にこれをとる
                this.frameCont = frameCont;

                gameModel = new MainGameModel();

                this.setLayout(new BorderLayout()); // それぞれのFieldを再現するためにBorderLayoutを使う

                myField = new MyFieldPanel(gameModel, this);
                mySide = new MySidePanel(gameModel);
                battleField = new BattleFieldPanel(gameModel);
                rivalField = new RivalFieldPanel(gameModel, this);
                rivalSide = new RivalSidePanel(gameModel);

                gameController = new MainGameController(gameModel, this);

                this.add(myField, BorderLayout.SOUTH);
                this.add(mySide, BorderLayout.EAST);
                this.add(battleField, BorderLayout.CENTER);
                this.add(rivalField, BorderLayout.NORTH);
                this.add(rivalSide, BorderLayout.WEST);

        }

        public MyFieldPanel getMyField() {
                return myField;
        }
        public MySidePanel getMySide() {
                return mySide;
        }
        public BattleFieldPanel getBattleField() {
                return battleField;
        }
        public RivalFieldPanel getRivalField() {
                return rivalField;
        }
        public RivalSidePanel getRivalSide() {
                return rivalSide;
        }

// JPanelをリザルト画面に切り替えるメソッド
        public void GotoResult(){
                frameCont.showResult(this, gameModel.getPlayer().getLife(), gameModel.getRival().getLife() );
        }
}
