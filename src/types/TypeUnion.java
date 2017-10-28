
package types;

import delta.DeltaUnion;
import delta.Unknown;
import delta.Change;

import typeSystem.TYPE;

import solution.CandidatesList;


public class TypeUnion extends TypeT
{ private final TYPE T;
  private final String label;
  private final TypeT t;
  public TypeUnion(TYPE T, String label, TypeT t)
  { if(!T.isUNION()||!T.getMembers().hasLabel(label)||!T.getMembers().getItsTYPE(label).equals(t.typeOf()))
    { throw new RuntimeException("This value does not match this TYPE T.");}
    this.T=T; this.label=label; this.t=t;
  }        
    
  @Override
  public TYPE typeOf() { return this.T;}
  public TypeT getValue(){ return this.t;}
  //public String getLabel(){ return this.label;}
  public boolean isNil(){ return this.label=="nil";}
  
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeUnion)
    { TypeUnion that=(TypeUnion)obj;
      return this.T.equals(that.T)&&this.label.equals(that.label)&&this.t.equals(that.t);
    }
    else{ throw new RuntimeException("This obj="+obj+" is not of TypeUnion");}  
  }          
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.label);
    buf.append(".");
    buf.append(this.t);
    return buf.toString();
  }          
    
  @Override
  public double weight() { return this.t.weight();} 
  @Override
  public CandidatesList refine(TypeT obj) 
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeUnion that=(TypeUnion)obj;
      if(!this.label.equals(that.label))
      { return new CandidatesList(new DeltaUnion("dismatch: ("+this.label+"-to-"+that.label+")", new Change(this,that)),new CandidatesList());}  
      else{ return new CandidatesList(new DeltaUnion(this.label, new Unknown(this.t, that.t)),new CandidatesList());}  
    }
    else{ throw new RuntimeException("This obj="+obj+" is NOT the same of union type");}  
  }
}
