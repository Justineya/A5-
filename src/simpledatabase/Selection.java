package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		boolean selected=false;
		attributeList = new ArrayList<Attribute>();
		Tuple tuple= child.next();
		if(tuple==null)
			return null;
		if(!child.from.equals(whereTablePredicate))
			return tuple;
		else{
			while(!selected){
			for (Attribute a:tuple.getAttributeList()){
				if(a.getAttributeName().equals(whereAttributePredicate) && !a.getAttributeValue().equals(whereValuePredicate))
					tuple=child.next();
				else if(a.getAttributeName().equals(whereAttributePredicate) && a.getAttributeValue().equals(whereValuePredicate)){
					selected=true;
				}
				if(tuple==null)
					return null;
			}
		}
			}
		
		return tuple;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}