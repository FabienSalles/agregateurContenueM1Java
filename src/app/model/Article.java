package app.model;

import java.util.Date;

import org.w3c.dom.Element;

public class Article implements Model
{
	protected String title;
	protected String content;
	protected String date;
	protected String author;
	
	public Article(Element item) {
		
	}

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Article : \n Title : " + title + "\n Content : \n" + content + "\n Date : "
				+ date + "\n Author : " + author + "";
	}
	
}
