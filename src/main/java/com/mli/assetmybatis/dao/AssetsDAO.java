package com.mli.assetmybatis.dao;

import java.util.List;

import com.mli.assetmybatis.model.Assets;

/**
 * 資產 DAO MyBatis Mapper
 * 
 * @Author D3031104
 * @version 1.0
 */

public interface AssetsDAO {

	/**
	 * 從資料庫中選擇所有資產。
	 * 
	 * @return 資產列表
	 */
	List<Assets> findAll();

	/**
	 * 根據資產編號選擇資產。
	 * 
	 * @param assetNumber 資產編號
	 * @return 資產
	 */
	Assets findByAssetNumber(String assetNumber);

	/**
	 * 插入資產。
	 * 
	 * @param asset 資產
	 * @return 如果插入成功則返回 true，否則返回 false
	 */
	boolean insert(Assets asset);

	/**
	 * 更新資產。
	 * 
	 * @param asset 資產
	 * @return 如果更新成功則返回 true，否則返回 false
	 */
	boolean update(Assets asset);

	/**
	 * 刪除資產。
	 * 
	 * @param assetNumber 資產編號
	 * @return 如果刪除成功則返回 true，否則返回 false
	 */
	boolean delete(String assetNumber);

	/**
	 * 根據部門ID選擇資產。
	 * 
	 * @param unitId 部門ID
	 * @return 資產列表
	 */
	List<Assets> selectByUnitId(int unitId);

	/**
	 * 從資料庫中獲取最後一筆資產的ID。
	 * 
	 * @return 最後一筆資產的ID，如果資料庫中沒有資產則返回0。
	 */
	int getLastIdFromDatabase();

	/**
	 * 生成新的資產ID。
	 * 
	 * @return 新的資產ID
	 */
	int generateNewId();

}
