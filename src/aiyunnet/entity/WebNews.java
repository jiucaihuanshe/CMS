package aiyunnet.entity;

import java.util.List;

public class WebNews extends BaseEntity
{
	private int id;
	private int menuId;
	private String title;
	private String subTitle;
	private String brief;
	private String content;
	private String linkUrl;
	private String author;
	private int readCount;
	private int sortOrder;
	private String keywords;
	private short status;

	//extend
	private String fileIds;
	private List<WebFile> files;
	
	public List<WebFile> getFiles()
	{
		return files;
	}

	public void setFiles(List<WebFile> files)
	{
		this.files = files;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getMenuId()
	{
		return menuId;
	}

	public void setMenuId(int menuId)
	{
		this.menuId = menuId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getSubTitle()
	{
		return subTitle;
	}

	public void setSubTitle(String subTitle)
	{
		this.subTitle = subTitle;
	}

	public String getBrief()
	{
		return brief;
	}

	public void setBrief(String brief)
	{
		this.brief = brief;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getLinkUrl()
	{
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl)
	{
		this.linkUrl = linkUrl;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public int getReadCount()
	{
		return readCount;
	}

	public void setReadCount(int readCount)
	{
		this.readCount = readCount;
	}

	public short getStatus()
	{
		return status;
	}

	public void setStatus(short status)
	{
		this.status = status;
	}

	public void setSortOrder(int sortOrder)
	{
		this.sortOrder = sortOrder;
	}

	public int getSortOrder()
	{
		return sortOrder;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public void setFileIds(String fileIds){
		this.fileIds=fileIds;
	}
	
	public String getFileIds(){
		return fileIds;
	}
}
