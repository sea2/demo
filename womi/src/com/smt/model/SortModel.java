package com.smt.model;

public class SortModel {

	private String name;   //��ʾ������
	private String sortLetters;  //��ʾ����ƴ��������ĸ
	private String alias;
	private String fid;
	private String ����ǩ��;
	
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
    public String get����ǩ��()
    {
        return ����ǩ��;
    }
    public void set����ǩ��(String ����ǩ��)
    {
        this.����ǩ�� = ����ǩ��;
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
