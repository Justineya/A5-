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
		try{
			String attributeline,dataTypeline,tupleline;
		  	if(getAttribute==false){
				attributeline = br.readLine();
				dataTypeline = br.readLine();
			    tupleline = br.readLine();
				tuple=new Tuple(attributeline,dataTypeline,tupleline);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute = true;
				return tuple;
		  	}
		  	else{
				int i=0;
			    tupleline = br.readLine();
				if(tupleline==null)
					return null;
				String[] col=tupleline.split(",");
				ArrayList<Attribute> alist= new ArrayList<Attribute>();
				for(Attribute a :tuple.getAttributeList()){
					Attribute newa=new Attribute();
					newa.setAttributeName(a.getAttributeName());
					newa.attributeType=a.getAttributeType();
					newa.setAttributeValue(a.getAttributeType(),col[i]);
					i++;
					alist.add(newa);
				}
				tuple=new Tuple(alist);
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
	
	