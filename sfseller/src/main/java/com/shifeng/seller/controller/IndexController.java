package com.shifeng.seller.controller;



import com.shifeng.util.Const;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< .mine
import com.shifeng.seller.entity.users.Users;
||||||| .r487
import com.shifeng.seller.entity.menu.Menus;
import com.shifeng.seller.entity.users.Users;
=======
import javax.servlet.http.HttpSession;
>>>>>>> .r887

/**
 * 
*    
* 项目名称：compass-data   
* 类名称：IndexController   
* 类描述：  登录成功跳转控制
* @version    
*
 */
@Controller
public class IndexController {

	/**
	 * 跳转到后台管理首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public ModelAndView adminIndex(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
<<<<<<< .mine
		Users user = (Users) session.getAttribute(Const.SESSION_USER);


		mv.setViewName("admin/index");
||||||| .r487
		Users user = (Users) session.getAttribute(Const.SESSION_USER);
        List<Menus> menus =new ArrayList<>();
		mv.addObject("menus", menus);
		mv.setViewName("admin/index");
=======
		mv.setViewName("index");
>>>>>>> .r887
		return mv;
	}
	 

	/**
	 * 方法描述：goRegister
	 * 返回类型：ModelAndView
	 * @return
	 */
	@RequestMapping(value="/register")
	public ModelAndView goRegister(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	
	
	/**
	 * 方法描述：记录访问的路径，登录过后进行跳转
	 * 返回类型：ModelAndView
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/loginSaveUrl")
	public ModelAndView goLogin(String url,HttpSession session){
		session.setAttribute(Const.SAVE_URL, url);
		ModelAndView mv = new ModelAndView("redirect:/login.html");
		return mv;
	}
	

	
	
<<<<<<< .mine
	/**
	 * 方法描述：记录访问的路径，登录过后进行跳转
	 * 返回类型：ModelAndView

	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView goLogin(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
||||||| .r487
	/**
	 * 方法描述：记录访问的路径，登录过后进行跳转
	 * 返回类型：ModelAndView
	 * @param url
	 * @return
	 */
	@RequestMapping(value="/login")
	public ModelAndView goLogin(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
=======

>>>>>>> .r887
	
	
}
