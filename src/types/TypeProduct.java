
package types;
import delta.DeltaProduct;
import solution.CandidatesList;
import typeSystem.TYPE;
import utility.ListOfLabelandTypeTs;


public class TypeProduct extends TypeT
{ private final TYPE T;
  private final ListOfLabelandTypeTs values;
  public TypeProduct(TYPE T, ListOfLabelandTypeTs values) 
  { if(!T.isPRODUCT()||!T.getMembers().hasSameTYPE(values))
    { throw new RuntimeException("This TYPE T="+T+" does not match the value TYPEs="+values);} 
    this.T=T; this.values=values;
  }
  @Override
  public TYPE typeOf(){ return this.T;}   
  public ListOfLabelandTypeTs getValues(){ return this.values;}        
  
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeProduct)
    { TypeProduct that=(TypeProduct)obj;
      return this.T.equals(that.T)&&this.values.equals(that.values);
    }
    else { throw new RuntimeException("This obj="+obj+" is not of TypeProduct");}
  }
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder();
    buf.append("("); buf.append(this.values);buf.append(")");
    return buf.toString();
  }    
  
  @Override
  public double weight(){ return this.values.weight();}
  @Override
  public CandidatesList refine(TypeT obj)
  { if (obj.typeOf().equals(this.typeOf()))
    { //System.out.println("Test when compare recursive type, really run here????????");
      TypeProduct that=(TypeProduct)obj;
      return new CandidatesList(new DeltaProduct(this.values.getUnknownDeltas(that.values)),new CandidatesList());
    }
    else{ throw new RuntimeException(obj+" is NOT of TypeProduct");}  
  }                
}
