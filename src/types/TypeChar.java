
package types;
import delta.Id;
import delta.Change;

import typeSystem.TYPE;

import solution.CandidatesList;
public class TypeChar extends TypeT
{ private final char c;
  public TypeChar(TYPE T, char c)
  { if(!T.equals(TYPE.CHAR)) throw new RuntimeException("TypeChar must be of TYPE.CHAR.");
    this.c=c;
  }  
  @Override
  public TYPE typeOf(){ return TYPE.CHAR;}         
  public char getValue(){ return this.c;}         
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeChar)
    { TypeChar that=(TypeChar)obj;
      return this.c==that.c;
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeChar");} 
  } 
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append(this.c);
    return buf.toString();
  }   
  
  @Override
  public double weight(){ return 1.0;}
  @Override
  public CandidatesList refine(TypeT obj)
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeChar that=(TypeChar)obj;
      if(this.equals(that)){ return new CandidatesList(new Id(this),new CandidatesList());}    
      else{ return new CandidatesList(new Change(this,that),new CandidatesList());}  
    }
    else{ throw new RuntimeException(obj+"is NOT of TypeChar"); }  
  }        
}
