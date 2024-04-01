### 使用說明

1.  JDK version = 11;

2.  clone 專案以後，執行 mvn clean install

3.  運行 ApiSpringBoot2Application.java

    以啟動 Spring Boot

4.  打開 http://localhost:8088/swagger-ui/index.html

    以查看 api 狀態

### Hibernate、Spring Data JPA 與 Mybatis 的差異

- Hibernate:

1. Model 層: 定義與數據庫表對應的 Java 實體類
2. DAO 層: 定義與數據庫交互的代碼，包括使用 Hibernate 提供的 Session 對象進行數據操作
3. Service 層: 封裝業務邏輯
4. Controller 層: 用於處理請求和返回響應

- Spring Data JPA:

1. Model 層: 定義與數據庫表對應的 Java 實體類
2. Repository 層: 定義對數據庫的 CRUD 操作
3. Service 層：封裝業務邏輯
4. Controller 層: 處理請求和返回響應

- MyBatis:

1. Model 層: 定義與數據庫表對應的 Java 實體類
2. Mapper 層: 代替了 DAO 層的角色，定義與數據庫交互的 Mapper 接口，以及對應的 XML 映射文件，其中定義了 SQL 語句與 Java 方法的映射關係

   - 在 CRUD 方法上加 Annotation 來寫 SQL 指令
     ```JAVA
     @Select("SELECT * FROM user")
     List<User> getAllUser();
     ```
   - 使用 mapper.xml 來寫 SQL 指令兩種方式

     ```XML
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE mapper
             PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

     <mapper namespace="com.mli.discord.module.login.dao.UnitsDAO">

         <resultMap id="unitsResultMap" type="com.model.Units">
             <id property="id" column="id"/>
             <result property="creationDate" column="creation_date" typeHandler="com.util.LocalDateTimeHandler"/>
         </resultMap>

         <select id="findById" resultMap="unitsResultMap" parameterType="int">
             SELECT * FROM units WHERE id = #{id}
         </select>

     </mapper>
     ```

3. Service 層：負責業務邏輯的封裝
4. Controller 層: 處理請求和返回響應
