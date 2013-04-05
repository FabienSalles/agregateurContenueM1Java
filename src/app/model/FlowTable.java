package app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import app.core.Query;

public class FlowTable
{
	
	public static Set<Flow> getFlow() throws SQLException
	{
		Set<Flow> flows = new LinkedHashSet<Flow>();

		Connection conn = Query.getInstance();
		Statement stat = conn.createStatement();
        	ResultSet rs = stat.executeQuery("SELECT * FROM Flows;");
        
        	while(rs.next()) {
            		Flow f = new Flow();
        	
        		f.setPath(rs.getString("path"));
        		f.setType(rs.getString("type"));
            
            		flows.add(f);
	        }
        
		return flows;
	}
	
	public static Set<Flow> getFlowByType(String type) throws SQLException
	{
		Set<Flow> flows = new LinkedHashSet<Flow>();

		PreparedStatement prepare = Query
				.getInstance()
				.prepareStatement("SELECT * FROM Flows WHERE type = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		prepare.setString(1, type);
        	ResultSet rs = prepare.executeQuery();
        
        	while(rs.next()) {
            		Flow f = new Flow();
        	
        		f.setPath(rs.getString("path"));
        		f.setType(rs.getString("type"));
            
            		flows.add(f);
        	}
        
		return flows;
	}
}
