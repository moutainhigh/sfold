package com.shifeng.seller.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.shifeng.entity.freight.ExpressConfig;
import com.shifeng.entity.order.ExpressOrder;
import com.shifeng.entity.order.OrderInfo;
import com.shifeng.plugin.page.Page;
import com.shifeng.seller.entity.users.Users;
import com.shifeng.seller.freight.service.ExpressConfigService;
import com.shifeng.seller.order.dto.OrderInfoDTO;
import com.shifeng.seller.order.dto.SearchOrderInfoDTO;
import com.shifeng.seller.order.service.ExpressOrderService;
import com.shifeng.seller.order.service.OrderInfoService;
import com.shifeng.util.Const;


/** 
 * 订单表(o_orderInfo)Controller
 * @author Win Zhong 
 * @version Revision: 1.00 
 *  Date: 2017-02-24 10:43:55 
 */ 
@Controller
@RequestMapping(value="/orderInfo")
public class OrderInfoController{
	
	@Resource(name="orderInfoServiceImpl")
	private OrderInfoService orderInfoServiceImpl;

	@Resource(name="expressConfigServiceImpl")
	private ExpressConfigService expressConfigServiceImpl;
	
	/**
	 * 列表页面
	 * @param mv
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goOrderInfoList")
	public ModelAndView goOrderInfoList(ModelAndView mv,String state) throws Exception{
		if(StringUtils.isEmpty(state)){
			state = "1";
		}
		mv.addObject("state", state);
		mv.setViewName("order/orderinfo");
		return mv;
	}
	 
	 
	/**
	 * 查询所有
	 * @param page
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findAllOrderInfo")
	@ResponseBody
	public ModelAndView findAllOrderInfo(ModelAndView mv,Page page,SearchOrderInfoDTO dto){
		if(dto==null){
			dto = new SearchOrderInfoDTO();
		}
		page.setT(dto);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<OrderInfoDTO> orderInfos = orderInfoServiceImpl.findAllOrderInfo(page);
			mv.addObject("orderInfos", orderInfos);
			mv.addObject("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mv.setViewName("order/orderinfoList");
		return mv;
	}
 
 
	/**
	 * 跳转编辑页面
	 * @param mv
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goOrderInfoEdit")
	@ResponseBody
	public ModelAndView goOrderInfoEdit(ModelAndView mv,String id) throws Exception{
		mv.addObject("id", id);
		mv.setViewName("order/orderInfoEdit");
		return mv;
	}
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findOrderInfoById")
	@ResponseBody
	public Map<String,Object> findOrderInfoById(String id) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		OrderInfo orderInfo = orderInfoServiceImpl.findOrderInfoById(id);
		map.put("orderInfo",orderInfo);
		return map;
	}
	
	
	/**
	 * 修改
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOrderInfo")
	@ResponseBody
	public Map<String,Object> updateOrderInfo(OrderInfo orderInfo,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		try {
			orderInfoServiceImpl.updateOrderInfo(orderInfo);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteOrderInfo")
	@ResponseBody
 	public Map<String,Object> deleteOrderInfo(String id) throws Exception{
 		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		try {
			orderInfoServiceImpl.deleteOrderInfo(id);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
 	}
 
	/**
	 * 修改备注
	 * @param orderInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateOrderInfoRemark")
	@ResponseBody
	public Map<String,Object> updateOrderInfoRemark(OrderInfo orderInfo,HttpSession session) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		orderInfo.setSellerId(user.getShopid());
		try {
			orderInfoServiceImpl.updateOrderInfoRemark(orderInfo);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
	}
	
	/**
	 * 跳转商品出库页面
	 * @return
	 */
	@RequestMapping(value="goShipments")
	@ResponseBody
	public ModelAndView goShipments(ModelAndView mv,String orderId){
		Page page = new Page();
		SearchOrderInfoDTO dto = new SearchOrderInfoDTO();
		dto.setOrderId(orderId);
		page.setT(dto);
		
		try {
			List<OrderInfoDTO> orderInfos = orderInfoServiceImpl.findAllOrderInfo(page);
			
			if(orderInfos!=null&&orderInfos.size()>0){
				mv.addObject("orderInfo", orderInfos.get(0));
				
				//系统快递
				List<ExpressConfig> expressconfig = expressConfigServiceImpl.findAllExpressConfig();
				mv.addObject("expressconfig", expressconfig);
				
				mv.setViewName("order/shipments");
			}else{
				mv.setViewName("redirect:orderInfo/goOrderInfoList.html");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 取消订单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="cancelOrder")
	@ResponseBody
	public Map<String,Object> cancelOrder(String id,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(Const.RESPONSE_STATE, 500);
		Users user = (Users) session.getAttribute(Const.SELLER_SESSION_USER);
		try {
			orderInfoServiceImpl.cancelOrder(id,user);
			map.put(Const.RESPONSE_STATE, Const.RESPONSE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			map.put(Const.ERROR_INFO, "保存异常，请稍后重试!!!");
		}
		return map;
	}
	
}
