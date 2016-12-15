import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class Main {
    FrameController controller;
    public Main(){
      this.controller = new FrameController();
    }

    public static void main(String[] args) {
      new Main();
    }

}

final class FrameController extends JFrame{

    private JPanel mainPanel;

    private TitlePanel titlePanel;
    private DeckEditPanel editPanel;
    private MainGamePanel gamePanel;
    private ResultPanel resultPanel;

    private CardLayout layout;

    public FrameController() {

        this.titlePanel = new TitlePanel(this);
        this.editPanel = new DeckEditPanel(this);
        this.gamePanel = new MainGamePanel(this);
        this.resultPanel = new ResultPanel(this);

        layout = new CardLayout();

        this.setLayout(layout);

        this.add(titlePanel, "Title");
        this.add(editPanel, "Edit");
        this.add(gamePanel, "Game");
        this.add(resultPanel, "Result");

        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.showTitle();
    }

    public void showPanel(JPanel panel) {
      CardLayout cl = (CardLayout)layout;
      this.mainPanel = panel;
    }

    public void showTitle() {
        mainPanel = titlePanel;
        layout.first(this);
    }

    public void showDeckEdit() {
      mainPanel = editPanel;
      // layout.
    }

    public void showMainGame() {
        mainPanel = gamePanel;
    }

    public void ShowResult() {
        mainPanel = resultPanel;
    }

}


// Title's Model & View & Controller
final class TitlePanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton start;
  JButton deckEdit;

  public TitlePanel(FrameController frameCont) {
    this.frameCont = frameCont;
    setLayout(new FlowLayout());
    start = new JButton("ゲームスタート");
    deckEdit = new JButton("デッキ編集");

    start.addActionListener(this);
    deckEdit.addActionListener(this);

    this.add(start);
    this.add(deckEdit);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == start){
      frameCont.showMainGame();
    }
    else if (e.getSource() == deckEdit) {
      frameCont.showDeckEdit();
    }
  }
}


// DeckEdit's Model & View & Controller
final class DeckEditPanel extends JPanel implements ActionListener { 
  FrameController frameCont;
  JButton end;

  public DeckEditPanel(FrameController frameCont) {
    this.frameCont = frameCont;

    end = new JButton("タイトルへ進む");
    end.addActionListener(this);
    this.add(end);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == end) {
      frameCont.showTitle();
    }
  }
}

// MainGame's Model & View & Controller
final class MainGamePanel extends JPanel implements ActionListener { 
  FrameController frameCont;
  JButton result;

  public MainGamePanel(FrameController frameCont) {
    this.frameCont = frameCont;

    result = new JButton("Resultへ進む");
    result.addActionListener(this);
    this.add(result);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == result) {
      frameCont.showMainGame();
    }
  }

}

// Result's Model & View & Controller
final class ResultPanel extends JPanel implements ActionListener {
  FrameController frameCont;
  JButton title;

  public ResultPanel(FrameController frameCont) {
    this.frameCont = frameCont;

    title = new JButton("タイトルへ進む");
    title.addActionListener(this);

    this.add(title);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == title) {
      frameCont.showTitle();
    }
  }
}


