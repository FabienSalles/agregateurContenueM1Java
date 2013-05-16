package app.flow;

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
	private static FlowTable instance;
	
	private FlowTable()
	{
		this.conn  = Query.getInstance();
	}
	
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
			e.printStackTrace();
		}
		
        
		return flows;
	}
	
	private void addFlow(ResultSet rs)
	{
		try {
			//System.out.println(FlowType.valueOf(rs.getString("type").trim().toLowerCase()));
			switch(FlowType.getName(rs.getString("type").trim().toLowerCase()))
			{
				case HTML:
					flows.add(new HTMLFlow(new URL(rs.getString("path"))));
					break;
				case RSS:
					flows.add(new RSSFlow(new URL(rs.getString("path"))));
					break;
				case FOLDER:
					flows.add(new FolderFlow(rs.getString("path")));
					break;
				default:
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


