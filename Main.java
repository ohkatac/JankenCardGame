import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.deck_edit.*;
import com.main_game.*;
import com.result.*;
import com.title.*;


public final class Main {
    FrameController controller;
    public Main(){
      this.controller = new FrameController();
    }

    public static void main(String[] args) {
      new Main();
    }

}

// GameMode Controller : Title, DeckEdit, MainGame, Result
final class FrameController extends JFrame{

    private TitlePanel titlePanel;
    private DeckEditPanel editPanel;
    private MainGamePanel gamePanel;
    private ResultPanel resultPanel;

    public FrameController() {

        titlePanel = new TitlePanel(this);
        editPanel = new DeckEditPanel(this);
        gamePanel = new MainGamePanel(this);
        resultPanel = new ResultPanel(this);

        this.add(titlePanel);
        titlePanel.setVisible(true);

        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void showTitle(JPanel currentPanel) {
      currentPanel.setVisible(false);
      titlePanel = new TitlePanel(this);
      this.add(titlePanel);
      this.titlePanel.setVisible(true);
    }

    public void showDeckEdit(JPanel currentPanel) {
      currentPanel.setVisible(false);
      editPanel = new DeckEditPanel(this);
      this.add(editPanel);
      this.editPanel.setVisible(true);
    }

    public void showMainGame(JPanel currentPanel) {
      currentPanel.setVisible(false);
      gamePanel = new MainGamePanel(this);
      this.add(gamePanel);
      this.gamePanel.setVisible(true);
    }

    public void showResult(JPanel currentPanel) {
      currentPanel.setVisible(false);
      resultPanel = new ResultPanel(this);
      this.add(resultPanel);
      this.resultPanel.setVisible(true);
    }
}