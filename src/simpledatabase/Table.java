package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		
		try {
			if (!getAttribute){
				String attrLine = br.readLine();
				String dttpLine = br.readLine();
				String tuplLine = br.readLine();
				if (tuplLine == null)
					return null;
				getAttribute = true;
				tuple = new Tuple (attrLine, dttpLine, tuplLine);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				return tuple;
			}
			else{
				ArrayList<Attribute> attrList = new ArrayList<Attribute>();
				String tuplLine = br.readLine();
				if (tuplLine == null)
					return null;
				String[] newTuplLine = tuplLine.split(",");
				int i = 0;
				for (Attribute attr:tuple.getAttributeList()){
					Attribute newAttr = new Attribute();
					newAttr.setAttributeName(attr.getAttributeName());
					newAttr.attributeType = attr.getAttributeType();
					newAttr.setAttributeValue(attr.getAttributeType(), newTuplLine[i]);
					attrList.add(newAttr);
					i++;
				}
				tuple = new Tuple(attrList);
				return tuple;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tuple;
		
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}