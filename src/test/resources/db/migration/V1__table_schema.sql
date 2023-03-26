CREATE SCHEMA food_ingredients_app;

-- change current schema
SET search_path = 'food_ingredients_app';

-- users
CREATE TABLE IF NOT EXISTS users (
    id character varying(26),
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(email)
);

COMMENT ON TABLE users IS 'ユーザテーブル';
COMMENT ON COLUMN users.id IS 'ユーザーID';
COMMENT ON COLUMN users.name IS 'ユーザー名';
COMMENT ON COLUMN users.email IS 'メールアドレス';
COMMENT ON COLUMN users.password IS 'パスワードのハッシュ値';
COMMENT ON COLUMN users.created_at IS '作成日時';
COMMENT ON COLUMN users.updated_at IS '更新日時';

-- foods
CREATE TABLE IF NOT EXISTS foods (
    id character varying(26),
    user_id character varying(26) NOT NULL,
    name character varying(255) NOT NULL,
    calories numeric(4,1) NOT NULL,
    protein numeric(4,1) NOT NULL,
    fat numeric(4,1) NOT NULL,
    carbohydrate numeric(4,1) NOT NULL,
    per_grams numeric(4,1) NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone NOT NULL,
    PRIMARY KEY(id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

COMMENT ON TABLE foods IS '食品テーブル';
COMMENT ON COLUMN foods.id IS '食品ID';
COMMENT ON COLUMN foods.user_id IS 'ユーザーID';
COMMENT ON COLUMN foods.name IS '食品名';
COMMENT ON COLUMN foods.calories IS '熱量(kcal)';
COMMENT ON COLUMN foods.protein IS 'タンパク質';
COMMENT ON COLUMN foods.fat IS '脂質';
COMMENT ON COLUMN foods.carbohydrate IS '炭水化物';
COMMENT ON COLUMN foods.per_grams IS 'グラム単位';
COMMENT ON COLUMN foods.created_at IS '作成日時';
COMMENT ON COLUMN foods.updated_at IS '更新日時';