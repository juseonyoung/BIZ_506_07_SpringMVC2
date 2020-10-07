package com.biz.book.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="rss") //이게 있어야 XML 파싱함 
public class Book_XML_Parent {

	@XmlElement(name="channel")
	public Book_XML_Channel channel;
	
}
