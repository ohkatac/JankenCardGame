package com.asset_controller;

import java.util.ArrayList;
// import for Read and Write File
import java.io.File;
// import for Reader
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
// import for Write
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

// CSVファイルの入出力をするためのクラスです。 このクラスでは.csvの中身を書くとき中を初期化して一から書く仕様に
// なっているので中のデータを保持したいときはいったんReadで別のint[]変数に避難させてからwrite系メソッドを使うようにしてください。

/* 使い方(パスの指定方法はMain.javaがあるディレクトリからの相対パスです。)
RW_csv mainDeck_csv = new RW_csv("ファイルのパスをここにいれる(文字列で入れる)" );
mainDeck_csv.WriteCSV(data); // dataはint[]一次元配列、これで指定ファイルにdataがcsv形式で代入される
mainDeck_csv.ReadCSV(); // これで指定されたcsvファイルの中身を取り出せる。 返り値はint[]
またReadCSV2(), WriteCSV2は拡張性を高めるために将来のことを考えて実装した。
いらなくなれば後で消します。

MainGameやDeckEditパネルで.csvファイルを取り出したいなぁと思ったら
RW_csv mainDeck = new RW_csv("assets/css/main_deck.csv"); 
// この時ファイルが存在していなかったら新しく空の.csvが生成されます。
int[] data = mainDeck.ReadCSV();
・・・・・・dataの中身をいろいろ変えたりする。
mainDeck.WriteCSV(data);

ReadCSV2(), WriteCSV2()についてですが複数のデッキを保存するときがいつか来るかもしれないかなと思い作りました。
例えばデッキのお気に入り機能とか実装するときに・・・
*/
public class RW_csv {
	String path;
  File file = null;

  public RW_csv(String path) {
		this.path = path;
		URL url =  this.getClass().getClassLoader().getResource(path);
		try {
			this.file = new File(url.toURI());
		} catch (URISyntaxException e) {
			System.out.println(e);
		}

    /* jarファイルに変換するとFileクラスが使えないためコメントアウトする
		if(!this.file.exists()) { // もし指定されたファイル名のファイルが存在しなかったら新しく生成する
      try {
        file.createNewFile();
      } catch(IOException e) {
        System.out.println(e);
      }
    } // 処理ここまで
    */
  }

  // 1次元配列を書き込むためのメソッド
  public void WriteCSV(int[] data) {
    try {
      // CSVに書き込みをするためのオブジェクト
      PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file), "UTF-8")));
      String writeData = ""; // 書き込むデータを入れる変数

      for (int i = 0; i < data.length; i++) {
        writeData += Integer.toString(data[i]); // 数値を文字に変換しつつ代入していく
        if (i < data.length - 1) writeData += ","; // CSVファイルなので間に','を入れる
      }

      pr.println(writeData); // ファイルに書き込み&改行

      pr.close(); // writerを閉じる。
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // １次元配列を読み込むためのメソッド
  public int[] ReadCSV() {
    int[] intData = null; // 返り値を代入する配列変数

    try {
      // csvファイルリーダーを宣言
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), "UTF-8"));

      String line = br.readLine(); // CSVファイルの先頭の一行を読み込む
      if (line == null) return null;
      String[] data = line.split(",", 0); // csvファイルの行を','で分割して代入する

      intData = new int[data.length]; // 入ったデータ分の個数int型配列を確保

      for(int i = 0; i < data.length; i++) {
        intData[i] = Integer.parseInt(data[i]); // 文字列を数値に直しつつint型配列に代入
      }

      br.close(); // Readerを閉じる
    } catch (IOException e) {
      System.out.println(e);
    }
    return intData;
  }

  public String readString() {
    String data;

    try {
      // csvファイルリーダーを宣言
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), "UTF-8"));

      data = br.readLine(); // CSVファイルの先頭の一行を読み込む
    } catch (IOException e) {
      data = null;
      System.out.println(e);
      System.exit(1);
    }

    return data;
  }

  // 2次元配列をCSVに書き込むためのメソッド(拡張性を持たせるためにあえて作りました。 最終的に使わないようでしたら削除します。)
  public void WriteCSV2(String[][] data) {
    try {
      if (checkBeforeWriteFile(this.file)) { // 書き込みがされていなかったらエラーメッセージを表示する
        // CSVに書き込みをするためのオブジェクト
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file), "UTF-8")));
        String[] writeData = new String[data.length]; // 書き込むデータを入れる変数

        for (int i = 0; i < data.length; i++) {
          writeData[i] = ""; // 文字列で初期化をしておく
          for (int j = 0; j < data[i].length; j++) {
            writeData[i] += data[i][j]; // 書き込み文字列にdataの内容を加えていく
            if (j < data[i].length - 1) writeData[i] += ","; // CSVファイルなので間に','を入れる
          }
        }

        for (int i = 0; i < writeData.length; i++) {
          pr.println(writeData[i]); // ファイルに書き込み&改行、これをデータ個数分繰り返す。
        }

        pr.close(); // writerを閉じる。
      } else {
        System.out.println("failed to write file!");
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  // 2次元配列をcsvファイルから読み込むためのメソッド(拡張性を持たせるためにあえて作りました。 最終的に使わないようでしたら削除します。)
  public String[][] ReadCSV2() {
    String[][] strData = null; // 返り値となる二次元配列の変数
    ArrayList<String> temp_list = new ArrayList<String>(); // csvファイルの中身をいったん格納するためのStringList

    try {
      // csvファイルリーダーを宣言
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.file), "UTF-8"));

      // 一行ずつCSVファイルを読み込む
      String line = br.readLine();
      while (line != null) {
        // 読み込んだ行をリストに代入していく
        temp_list.add(line);
        // readLineメソッドにより現在の読み込み位置を次の行にする。
        line = br.readLine();
      }

      strData = new String[temp_list.size()][]; // リスト構造のサイズ分新たに配列を確保

      for( int i = 0; i < temp_list.size(); i++ ) {
        strData[i] = temp_list.get(i).split(",", 0); // リスト構造に避難させていた文字列を','で区切りつつ代入していく
      }

      br.close(); // Readerを閉じる
    } catch (IOException e) {
      System.out.println(e);
    }
    return strData;
  }


  // ファイルに書き込みが反映されたかどうかを判定するためのメソッド。 この中のクラスでしか使わない。
  private static Boolean checkBeforeWriteFile(File file) {
    if (file.exists()) {
      if (file.isFile() && file.canWrite()) {
        return true;
      }
    }
    return false;
  }
}