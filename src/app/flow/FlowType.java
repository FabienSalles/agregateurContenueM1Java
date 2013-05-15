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
}
