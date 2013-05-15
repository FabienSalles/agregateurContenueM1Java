package app.article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import app.database.Model;
import app.database.Query;

public class Article implements Model
{
	/**
	 * Id of the article in the database
	 */
	protected Integer rowid;
	
	/**
	 * Prepare statement for request in database
	 */
	protected PreparedStatement prepare;
	
	protected String title;
	protected String content;
	protected String date;
	protected String author;
	
	
	public Integer getRowid()
	{
		return rowid;
	}

	public void setRowid(Integer rowid)
	{
		this.rowid = rowid;
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
	
	public Date getDateTime()
	{
		return new Date();
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
	public void save()
	{
		try {
			this.prepare = Query.getInstance().prepareStatement("INSERT INTO article (title, content, date, author) values (?, ?, ?, ?)");
			this.prepare.setString(1, this.title);
			this.prepare.setString(2,  this.getContent());
			this.prepare.setString(3, this.date);
			this.prepare.setString(4,  this.author);
			this.prepare.executeUpdate();
			this.rowid = this.prepare.getGeneratedKeys().getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update()
	{
		if (this.rowid != null)
		{
			try {
				this.prepare = Query.getInstance().prepareStatement("update flow SET title=?, content=?, date=?, author=? where rowid=?");
				this.prepare.setString(1, this.title);
				this.prepare.setString(2,  this.getContent());
				this.prepare.setString(3, this.date);
				this.prepare.setString(4,  this.author);
				this.prepare.setInt(5, this.rowid);
				this.prepare.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void delete()
	{
		if (this.rowid != null)
		{
			try {
				this.prepare = Query.getInstance().prepareStatement("DELETE FROM article SET where rowid=?");
				this.prepare.setInt(1, this.rowid);
				this.prepare.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", date=" + date + ", author="
				+ author + "]";
	}
	
	
}
