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
		//Delete the lines below and add your code here
		Tuple tuple = this.child.next();
		if (!child.from.equals(whereTablePredicate)) return tuple;
		
		Boolean flag = false;
		while (tuple != null){
			for (Attribute attr: tuple.getAttributeList()){	
				if (attr.attributeName.equals(this.whereAttributePredicate) 
						&& attr.attributeValue.toString().equals(this.whereValuePredicate)){
					flag = true;
					return tuple;
				}
				else if (attr.attributeName.equals(this.whereAttributePredicate) 
						&& !attr.attributeValue.toString().equals(this.whereValuePredicate)){
					flag = true;
					tuple = child.next();
					break;
				}	
			}
			if (!flag){
				return tuple;
			}
		}
		return null;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}



	
}