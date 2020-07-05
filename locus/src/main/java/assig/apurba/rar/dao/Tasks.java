/* TASKS.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:21:45 PM
 * Last edited:
 *   7/5/2020, 2:21:45 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.dao;

public class Tasks {
	private Integer id;
	private String name;
	private String description;
	
	public Tasks() {
		super();
	}
	
	public Tasks(Integer id) {
		super();
		this.id = id;
	}
	
	public Tasks(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", description:" + description + "}";
	}
	
	
}
