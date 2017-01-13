# README #

動いたと思っても油断は禁物です。 .classファイルが残っているとうまくいったような感じになる場合があります。
動いたと思っても.classをいったんすべて消してから
javac Main.java
java Main
で動作させる確認をしましょう。
カレントディレクトリ以下の.classファイルすべてを消去するコマンド  
$ find ./ -name '*.class' | xargs rm  

