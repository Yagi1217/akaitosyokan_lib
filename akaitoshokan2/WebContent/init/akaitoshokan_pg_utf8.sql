DROP DATABASE IF EXISTS sample;
DROP USER IF EXISTS student;
CREATE USER student WITH PASSWORD 'himitu';
CREATE DATABASE sample OWNER student ENCODING 'UTF8';
\c sample

DROP TABLE IF EXISTS classification;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS item_master;
DROP TABLE IF EXISTS item_list;
DROP TABLE IF EXISTS rental_list;

CREATE TABLE classification (
    code INTEGER PRIMARY KEY,
    name  VARCHAR(100)
);

  CREATE TABLE members (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    name_kana VARCHAR(256) NOT NULL,
    address VARCHAR(100) NOT NULL,
    tel VARCHAR(13) NOT NULL,
    email VARCHAR(256) NOT NULL,
    birthday DATE,
    regist_date DATE,
    release_date DATE
);

CREATE TABLE item_master (
    isbn VARCHAR(15) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    class_code INTEGER NOT NULL,
    author VARCHAR(256),
    publisher VARCHAR(256)
);

CREATE TABLE item_list (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(15) NOT NULL,
    name VARCHAR(100) NOT NULL,
    rental_date DATE,
    arrival_date DATE,
    discard_date DATE,
    remarks VARCHAR(256)
);

CREATE TABLE rental_list (
    id SERIAL PRIMARY KEY,
    member_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE,
    return_scheduled DATE NOT NULL,
    remarks VARCHAR(256)
);

ALTER TABLE classification OWNER TO student;
ALTER TABLE members OWNER TO student;
ALTER TABLE item_master OWNER TO student;
ALTER TABLE item_list OWNER TO student;
ALTER TABLE rental_list OWNER TO student;

INSERT INTO classification VALUES(0,'総記');
INSERT INTO classification VALUES(1,'哲学');
INSERT INTO classification VALUES(2,'歴史');
INSERT INTO classification VALUES(3,'社会科学');
INSERT INTO classification VALUES(4,'自然科学');
INSERT INTO classification VALUES(5,'技術');
INSERT INTO classification VALUES(6,'産業');
INSERT INTO classification VALUES(7,'芸術');
INSERT INTO classification VALUES(8,'言語');
INSERT INTO classification VALUES(9,'文学');

INSERT INTO item_master VALUES('9784839954093','Webとプログラミングのきほんのきほん',5,'大澤 文孝','マイナビ出版');
INSERT INTO item_master VALUES('9784798130644','おうちで学べるプログラミングのきほん',5,'河村 進','翔泳社');
INSERT INTO item_master VALUES('9784774137100','これからはじめるプログラミングの基礎の基礎',5,'谷尻 かおり','技術評論社');
INSERT INTO item_master VALUES('9784798039251','プログラミングの仕組み プログラムはこうして作られる',5,'平山 尚(株式会社セガ)','秀和システム');
INSERT INTO item_master VALUES('9784480689382','世界が変わるプログラム入門',5,'山本貴光','筑摩書房');
INSERT INTO item_master VALUES('9784863541689','ウェブエンジニアの教科書',5,'佐々木達也','シーアンドアール研究所');
INSERT INTO item_master VALUES('9784797383102','プログラミング入門講座-基本と思考法と重要事項がきちんと学べる授業-',5,'米田 昌悟','SBクリエイティブ');
INSERT INTO item_master VALUES('9784479794653','アイデアを実現させる最高のツールプログラミングをはじめよう',5,'池澤あやか','大和書房');
INSERT INTO item_master VALUES('9784844336389','すっきりわかるJava入門',5,'中山 清喬','インプレス');
INSERT INTO item_master VALUES('9784797387605','新・明解Java入門',5,'柴田 望洋','SBクリエイティブ');
INSERT INTO item_master VALUES('9784774195025','3ステップでしっかり学ぶ Ruby入門',5,'WINGSプロジェクト 竹馬 力','技術評論社');
INSERT INTO item_master VALUES('9784774193977','プロを目指す人のためのRuby入門',5,'伊藤 淳一','技術評論社');
INSERT INTO item_master VALUES('9784897978857','いきなりはじめるPHP〜ワクワク・ドキドキの入門教室〜',5,'谷藤賢一','リックテレコム');
INSERT INTO item_master VALUES('9784798126319','10日でおぼえるPHP入門教室',5,'山田祥寛','翔泳社');
INSERT INTO item_master VALUES('9784822292270','独学プログラマ-Python言語の基本から仕事のやり方まで-',5,'コーリー・アルソフ','日経BP');
INSERT INTO item_master VALUES('9784844380153','基礎 Python',5,'大津 真','インプレス');
INSERT INTO item_master VALUES('9784797392586','やさしいC言語',5,'高橋 麻奈','SBクリエイティブ');
INSERT INTO item_master VALUES('9784774167084','はじめて学ぶC言語プログラミング入門講座',5,'西村 広光','技術評論社');
INSERT INTO item_master VALUES('9784295003854','スラスラ読める JavaScript ふりがなプログラミング(ふりがなプログラミングシリーズ)',5,'リブロワークス','インプレス');

INSERT INTO item_list(isbn,name) SELECT item_master.isbn,item_master.name FROM item_master;

INSERT INTO members VALUES(1,'テスト1','てすといち','東京都1','03-000-0000','aaaa@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(2,'テスト2','てすとに','東京都2','03-000-0000','bbbb@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(3,'テスト3','てすとさん','東京都3','03-000-0000','cccc@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(4,'テスト4','てすとよん','東京都4','03-000-0000','dddd@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(5,'テスト5','てすとご','東京都5','03-000-0000','eeee@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(6,'テスト6','てすとろく','東京都6','03-000-0000','ffff@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(7,'テスト7','てすとなな','東京都7','03-000-0000','gggg@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(8,'テスト8','てすとはち','東京都8','03-000-0000','hhhh@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(9,'テスト9','てすときゅう','東京都9','03-000-0000','iiii@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(10,'テスト10','てすとじゅう','東京都10','03-000-0000',	'jjjj@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(11,'テスト11','てすとじゅういち','東京都11','03-000-0000',	'kkkk@test.co.jp','2000-02-02','2020-02-02');
INSERT INTO members VALUES(12,'テスト12','てすとじゅうに','東京都12',	'03-000-0000','llll@test.co.jp','2000-02-02','2020-02-02');