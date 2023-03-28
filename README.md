# search-food-ingredients-backend
食品成分検索アプリケーション バックエンド

## yml
参考：https://www.wakuwakubank.com/posts/488-it-yaml/

## postgresへの接続(jdbc)
参考：https://itsakura.com/sb-postgresql-jdbc

## Unit Test
- 下記のサイトを参考に実装する  
  - https://techblog.zozo.com/entry/it-improvement-with-testcontainers  
  - https://www.m3tech.blog/entry/2021/12/19/120000

## note
- JvmStaticについて
  - https://scrapbox.io/taka521-tech-notes/%E3%80%90Kotlin%E3%80%91@JvmStatic%E3%81%A8%E3%81%AF
- ランダム値生成  
  - https://www.baeldung.com/kotlin/random-alphanumeric-string  
  - https://github.com/Baeldung/kotlin-tutorials/blob/master/core-kotlin-modules/core-kotlin-strings/src/test/kotlin/com/baeldung/randomString/RandomStringUnitTest.kt
- 関数型について
  - https://kotlinlang.org/docs/lambdas.html#function-types
- 正規表現  
  - https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html
- Spring Security  
  - https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter  
  - https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
  - https://qiita.com/opengl-8080/items/c105152c9ca48509bd0c
  - https://qiita.com/suke_masa/items/908805dd45df08ba28d8
- SecurityContextHolder.getContext().authenticationのモック  
  - https://stackoverflow.com/questions/57570950/how-to-mock-securitycontextholder-getcontext-getauthentication-getcredential
- ハッシュ化したパスワードの生成方法
  - https://github.com/n-takehata/kotlin-server-side-programming-practice/tree/main/part2/book-manager
- Postmanが起動しない(ローカルデータを削除することで解決)
  - https://support.postman.com/hc/en-us/articles/4413408378519-Can-I-run-the-Postman-Desktop-app-on-my-Apple-M1-chip-
  - https://support.postman.com/hc/en-us/articles/360025359014
  - https://support.postman.com/hc/en-us/articles/4410785125271#h_01GGSSFFHJ46Y21XHA604GXK81
- DynamicPropertySourceが動いていない
  - @JvmStaticを付けることで解決

## 課題
- テストで使用するDBへの接続 でデフォルトスキーマを設定する方法はあるのか？
  - 一旦、SQLにスキーマ名を直書きすることで対応する
  - 余裕があれば対応すること
- Flywayのmigrate()が動いていない
  - 原因は、Flywayがtest/rsources/db/migrationではなく、main/resources/db/migrationを参照していたことだった?  
    と思ったら、test/resources配下が参照されるようになった。原因不明...
- FoodRepositoryImplTestがUserRepositoryを意識している問題を解決したい
- SpringのUserと区別するため、domain.model.User.ktのクラス名を変更したい。
- Spring Bootのバージョンが古い(書籍で学習したバージョン)のため、バージョンアップすること
- Redisの導入
- postmanを使用した自動テスト
- 異常系のレスポンス(現状ハンドリングしていないため)

## その他メモ
Foodの更新時は、id, name, calories, protein, fat, carbohydrate, perGrams を必須にすべき

## 残作業
- bootRun -> ターミナルから動作確認
- コミット