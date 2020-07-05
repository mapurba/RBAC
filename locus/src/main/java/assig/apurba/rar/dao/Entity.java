/* ENTITY.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:21:12 PM
 * Last edited:
 *   7/5/2020, 2:21:14 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   entity
**/



package assig.apurba.rar.dao;



public abstract class Entity {
    
    public Long entityId;
    public abstract Long getEntityId();
    public abstract void setEntityId(Long id);


}