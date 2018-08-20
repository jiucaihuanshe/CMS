package aiyunnet.entity;

public class WebFile extends BaseEntity
{
	private int id;
	private int outId;
	private String fileName;
	private String fileType;
	private String filePath;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getOutId()
	{
		return outId;
	}

	public void setOutId(int outId)
	{
		this.outId = outId;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

}
