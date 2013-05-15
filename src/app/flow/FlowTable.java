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
	Set<Flow> flows;
	Connection conn;
	Statement stat;
	ResultSet rs;
	PreparedStatement prepare;
	static FlowTable instance;
	
	private FlowTable() throws SQLException
	{
		conn  = Query.getInstance();
		stat = conn.createStatement();
	}
	
	public Set<Flow> getFlow()
	{
		flows = new LinkedHashSet();
        try {
			rs = stat.executeQuery("SELECT * FROM Flow;");
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
		try {
			prepare = Query
				.getInstance()
				.prepareStatement("SELECT * FROM flow WHERE type = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
			;
			prepare.setString(1, type);
        	rs = prepare.executeQuery();
        
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
    * @return Connection connect
	 * @throws  
    */
    public static FlowTable getInstance()
    {
        if (instance == null) {
             try
             {
				instance = new FlowTable();
             }
             catch (SQLException e)
             {
				e.printStackTrace();
			}
        }
        return instance;    
    }
}


