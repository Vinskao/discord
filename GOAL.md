## 目標

1. 可使用Java進行資料的CRUD
2. 熟悉JDBC Template的使用
2. 熟悉yaml格式
 
## 預估時間

1 ~ 1.5 天

 
## 需求說明

1. 請建立Spring Boot專案，Swagger頁面須為http://localhost:8091/practice5/swagger-ui.html
2. 針對專案的配置，請使用yaml而不是properties
3. 使用SQLite作為資料庫，建立Table來存放資產資料，資料需有:
    - 財產編號
    - 財產名稱
    - 使用單位
    - 使用人
    - 資產建立日期
    - 資產價值
4. 資料持久化請使用JDBC Template
5. Java物件需有三種(或以上)不同型態的屬性。不要偷懶全部都用字串
6. 須能夠使用Java來做到資料的CRUD，需有以下的API:
    - 查詢所有資料
    - 查詢單筆資料
    - 新增一筆資料
    - 更新一筆資料
    - 刪除一筆資料
7. API的傳入與回傳值皆使用Json格式，回傳值請使用ResponseEntity