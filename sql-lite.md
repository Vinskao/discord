讓我們先暫緩一下Java，先來補強SQL能力。

目標
 
 留言
熟悉SQLite的使用
熟悉SQL語法
 
預估時間
0.5 天

 
需求說明
請在本機安裝DB Browser for SQLite
請使用這個網站: https://www.1keydata.com/tw/sql/sql.html
在本機練習SQL指令(26項)、表格處理(12項)、進階SQL(13項)
 
一些指令
提供一些練習題中需要使用的表格，請留意每一個練習中的資料未必相同，請自行修改。

CREATE TABLE Store_Information (
Store_Name TEXT,
Sales INTEGER,
Txn_Date TEXT
);
 
INSERT INTO Store_Information("Store_Name","Sales","Txn_Date")
VALUES 
("Los Angeles","1500","05-Jan-1999"),
("San Diego","250","07-Jan-1999"),
("Los Angeles","300","08-Jan-1999"),
("Boston","700","08-Jan-1999");
 
 
CREATE TABLE Geography (
Region_Name TEXT,
Store_Name TEXT
);
 
INSERT INTO Geography("Region_Name","Store_Name")
VALUES
("East","Boston"),
("East","New York"),
("West","Los Angeles"),
("West","San Diego");
 
CREATE TABLE Internet_Sales (
Txn_Date TEXT,
Sales INTEGER
);
 
INSERT INTO Internet_Sales("Txn_Date","Sales")
VALUES
("07-Jan-1999","250"), 
("10-Jan-1999","535"), 
("11-Jan-1999","320"), 
("12-Jan-1999","750");
 
CREATE TABLE Store_Sales (
Store_ID INTEGER,
Salesperson TEXT,
Sales INTEGER
);
 
INSERT INTO Store_Sales ("Store_ID","Salesperson","Sales")
VALUES
("1","Aaron","374"), 
("1","Beatrice","492"), 
("1","Cathy","430"),
("2","Dan","462"),
("2","Elmo","747"),
("2","Frank","1332"),
("2","Gina","898"),
("2","Harry","603"),
("3","Isabel","247"),
("3","Jimmy","1030"),
("3","Kara","1030"),
("3","Lamar","1314"),
("3","Mary","462");
 
CREATE TABLE Total_Sales (
Name TEXT,
Sales INTEGER
);
 
INSERT INTO Total_Sales ("Name","Sales")
VALUES
("John","10"), 
("Jennifer","15"),
("Stella","20"),
("Sophia","40"),
("Greg","50"),
("Jeff","22");