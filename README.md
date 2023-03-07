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

テスト用のDBに接続したいのだが、本番用のDBに接続されてしまう。
一旦、本番用のスキーマを削除して対応。

## note
- JvmStatic  
  https://scrapbox.io/taka521-tech-notes/%E3%80%90Kotlin%E3%80%91@JvmStatic%E3%81%A8%E3%81%AF

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
  　