package types;

import delta.Copy;
import delta.Change;
import typeSystem.TYPE;
import solution.CandidatesList;

public class TypeString extends TypeT
{ private final String str;
  public TypeString(TYPE T, String str)
  { if(!T.equals(TYPE.STRING)) throw new RuntimeException("TypeString must be of TYPE.STRING.");
    this.str=str;
  }  
  @Override
  public TYPE typeOf(){ return TYPE.STRING;}         
  public String getValue(){ return this.str;}         
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeString)
    { TypeString that=(TypeString)obj;
      return this.str.equals(that.str);
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeString");} 
  } 
  
  public String toString(){return this.str;}   
  public double weight(){ return 0.0;}
  @Override
  public CandidatesList refine(TypeT obj)
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeString that=(TypeString)obj;
      if(this.equals(that)){ return new CandidatesList(new Copy(this),new CandidatesList());}    
      else{ return new CandidatesList(new Change(this,that),new CandidatesList());}  
    }
    else{ throw new RuntimeException(obj+"is NOT of TypeString"); }  
  }        
}