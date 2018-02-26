package com.e3.utils;

import java.io.Serializable;

public class Ad2Item implements Serializable{

	  /*{
	        "alt": "",
	        "href": "http://c.fa.jd.com/adclick?sid=2&cid=601&aid=3679&bid=0&unit=36612&advid=108828&guv=&url=http://sale.jd.com/act/Crv8iTP0zjsaVYR.html",
	        "index": 8,
	        "src": "http://img11.360buyimg.com/da/jfs/t313/170/1681775134/10831/7f4b7161/5440715aN1f03f497.jpg",
	        "ext": ""
	    }*/
	
	private String alt;
	private String href;
	private Integer index;
	private String src;
	private String ext;
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	
}
