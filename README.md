### 使用說明

1. JDK version = 11;

2. 運行 ApiSpringBoot2Application.java 

      以啟動 Spring Boot

3. (optional)使用 postman 進行以下測試
      - 方法： 使用 POST 方法。

            網址： http://localhost:8091/practice5/assets/select-all

            備註： 檢索所有資產的資訊。

            傳入值： 無。

      - 方法： 使用 POST 方法。

            網址： http://localhost:8091/practice5/assets/update

            備註： 更新資產資訊。

            傳入值：

                  {
                        "assetNumber": "A003",
                        "assetName": "x",
                        "unitOfUse": "YourUnitOfUse",
                        "user": "YourUser",
                        "creationDate": "2024-03-19",
                        "value": 0
                  }

      - 方法： 使用 POST 方法。

            網址： http://localhost:8091/practice5/assets/select-by-asset-number

            備註： 根據資產編號檢索資產資訊。

            傳入值： 

            
                  {
                        "assetNumber" : "A050"
                  }
            

      - 方法： 使用 POST 方法。

            網址： http://localhost:8091/practice5/assets/add

            備註： 新增資產。

            傳入值： 
            
                  {
                        "assetNumber": "A050",
                        "assetName": "Computer",
                        "unitOfUse": "IT Department",
                        "user": "John Doe",
                        "creationDate": "2024-03-19",
                        "value": 1500.0
                  }

      - 方法： 使用 POST 方法。

            網址： http://localhost:8091/practice5/assets/delete

            備註： 刪除資產。

            傳入值： 

                  {
                        "assetNumber" : "A050"
                  }

4. 打開 http://localhost:8091/practice5/swagger-ui/index.html

      以查看api狀態
