package aiyunnet.web.custom.vo;

public class UploadState
{
	private int code;
	private int fileId;
	private String url;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setFileId(int fileId)
	{
		this.fileId = fileId;
	}

	public int getFileId()
	{
		return fileId;
	}
}
