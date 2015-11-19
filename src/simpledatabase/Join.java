package simpledatabase;
import java.util.ArrayList;



public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		
		//Delete the lines below and add your code here
		
		
		while (true){
			Tuple ltuple = this.leftChild.next();
			if (ltuple == null) break;
			tuples1.add(ltuple);
		}
	
		Tuple rtuple = this.rightChild.next();
		if (rtuple == null){	
			return null;		
		}
		
		for (Tuple ltuple1:tuples1){	
			for (Attribute lattr:ltuple1.getAttributeList()){
				for (Attribute rattr: rtuple.getAttributeList()){
					if (lattr.getAttributeName().equals(rattr.getAttributeName()) && 
							lattr.getAttributeValue().equals(rattr.getAttributeValue())){
						for (Attribute a:ltuple1.getAttributeList())
							newAttributeList.add(a);

						for (Attribute a: rtuple.getAttributeList()){
							if (!a.getAttributeName().equals(lattr.getAttributeName()))
								newAttributeList.add(a);
						}
					}						
				}
			}
		}
		
		
		Tuple tuple1 = new Tuple(newAttributeList);
		newAttributeList = new ArrayList<Attribute>();
		return tuple1;
	
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}