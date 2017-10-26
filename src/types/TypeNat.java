
package types;

import delta.Id;
import delta.Change;

import typeSystem.TYPE;

import solution.CandidatesList;

public class TypeNat extends TypeT
{ private final int i;
  public TypeNat(TYPE T,int i) 
  { if(!T.equals(TYPE.NAT)) throw new RuntimeException("TypeNat must be of TYPE.NAT.");
    this.i=i;
  }
  @Override
  public TYPE typeOf(){ return TYPE.NAT;}        
  public int getValue(){ return this.i;}        
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeNat)
    { TypeNat that=(TypeNat)obj;
      return this.i==that.i;
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeNat");} 
  } 
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.i);
    return buf.toString();
  }
  
  @Override
  public double weight(){ return 1.0;}
  @Override
  public CandidatesList refine(TypeT obj)
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeNat that=(TypeNat)obj;
      if(this.equals(that)){ return new CandidatesList(new Id(this),new CandidatesList());}    
      else{ return new CandidatesList(new Change(this,that),new CandidatesList());}  
    }
    else{ throw new RuntimeException(obj+"is NOT of TypeNat"); }  
  }        
}
