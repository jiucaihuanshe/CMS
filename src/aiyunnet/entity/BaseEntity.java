package aiyunnet.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import aiyunnet.utils.DateTimeFormatConverter;

public class BaseEntity
{
	private Date addon;
	private Date editon;
	private Date deleteon;
	private short flagDelete;

	@JsonSerialize(using = DateTimeFormatConverter.class)
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
