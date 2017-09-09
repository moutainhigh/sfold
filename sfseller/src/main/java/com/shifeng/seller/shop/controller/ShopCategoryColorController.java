package com.shifeng.seller.shop.controller;

import com.shifeng.entity.shop.ShopCategoryColor;
import com.shifeng.seller.controller.BaseController;
import com.shifeng.seller.entity.users.Users;
import com.shifeng.seller.shop.service.ShopCategoryColorService;
import com.shifeng.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/** 
 * 店铺分类颜色属性(p_shop_category_color)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-03-28 13:20:44 
 */ 
@Controller
@RequestMapping(value="/shopcategorycolor")
public class ShopCategoryColorController extends BaseController {

	@Resource(name="shopcategorycolorServiceImpl")
	private ShopCategoryColorService shopcategorycolorServiceImpl;

	/**
	 * 新增
	 * @param shopcategorycolor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveShopCategoryColor")
	@ResponseBody
	public Map<String,Object> saveShopCategoryColor(ShopCategoryColor shopcategorycolor,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		shopcategorycolor.setShopid(user.getShopid());
		try {
			shopcategorycolorServiceImpl.saveShopCategoryColor(shopcategorycolor,map);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 修改
	 * @param shopcategorycolor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateShopCategoryColor")
	@ResponseBody
	public Map<String,Object> updateShopCategoryColor(ShopCategoryColor shopcategorycolor,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		try {
			shopcategorycolorServiceImpl.updateShopCategoryColor(shopcategorycolor);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteShopCategoryColor")
	@ResponseBody
 	public Map<String,Object> deleteShopCategoryColor(String id) throws Exception{
 		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		try {
			shopcategorycolorServiceImpl.deleteShopCategoryColor(id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存失败，请稍后重试!!!");
		}
		return map;
 	}
 
}
