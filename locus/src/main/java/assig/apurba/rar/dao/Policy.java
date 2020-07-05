/* POLICY.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:21:18 PM
 * Last edited:
 *   7/5/2020, 2:21:19 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   <Todo>
**/

package assig.apurba.rar.dao;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.ExpressionParser;


public class Policy extends Entity{

    private static final Long serialVersionUID = -2977271355914647053L;
    private String name;
    private String description;
    private Expression condition;
    private Long id;
    /*
	 * Boolean SpEL expression. If evaluated to true, then this rule is applied to the request access context.
	 */
	private Expression  target;
	
	/*
	 * Boolean SpEL expression, if evaluated to true, then access granted.
	 */
	// private Expression  condition;


    public Policy(String name, String description,String target,String condition) {
        super();
        this.name=name;
        this.description=description;
        // this.policyId = policyId;
        // this.condition=condition;
        this.setEntityId(this.serialVersionUID);
        this.id=this.serialVersionUID;
    }


    public Long getEntityId() {
        return this.entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expression getTarget() {
        return this.target;
    }

    public void setTarget(String target) {
		ExpressionParser exp = new SpelExpressionParser();

        this.target = exp.parseExpression(target);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
    
}