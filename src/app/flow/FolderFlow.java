package app.flow;

import java.io.File;
import java.net.URL;
import java.util.Set;

import app.article.Article;
import app.core.exception.UnknownTypeException;

public class FolderFlow extends Flow implements ArticleRecover
{

	public FolderFlow(String url)
	{
		super(url);
		this.type = FlowType.FOLDER;
		this.recover();
	}
	
	@Override
	public void recover() {
		File[] files = this.listFiles(this.path);
		System.out.println(files);
		
	}
	
	public File[] listFiles(String path)
	{ 
		File[] files = null; 
		File directoryToScan = new File(path); 
		files = directoryToScan.listFiles(); 
		return files; 
	}
	
	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String recoverType(URL url) throws UnknownTypeException {
		
		String str[] = url.getFile().split("\\.");
		
		if (str.length==1)
		{
			throw new UnknownTypeException("type missing");
		}

		return str[str.length-1];
	}
}
