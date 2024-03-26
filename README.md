### 使用說明

1.  JDK version = 11;

2.  clone 專案以後，執行 mvn clean install

3.  運行 ApiSpringBoot2Application.java

    以啟動 Spring Boot

3.  打開 http://localhost:8091/practice5/swagger-ui/index.html

    以查看 api 狀態

### Hibernate、Spring Data JPA 與 Mybatis的差異

- Hibernate:

1. Model層: 定義與數據庫表對應的Java實體類
2. DAO層: 定義與數據庫交互的代碼，包括使用Hibernate提供的Session對象進行數據操作
3. Service層: 封裝業務邏輯
4. Controller層: 用於處理請求和返回響應

- Spring Data JPA:

1. Model層: 定義與數據庫表對應的Java實體類
2. Repository層: 定義對數據庫的CRUD操作
3. Service層：封裝業務邏輯
4. Controller層: 處理請求和返回響應

- MyBatis:

1. Model層: 定義與數據庫表對應的Java實體類
2. Mapper層: 代替了DAO層的角色，定義與數據庫交互的Mapper接口，以及對應的XML映射文件，其中定義了SQL語句與Java方法的映射關係
    - 在CRUD方法上加Annotation來寫SQL指令
        ```JAVA
        @Select("SELECT * FROM users")
        List<User> getAllUsers();
        ```
    - 使用mapper.xml來寫SQL指令兩種方式
        ```XML
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper
                PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

        <mapper namespace="com.mli.assetmybatis.dao.UnitsDAO">

            <resultMap id="unitsResultMap" type="com.model.Units">
                <id property="id" column="id"/>
                <result property="creationDate" column="creation_date" typeHandler="com.util.LocalDateTimeHandler"/>
            </resultMap>

            <select id="findById" resultMap="unitsResultMap" parameterType="int">
                SELECT * FROM units WHERE id = #{id}
            </select>

        </mapper>
        ```

3. Service層：負責業務邏輯的封裝
4. Controller層: 處理請求和返回響應
