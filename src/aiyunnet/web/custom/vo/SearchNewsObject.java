package aiyunnet.web.custom.vo;

public class SearchNewsObject
{
	private String title;
	private String keywords;
	private String author;
	private String content;
	private Integer menuId;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setMenuId(Integer menuId)
	{
		this.menuId=menuId;
	}
	
	public Integer getMenuId()
	{
		return menuId;
	}
}
