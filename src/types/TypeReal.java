package types;

import delta.Change;
import delta.Id;

import typeSystem.TYPE;

import solution.CandidatesList;

public class TypeReal extends TypeT
{ private final TYPE T;
  private final double real;
  public TypeReal(TYPE T, double real) 
  { if(!T.isREAL()) throw new RuntimeException("TypeReal must be of TYPE.REAL.");
    this.T=T; this.real=real;
  }
  @Override
  public TYPE typeOf(){ return this.T;}        
  public double getValue(){ return this.real;}   

  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeReal)
    { TypeReal that=(TypeReal)obj;
      return (this.T.equals(that.T)&&this.real==that.real);
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeReal");} 
  } 
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.real);buf.append("(");buf.append(this.T.getAcc()); buf.append(")");
    return buf.toString();
  }
  
  @Override
  public double weight(){ return 1.0;}
  @Override
  public CandidatesList refine(TypeT obj)
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeReal that=(TypeReal)obj;
      if(this.equals(that)){ return new CandidatesList(new Id(this),new CandidatesList());}    
      else { return new CandidatesList(new Change(this, that), new CandidatesList());}  
    }
    else{ throw new RuntimeException(obj+"is NOT of TypeNat"); }  
  }        
}
