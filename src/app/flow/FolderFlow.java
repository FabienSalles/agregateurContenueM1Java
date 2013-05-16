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
		
		for(File file : files)
		{
			try {
				String extension = this.recoverType(file.getAbsolutePath());
				if (extension != null)
				{
					switch(FlowType.getName(extension))
					{
						case HTML:
							Flow htmlflow = new HTMLFlow(file);
							this.articles.addAll(htmlflow.getArticles());
							break;
						case RSS:
							Flow rssflow = new RSSFlow(file);
							this.articles.addAll(rssflow.getArticles());
							break;
						default:
							break;
					}
				}
			} catch (UnknownTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public File[] listFiles(String path)
	{ 
		File[] files = null; 
		File fileOrDirectory = new File(path);
		
		if (fileOrDirectory.isDirectory())
		{
			files = fileOrDirectory.listFiles(); 
		}
		else
		{
			files[0] = fileOrDirectory;
		}
		
		return files; 
	}
	
	@Override
	public Set<Article> search(String keyworkds) {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String recoverType(String path) throws UnknownTypeException {
		
		String str[] = path.split("\\.");
		
		if (str.length > 1)
		{
			return str[str.length-1];
		}
		return null;
		
	}
}
