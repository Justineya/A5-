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
		ArrayList<Tuple> tuples= new ArrayList<Tuple>(); 
		if(tuplesResult.isEmpty()==true){
		int i,j=0;
		while(true){
			Tuple tuple=child.next();
			if(tuple==null)
				break;
			
			tuples.add(tuple);
		}
		for(Tuple tuple1:tuples){
			i=0;
			if(tuplesResult.isEmpty()==true){
				tuplesResult.add(tuple1);
				j++;
				continue;
			}
		 
			    for(Attribute a:tuple1.getAttributeList()){
			    	for(int m=0;m<tuplesResult.get(i).getAttributeList().size();m++){
			    		if(a.getAttributeName().equals(this.orderPredicate) &&tuplesResult.get(i).getAttributeList().get(m).getAttributeName().equals(orderPredicate)){
			    			if(a.getAttributeValue().toString().compareTo(tuplesResult.get(i).getAttributeList().get(m).getAttributeValue().toString())>0){
			    				i++;
			    				m--;
						    	if(i==j){
						    		tuplesResult.add(i, tuple1);
						    		j++;
						    		break;}
			    				continue;
			    			}
			    			tuplesResult.add(i, tuple1);
			    			j++;
			    			i=0;
			    			break;
			    		}
			    	}
			    }
				
			
			}
		}
		try{
		Tuple tuple=tuplesResult.remove(0);
		return tuple;
		
		}
		catch(Exception e ){
			return null;
		}
		

	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}