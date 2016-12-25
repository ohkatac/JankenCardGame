import com.FrameController;

public final class Main {
    // FrameControllerはcom/ディレクトリ内のFrameController.javaを参照
    FrameController controller;
    public Main(){
      this.controller = new FrameController();
    }

    public static void main(String[] args) {
      new Main();
   }

}