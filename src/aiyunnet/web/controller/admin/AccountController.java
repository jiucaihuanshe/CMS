package aiyunnet.web.controller.admin;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import aiyunnet.entity.SysUser;
import aiyunnet.service.AccountService;
import aiyunnet.utils.EncryUtil;
import aiyunnet.utils.ImageHelper;
import aiyunnet.utils.ContextHelper;

@Controller
@RequestMapping("/Account/")
public class AccountController
{
	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login()
	{
		return "admin/login";
	}

	@RequestMapping(value = "dologin", method = RequestMethod.POST)
	@ResponseBody
	public Integer dologin(String account, String password, String validateCode)
	{
		String existedCode = ContextHelper.current().getValidateCode();
		int retCode = 0;
		if (existedCode.equals(validateCode))
		{			
			retCode=accountService.login(account, password);
		} else
		{
			retCode = 5;
		}

		ContextHelper.current().removeValidateCode();

		return retCode;
	}

	@RequestMapping("dologout")
	public String dologout()
	{
		 Subject subject = SecurityUtils.getSubject();
	     subject.logout();
		return "redirect:login.do";
	}

	@RequestMapping(value = "doShowUserInfo", method = RequestMethod.GET)
	public ModelAndView doShowUserInfo(int id)
	{
		SysUser user = accountService.getSysUser(id);
		ModelAndView mView = new ModelAndView();
		mView.addObject("user", user);
		mView.setViewName("admin/userInfo");
		return mView;
	}

	@RequestMapping(value = "doChangePassword", method = RequestMethod.POST)
	@ResponseBody
	public String doChangePassword(String newPassword, int id)
	{
		int n = accountService.changeUserPassword(EncryUtil.md5Encrypt(newPassword), id);
		return String.valueOf(n);
	}
	
	@RequestMapping(value = "validateCode")
	public void validateCode(HttpServletResponse response, HttpServletRequest request)
	{
		Random r = new Random(new Date().getTime());
		int[] rcode = new int[4];
		String codeStr = "";
		for (int i = 0; i < 4; i++)
		{
			rcode[i] = r.nextInt(10);
			codeStr += rcode[i];
		}

		ContextHelper.current().setValidateCode(codeStr);
		BufferedImage img = ImageHelper.generateValidateCode(rcode);
		OutputStream out;
		try
		{
			out = response.getOutputStream();
			ImageIO.write(img, "png", out);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	@RequestMapping("noPermission")
	public String noPermission()
	{
		return "nopermission";
	}
	
}
