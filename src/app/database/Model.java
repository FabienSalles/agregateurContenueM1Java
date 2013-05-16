package app.database;

/**
 * Interface for insert update and delete in database
 * @author fsalles
 *
 */
public interface Model {
	public void save();
	public void update();
	public void delete();
}
