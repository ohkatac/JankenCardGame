


public class FileImport {

  public static int[] import(String address) {
    int index[] = new int[];

    // ファイルの読み込み
    try {
      // ファイルのアドレスよりbrにファイルの情報を格納
      FileReader filereader = new FileReader(address);
      BufferedReader br = new BufferedReader(filereader);

      // 一行ずつ書き込む
      String str = br.readLine();
      int count = 0;

      while(str != null) {
        // strlist.add(str.split(",");
        // 一行の内容を','で分割してそれぞれを[count = ノード番号]の二時限目の配列の要素として格納
        index[count] = parseInt(str.split(","));
        // 次の行を読み込み
        str = br.readLine();
        count++;
      }

      br.close();
    } catch(FileNotFoundException e) {
      System.out.println(e);
    } catch(IOException e) 
      System.out.println(e);
    }

    return indesx;
  }

  public static int[] parseInts(String[] s) {
    // s[] = inに格納したいStringを収めた配列

    int[] x = new int[s.length];

    for( int i = 0; i < s.length; i++) {
      x[i] = Integer.parseInt(s[i]);
    }

    return x;
  }
}