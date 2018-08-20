package aiyunnet.web.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import aiyunnet.entity.*;
import aiyunnet.service.AccountService;
import aiyunnet.service.AdminService;
import aiyunnet.utils.ContextHelper;
import aiyunnet.web.custom.vo.*;;

@Controller
@RequestMapping("/Admin/")
public class AdminController
{
	@Autowired
	@Qualifier("accountServiceImpl")
	private AccountService accountService;
	
	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public ModelAndView main()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/main");
		return mv;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/index");
		return mv;
	}

	@RequestMapping(value = "sysUserList", method = RequestMethod.GET)
	public ModelAndView sysUserList()
	{
		List<SysUser> sysUsers = accountService.getSysUserList();
		
		for (SysUser sysUser : sysUsers)
		{
			List<SysRole> roles =adminService.getSysRolesByUserId(sysUser.getId());
			String roleNames = "";
			for (SysRole role : roles)
			{
				roleNames += "," + role.getName();
			}
			if (roleNames.length() > 0)
			{
				roleNames = roleNames.substring(1, roleNames.length() - 1);
			}
			sysUser.setRoleNames(roleNames);
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("sysUserList", sysUsers);
		mv.setViewName("admin/sysUserList");
		return mv;
	}

	@RequestMapping(value = "webMenuList", method = RequestMethod.GET)
	public ModelAndView webMenuList()
	{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/webMenuList");
		return mv;
	}

	@RequestMapping(value = "webNewsList", method = RequestMethod.GET)
	public ModelAndView webNewsList(int mId)
	{
		List<WebNews> webNewsList = adminService.getWebNewsList(mId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("mId", mId);
		mv.addObject("webNewsList", webNewsList);
		mv.setViewName("admin/webNewsList");

		return mv;
	}

	@RequestMapping(value = "webNews", method = RequestMethod.GET)
	public ModelAndView webNews(int id, int mId)
	{
		WebNews webNews = null;
		if (id == 0)
		{
			webNews = new WebNews();
			webNews.setId(0);
		} else
		{
			webNews = adminService.getWebNewsById(id);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("mId", mId);
		mv.addObject("webNews", webNews);
		mv.setViewName("admin/webNews");
		return mv;
	}

	@RequestMapping(value = "webInfo", method = RequestMethod.GET)
	public ModelAndView webInfo(int id, int mId)
	{
		WebNews webNews = null;
		if (id == 0)
		{
			webNews = new WebNews();
			webNews.setId(0);
		} else
		{
			webNews = adminService.getWebNewsById(id);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("mId", mId);
		mv.addObject("webNews", webNews);
		mv.setViewName("admin/webInfo");

		return mv;
	}

	@RequestMapping(value = "addAndEditWebNews", method = RequestMethod.POST)
	@ResponseBody
	public String addAndEditWebNews(WebNews webNews)
	{
		if (webNews.getId() > 0)
		{
			adminService.updateWebNews(webNews);
		} else
		{
			adminService.addWebNews(webNews);
		}
		return "success";
	}

	@RequestMapping(value = "deleteWebNews", method = RequestMethod.POST)
	@ResponseBody
	public String deleteWebNews(int id)
	{
		adminService.deleteWebNews(id);
		return "success";
	}

	@RequestMapping(value = "multiDeleteWebNews", method = RequestMethod.POST)
	@ResponseBody
	public String multiDeleteWebNews(String ids)
	{
		if (ids != null && ids.length() > 0)
		{
			adminService.deleteWebNewsByIds(ids);
		}

		return "success";
	}

	@RequestMapping(value = "deleteWebFile", method = RequestMethod.POST)
	@ResponseBody
	public String deleteWebFile(int id)
	{
		int n = adminService.deleteWebFile(id);
		return String.valueOf(n);
	}

	//@RequiresPermissions(value = { "sysUser:add" })
	@RequestMapping(value = "sysUser", method = RequestMethod.GET)
	public ModelAndView sysUser(int id)
	{
		ModelAndView mView = new ModelAndView();
		SysUser sysUser = null;
		if (id == 0)
		{
			sysUser = new SysUser();
		} else
		{
			sysUser = accountService.getSysUser(id);
			List<SysRole> existedRoles = adminService.getSysRolesByUserId(id);
			String existedRoleIds = "";
			for (SysRole role : existedRoles)
			{
				existedRoleIds += "," + role.getId();
			}
			if (existedRoleIds.length() > 0)
			{
				existedRoleIds = existedRoleIds.substring(1, existedRoleIds.length());
			}
			mView.addObject("existedRoleIds", existedRoleIds);
		}
		List<SysRole> roles = adminService.getSysRoles();

		mView.addObject("sysUser", sysUser);
		mView.addObject("roles", roles);
		mView.setViewName("admin/sysUser");
		return mView;
	}

	@RequestMapping(value = "addOrUpdateUser", method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdateUser(SysUser sysUser, String roleIds)
	{
		if (sysUser.getId() == 0)
		{
			accountService.addSysUser(sysUser, roleIds);
		} else
		{
			accountService.updateSysUser(sysUser, roleIds);
		}
		return "1";
	}

	@RequestMapping(value = "searchWebNews", method = RequestMethod.POST)
	@ResponseBody
	public List<WebNews> searchWebNews(SearchNewsObject searchObj)
	{
		List<WebNews> results = adminService.searchWebNews(searchObj.getTitle(),
				searchObj.getKeywords(), searchObj.getAuthor(), searchObj.getContent(), searchObj.getMenuId());
		return results;
	}

	@RequestMapping(value = "sysRoleList", method = RequestMethod.GET)
	public ModelAndView sysRoleList()
	{
		List<SysRole> sysRoles = adminService.getSysRoles();
		ModelAndView mView = new ModelAndView();
		mView.addObject("sysRoles", sysRoles);
		mView.setViewName("admin/sysRoleList");
		return mView;
	}

	@RequestMapping(value = "sysRole", method = RequestMethod.GET)
	public ModelAndView sysRole(int id)
	{
		SysRole sysRole = null;
		if (id == 0)
		{
			sysRole = new SysRole();
		} else
		{
			sysRole = adminService.getSysRole(id);
		}

		ModelAndView mView = new ModelAndView();
		mView.addObject("sysrole", sysRole);
		mView.setViewName("admin/sysRole");
		return mView;
	}

	@RequestMapping(value = "doAddUpdateSysRole", method = RequestMethod.POST)
	@ResponseBody
	public String doAddUpdateSysRole(SysRole sysRole)
	{
		int n = 0;
		if (sysRole.getId() == 0)
		{
			n = adminService.addSysRole(sysRole);
		} else
		{

		}
		return String.valueOf(n);
	}
	
	@RequestMapping(value = "deleteSysUser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSysUser(int id)
	{
		adminService.deleteSysUser(id);
		return "ok";	
	}

	@RequestMapping(value = "deleteSysRole", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSysRole(int id)
	{
		adminService.deleteSysRole(id);
		return "ok";	
	}
	
	@RequestMapping(value = "upload")
	@ResponseBody
	public UploadState upload(MultipartFile file) throws Exception
	{
		UploadState uploadState = new UploadState();
		if (file == null)
		{
			uploadState.setCode(0);
			uploadState.setFileId(0);
			return uploadState;
		}

		int fileId = 0;
		String fileName = file.getOriginalFilename();
		String fsName = "\\upload\\images\\" + fileName;
		File upfile = new File(ContextHelper.getRealPath() + fsName);
		if (!upfile.getParentFile().exists())
		{
			upfile.getParentFile().mkdirs();
		}

		file.transferTo(upfile);
		WebFile webFile = new WebFile();
		webFile.setOutId(0);
		webFile.setFileName(fileName);
		webFile.setFileType("image");
		webFile.setFilePath(fsName);
		
		fileId = adminService.addWebFile(webFile);
		uploadState.setCode(1);
		uploadState.setFileId(fileId);

		return uploadState;
	}

	//src='jt.com/upload/2018/08/05/xxx.jpg'
	//img src='jt.com/admin/download/id'
	@RequestMapping(value = "download")
	public ResponseEntity<byte[]> download(int fileId) throws Exception
	{
		WebFile webFile = adminService.getWebFileById(fileId);
		File downloadFile = null;
		String fileName = "";
		if (webFile != null)
		{
			String filePath = ContextHelper.getRealPath() + webFile.getFilePath();
			downloadFile = new File(filePath);
			fileName = webFile.getFileName();

			if (!downloadFile.exists())
			{
				filePath = ContextHelper.getRealPath()+"\\static\\images\\NoImage.png";
				downloadFile = new File(filePath);
				fileName = "NoImage.png";
			}
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", fileName);

		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadFile), headers, HttpStatus.CREATED);
	}

	// 普通下载，不使用框架
	public void downloadFile(int fileId, HttpServletResponse response) throws Exception
	{
		WebFile webFile =adminService.getWebFileById(fileId);

		BufferedInputStream bInputStream = null;
		BufferedOutputStream bOutputStream = null;
		if (webFile != null)
		{
			String downloadpath = ContextHelper.getRealPath() + webFile.getFilePath();
			long fileLength = new File(downloadpath).length();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment; filename=" + webFile.getFileName());
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bInputStream = new BufferedInputStream(new FileInputStream(downloadpath));
			bOutputStream = new BufferedOutputStream(response.getOutputStream());

			byte[] bs = new byte[1024];
			int readCount = 0;
			while ((readCount = bInputStream.read(bs, 0, bs.length)) != -1)
			{
				bOutputStream.write(bs, 0, readCount);
			}
			bInputStream.close();
			bOutputStream.close();
		}

	}

	
	
}
