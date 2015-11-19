package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		ArrayList<Tuple> temp = new ArrayList<Tuple>();
		
		if (tuplesResult.isEmpty()) {
			Tuple tuple = child.next();
			if(tuple==null){
				return null;
			}
			else{ 
				// add all tuples to temp
				while (tuple != null) {
					temp.add(tuple);
					tuple = child.next();
				}
			}
			
			//compare one y one, find the min one and store in tuplesResult
			tuple = temp.get(0);
			for(int i = 0 ; i <tuple.getAttributeList().size() ; i++){
				if(tuple.getAttributeName(i).equals(orderPredicate)){
					while(!temp.isEmpty()){
						int min = 0;
						for(int j = 0; j < temp.size();j++){
							if((temp.get(j).getAttributeValue(i)).toString().
									compareTo(temp.get(min).getAttributeValue(i).toString())<0)
								min = j;
						}
						tuplesResult.add(temp.get(min));
						temp.remove(min);
					}
				}
			}
			
		}
		Tuple tuple1 = tuplesResult.remove(0);
		return tuple1;
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}