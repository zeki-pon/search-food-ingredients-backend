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
  https://scrapbox.io/taka521-tech-notes/%E3%80%90Kotlin%E3%80%91@JvmStatic%E3%81%A8%E3%81%AF
- ランダム値生成  
  https://www.baeldung.com/kotlin/random-alphanumeric-string  
  https://github.com/Baeldung/kotlin-tutorials/blob/master/core-kotlin-modules/core-kotlin-strings/src/test/kotlin/com/baeldung/randomString/RandomStringUnitTest.kt
- 関数型について
  https://kotlinlang.org/docs/lambdas.html#function-types
- 正規表現  
  https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html

## 課題
- DynamicPropertySourceが動いていない  
  - @JvmStaticを付けることで解決
- テストで使用するDBへの接続 でデフォルトスキーマを設定する方法はあるのか？
  - 一旦、SQLにスキーマ名を直書きすることで対応する
  - 余裕があれば対応すること
- Flywayのmigrate()が動いていない
  - 原因は、Flywayがtest/resources/db/migrationではなく、main/resources/db/migrationを参照していたことだった?  
    と思ったら、test/resources配下が参照されるようになった。原因不明...
- FoodRepositoryImplTestがUserRepositoryを意識している問題を解決したい
- SpringのUserと区別するため、domain.model.User.ktのクラス名を変更したい。

## その他メモ
Foodの更新時は、id, name, calories, protein, fat, carbohydrate, perGrams を必須にすべき