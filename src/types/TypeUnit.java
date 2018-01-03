
package types;
import delta.Copy;

import typeSystem.TYPE;


import solution.CandidatesList;

public class TypeUnit extends TypeT
{ private static final TypeUnit unit=new TypeUnit(TYPE.UNIT);
  public TypeUnit(TYPE T)
  { if(!T.equals(TYPE.UNIT)) throw new RuntimeException("TypeUnit must be of TYPE.UNIT.");}
  @Override
  public TYPE typeOf(){ return TYPE.UNIT;}        
  public TypeUnit getValue(){ return unit;}       
  @Override
  public boolean equals(Object obj){ return obj instanceof TypeUnit;} 
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("unit");
    return buf.toString();
  }   
 
  @Override
  public double weight(){ return 1.0;}
  @Override
  public CandidatesList refine(TypeT obj)
  { if (obj.typeOf().equals(this.typeOf())){ return new CandidatesList(new Copy(this),new CandidatesList());}
    else{throw new RuntimeException(obj+" is NOT of TypeUnit.");} 
  }        
}

