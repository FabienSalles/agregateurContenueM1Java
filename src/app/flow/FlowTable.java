package app.flow;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import app.database.Query;

public class FlowTable
{
	private Set<Flow> flows;
	private Connection conn;
	
	/**
	 * Instance for the singleton
	 */
	private static FlowTable instance;
	
	private FlowTable()
	{
		this.conn  = Query.getInstance();
	}
	
	/**
	 * Get all objects
	 * @return LinkedHasSet<flow>
	 */
	public Set<Flow> getFlow()
	{
		this.flows = new LinkedHashSet();
        try {
        	Statement stat = this.conn.createStatement();
        	ResultSet rs = stat.executeQuery("SELECT rowid, type, path FROM Flow;");
			while(rs.next())
	    	{
	    		this.addFlow(rs);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return flows;
	}
	
	/**
	 * Get flow by type
	 * @param type
	 * @return LinkedHasSet<flow>
	 */
	public Set<Flow> getFlowByType(String type)
	{
		flows = new LinkedHashSet();
		PreparedStatement prepare;
		try {
			prepare = Query
				.getInstance()
				.prepareStatement("SELECT * FROM flow WHERE type = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
			;
			prepare.setString(1, type);
			ResultSet rs = prepare.executeQuery();
        
			// add flow in table
        	while(rs.next())
        	{
        		this.addFlow(rs);
        	}
        	// add sqlite flow
        	flows.add(new SQLiteFlow());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        
		return flows;
	}
	
	/**
	 * add flow in the list of article
	 * @param rs
	 */
	private void addFlow(ResultSet rs)
	{
		try {
			String typeofPath = this.getTypeOfPath(rs.getString("path"));
			Flow flow = null;
			switch(FlowType.getName(rs.getString("type").trim().toLowerCase()))
			{
				case HTML:
					if (typeofPath.equals("url"))
					{
						flow = new HTMLFlow(rs.getInt("rowid"), new URL(rs.getString("path")));
					}
					else
					{
						flow = new HTMLFlow(rs.getInt("rowid"), new File(rs.getString("path")));
					}
					break;
				case RSS:
					if (typeofPath.equals("url"))
					{
						flow = new RSSFlow(rs.getInt("rowid"), new URL(rs.getString("path")));
					}
					else
					{
						flow = new RSSFlow(rs.getInt("rowid"), new File(rs.getString("path")));
					}
					break;
				case FOLDER:
					flow = new FolderFlow(rs.getInt("rowid"), rs.getString("path"));
					break;
				default:
					break;
			}
			flows.add(flow);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public String getTypeOfPath(String path)
	{
		if (path.startsWith("http"))
		{
			return "url";
		}
		return "file";
	}
	
	/**
    * Méthode qui va nous retourner notre instance
    * et la créer si elle n'existe pas...
    * @return FlowTable flowtable
	 * @throws  
    */
    public static FlowTable getInstance()
    {
        if (instance == null) {
        	instance = new FlowTable();
        }
        return instance;    
    }
}


