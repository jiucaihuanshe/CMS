package aiyunnet.entity;

import java.util.Date;

public class WebMenu
{
	private int id;
	private String title;
	private int parentId;
	private short status;
	private Date addon;
	private Date editon;
	private Date deleteon;
	private short flagDelete;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getParentId()
	{
		return parentId;
	}

	public void setParentId(int parentId)
	{
		this.parentId = parentId;
	}

	public short getStatus()
	{
		return status;
	}

	public void setStatus(short status)
	{
		this.status = status;
	}

	public Date getAddon()
	{
		return addon;
	}

	public void setAddon(Date addon)
	{
		this.addon = addon;
	}

	public Date getEditon()
	{
		return editon;
	}

	public void setEditon(Date editon)
	{
		this.editon = editon;
	}

	public Date getDeleteon()
	{
		return deleteon;
	}

	public void setDeleteon(Date deleteon)
	{
		this.deleteon = deleteon;
	}

	public short getFlagDelete()
	{
		return flagDelete;
	}

	public void setFlagDelete(short flagDelete)
	{
		this.flagDelete = flagDelete;
	}

}
