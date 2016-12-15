// Java7以降対応

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class Main {
    FrameController controller;
    public Main(){
        this.controller = new FrameController();
        controller.start();
    }

    public static void main(String[] args) {
        new Main();
    }

}

final class FrameController {

    private JFrame mainFrame;

    private Frame1 frame1;
    private Frame2 frame2;
    private Frame3 frame3;

    public FrameController() {
        this.mainFrame = new MainFrame();

        this.frame1 = new Frame1(this);
        this.frame2 = new Frame2(this);
        this.frame3 = new Frame3(this);
    }

    public void start() {
        // FrameController controller = new FrameController();
        // controller.showFrame1();
        this.showFrame1();
    }

    public void showFrame(JFrame frame) {
        frame.setVisible(true);
        frame.requestFocus();
    }

    public void showFrame1() {
        mainFrame = frame1;
        showFrame(mainFrame);
    }

    public void showFrame2() {
        mainFrame = frame2;
    }

    public void showFrame3() {
        mainFrame = frame3;
    }

}

final class MainFrame extends JFrame {
    public MainFrame(){
        this.setTitle("J Game");
        this.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


// Model & View & Controller
final class Frame1 extends JFrame {

    public Frame1(final FrameController frameController) {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(new JButton(new AbstractAction("続きから") {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameController.showFrame2();
            }
        }));
        add(new JButton(new AbstractAction("始めから") {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameController.showFrame3();
            }
        }));
        pack();
    }

}

// Model & View & Controller
final class Frame2 extends JFrame {

    public Frame2(FrameController frameController) {
        setTitle("コンティニュー");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}

// Model & View & Controller
final class Frame3 extends JFrame {

    public Frame3(FrameController frameController) {
        setTitle("ニューゲーム");
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}