// package com.mli.assetmybatis.dao;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertNull;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.TestPropertySource;

// import com.mli.assetmybatis.model.Assets;

// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @SpringBootTest
// @TestPropertySource("classpath:application.yml")
// public class AssetsDaoJdbcTest {
// @Autowired
// private AssetsDaoJdbc assetsDaoJdbc;

// @Test
// public void testSelectAll() {
// List<Assets> assetsList = assetsDaoJdbc.selectAll();
// assertNotNull(assetsList);
// assertEquals(20, assetsList.size());
// }
// // @Test
// // public void testSelect() {
// // // 資產編號存在於數據庫中的情況
// // String existingAssetNumber = "A001";
// // Assets asset = assetsDaoJdbc.select(existingAssetNumber);
// // if (asset !=null){
// // System.out.println(asset.toString());
// // }
// // assertNotNull(asset);
// // assertEquals(existingAssetNumber, asset.getAssetNumber());

// // // 資產編號不存在於數據庫中的情況
// // String nonExistingAssetNumber = "A999";
// // asset = assetsDaoJdbc.select(nonExistingAssetNumber);
// // assertNull(asset); // 期望返回 null，表示未找到對應的資產
// // }
// }
