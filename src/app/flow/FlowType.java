package app.flow;

public enum FlowType {
	HTML("html"),
	RSS("rss"),
	MarkDown("md"),
	FOLDER("dossier"),
	DATABASE("bdd");
	
	private String type;
	
	FlowType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public static FlowType getName(String name)
	{
		FlowType flowtype = null;
		switch(name)
		{
			case "html":
				flowtype = HTML;
				break;
			case "rss":
				flowtype = RSS;
				break;
			case "md":
				flowtype = MarkDown;
				break;
			case "dossier":
				flowtype = FOLDER;
				break;
			case "bdd":
				flowtype = DATABASE;
				break;
		}
		return flowtype;
	}
}
