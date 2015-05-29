package com.smt.model;

public class Emotion {

	private String phrase;// 表情使用的替代文字
	private String type;
	private String url;// 表情图片存放的位置
	private boolean hot;// 是否为热门表情
	private boolean common;// 是否属于通用
	private String category;// 表情分类
	private String icon;
	private String picid;
	private String saveName;
	
	public Emotion() {
		// TODO Auto-generated constructor stub
	}

	public Emotion(String url, String phrase) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.phrase = phrase;
	}
	/**
	 *不带后缀.gif,从drawable中获取的时候需要用这个名字
	 * @return
	 */
	public String getSaveName2() {
//		String s1 = url.substring(url.lastIndexOf("/") + 1, url.length());
//		return s1.substring(0, s1.indexOf(".gif"));
		return url;
	}
	/**
	 * 带后缀.gif
	 * @return
	 */
	public String getSaveName(){
		
//		return url.substring(url.lastIndexOf("/")+1, url.length());
		return url;
	}
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isHot() {
		return hot;
	}
	public void setHot(boolean hot) {
		this.hot = hot;
	}
	public boolean isCommon() {
		return common;
	}
	public void setCommon(boolean common) {
		this.common = common;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPicid() {
		return picid;
	}
	public void setPicid(String picid) {
		this.picid = picid;
	}

	
	
}
