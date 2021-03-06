package com.shifeng.op.product.service;

import java.util.List;
import com.shifeng.entity.product.Images;
import com.shifeng.plugin.page.Page;

/** 
 * 商品图片表(p_images)接口
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-02-24 11:14:12 
 */  
public interface ImagesService {

	/**
	 * 查询所有
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<Images> findAllImages(Page page) throws Exception;
	
	/**
	 * 根据ID查询
	 */
	public Images findImagesById(String id) throws Exception;
	
	/**
	 * 修改
	 * @param images
	 * @throws Exception
	 */
	public void updateImages(Images images) throws Exception;
	
	/**
	 * 新增
	 * @param images
	 * @throws Exception
	 */
	public void saveImages(Images images) throws Exception;
    
    /**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteImages(String id) throws Exception;
	
}
