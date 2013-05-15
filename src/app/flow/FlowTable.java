package app.flow;

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
	private static FlowTable instance;
	
	private FlowTable()
	{
		this.conn  = Query.getInstance();
	}
	
	public Set<Flow> getFlow() throws SQLException
	{
		Statement stat = this.conn.createStatement();
		this.flows = new LinkedHashSet();
        try {
        	ResultSet rs = stat.executeQuery("SELECT * FROM Flow;");
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
	
	private void addFlow(ResultSet rs) throws SQLException
	{
		switch(rs.getString("type"))
		{
			case "rss":
			case "html":
			case "htm":
			case "php":
				flows.add(new URLFlow(rs.getString("path"), rs.getString("type")));
				break;
			case "folder":
				flows.add(new FolderFlow(rs.getString("path")));
			default:
				break;
		}
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


