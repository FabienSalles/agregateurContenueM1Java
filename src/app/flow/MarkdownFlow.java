package app.flow;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.article.Article;

public class MarkdownFlow extends Flow {

	
	
	public MarkdownFlow(String url) {
		super(url);
		this.recover();
	}
	
	@Override
	public void recover() {
		try {
			URL url = new URL(this.path);
			System.out.println(this.path);
//			
//			BufferedInputStream in = new BufferedInputStream(url.openStream());
//			FileOutputStream out = new FileOutputStream()
//		    byte data[] = new byte[1024];
//		    int count;
//		    while((count = in.read(data,0,1024)) != -1)
//		    {
//		        out.write(data, 0, count);
//		    }
		    
		} catch (MalformedURLException e) {
			Logger.getLogger(MarkdownFlow.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(MarkdownFlow.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public Set<Article> search(String keyworkds) {
		return this.articles;
	}

}
