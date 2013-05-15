package app.article;

import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;


public class MarkdownArticle extends Article implements ArticleSearch {

	public MarkdownArticle(String path) {
		PegDownProcessor processor = new PegDownProcessor();
		RootNode r = processor.parseMarkdown(path.toCharArray());
		r.toString();
	}
	
	@Override
	public Article search(String keyworkds) {
		return null;
	}

}
