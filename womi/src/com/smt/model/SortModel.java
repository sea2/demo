package com.smt.model;

public class SortModel {

	private String name;   //显示的数据
	private String sortLetters;  //显示数据拼音的首字母
	private String alias;
	private String fid;
	private String 个性签名;
	
	public String getFid()
    {
        return fid;
    }
    public void setFid(String fid)
    {
        this.fid = fid;
    }
    public String getAlias()
    {
        return alias;
    }
    public void setAlias(String alias)
    {
        this.alias = alias;
    }
    public String get个性签名()
    {
        return 个性签名;
    }
    public void set个性签名(String 个性签名)
    {
        this.个性签名 = 个性签名;
    }
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
