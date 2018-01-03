
package types;
import delta.Copy;
import delta.Change;

import typeSystem.TYPE;

import solution.CandidatesList;

public class TypeBool extends TypeT
{ private final boolean t;
  public TypeBool(TYPE T, boolean t)
  { if(!T.equals(TYPE.BOOL)) throw new RuntimeException("TypeBool must be of TYPE.BOOL");
    this.t=t;
  }
  
  @Override
  public TYPE typeOf(){ return TYPE.BOOL;}
  public boolean getValue(){ return this.t;}        
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeBool)
    { TypeBool that=(TypeBool)obj;
      return this.t==that.t;
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeBool");} 
  } 
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.t);
    return buf.toString();
  }    
  
  @Override
  public double weight(){ return 1.0;}
  
  @Override
  public CandidatesList refine(TypeT obj)
  { if (obj.typeOf().equals(this.typeOf()))
    { TypeBool that=(TypeBool)obj;
      if(this.equals(that)){ return new CandidatesList(new Copy(this),new CandidatesList());}    
      else{ return new CandidatesList(new Change(this,that),new CandidatesList());}
    }
    else{throw new RuntimeException(obj+" is NOT of TypeBool.");}    
  }        
}


