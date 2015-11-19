package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here

		Tuple tuple = child.next();
		if (tuple == null)
			return tuple;

		for (Attribute attr:tuple.getAttributeList()){
			if (this.attributePredicate.equals(attr.getAttributeName())){
				if (newAttributeList.size() == 0)
					newAttributeList.add(attr);
				else
					newAttributeList.set(0, attr);
			
				Tuple tuple1 = new Tuple(newAttributeList);
				return tuple1;
			}
		}
		
		return null;
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

	
	
}