
package utility;

import delta.Unknown;
import solution.CandidatesList;
import typeSystem.TYPE;
import types.TypeT;

public class ListOfLabelandTypeTs extends TypeT
{ private final LabelandTypeT head;
  private final ListOfLabelandTypeTs rest;
  public ListOfLabelandTypeTs(){ this.head=null; this.rest=null;}// constructor for empty ListOfLabelandTypeTs
  public ListOfLabelandTypeTs(LabelandTypeT head, ListOfLabelandTypeTs rest){ this.head=head; this.rest=rest;}// constructor for non-empty ListOfLabelandTypeTs
  
  public LabelandTypeT head()
  { if(!this.isEmptyListOfLabelandTypeTs()) return this.head;
    else throw new RuntimeException("There is no head element in an empty ListOfLabelandTypeTs."); 
  }
  public ListOfLabelandTypeTs rest()
  { if(!this.isEmptyListOfLabelandTypeTs()) return this.rest;
    else return new ListOfLabelandTypeTs();
  }
  public boolean isEmptyListOfLabelandTypeTs(){ return this.head==null&&this.rest==null;}
    
 public int size()
 { if(!this.isEmptyListOfLabelandTypeTs()) return 1+this.rest.size();
   else return 0;
 }        
 public boolean hasLabelandTypeT(LabelandTypeT newLabelandTypeT)
 { if(!this.isEmptyListOfLabelandTypeTs())
   { if(newLabelandTypeT.equals(this.head)){ return true;}
     else{ return this.rest.hasLabelandTypeT(newLabelandTypeT);}  
   }
   else return false;
 }        
  
 public ListOfLabelandTypeTs ins(LabelandTypeT newLabelandTypeT){ return new ListOfLabelandTypeTs(newLabelandTypeT, this);} 
 public ListOfLabelandTypeTs append(LabelandTypeT newLabelandTypeT)
 { if(!this.isEmptyListOfLabelandTypeTs())
   { return new ListOfLabelandTypeTs(this.head, this.rest.append(newLabelandTypeT));} 
   else return new ListOfLabelandTypeTs(newLabelandTypeT, this);
 }

 @Override
 public boolean equals(Object obj)
 { if(obj instanceof ListOfLabelandTypeTs)
   { ListOfLabelandTypeTs that=(ListOfLabelandTypeTs)obj;
     if(!this.isEmptyListOfLabelandTypeTs()&&!that.isEmptyListOfLabelandTypeTs())
     { return this.head.equals(that.head)&&this.rest.equals(that.rest);}
       else { return this.isEmptyListOfLabelandTypeTs()&&that.isEmptyListOfLabelandTypeTs();}
     }
     else{ throw new RuntimeException("This "+obj+" is not instance of ListOfLabelandTypeTs.");}
 }        
 @Override
 public String toString()
 { StringBuffer buf=new StringBuffer();
   if(!isEmptyListOfLabelandTypeTs()){ dump(buf);}
   return buf.toString();
 }
 private void dump(StringBuffer buf)
 { buf.append(this.head);
   if (!this.rest.isEmptyListOfLabelandTypeTs()){ buf.append(","); this.rest.dump(buf);}  
 } 

//@Override
 public double weight()
 { if(!this.isEmptyListOfLabelandTypeTs()) return this.head.getTypeT().weight()+this.rest.weight();
   else return 0.0;
 }   
 
 //When compare two values of a specific PRODUCT TYPE, in the this.refine(that) method
 //The first step is map each value of TypeT in the original value to the corresponding value in the target value 
 //using Unknown class.
 public ListOfLabelandDeltas getUnknownDeltas(ListOfLabelandTypeTs that)
 { if(this.size()!=that.size()){ throw new RuntimeException("Two product types, the size of them must be the same.");}
   else if(!this.isEmptyListOfLabelandTypeTs()&&!that.isEmptyListOfLabelandTypeTs())
   { return new ListOfLabelandDeltas(new LabelandDelta(this.head.getLabel(), new Unknown(this.head.getTypeT(), that.head.getTypeT())), this.rest.getUnknownDeltas(that.rest));}
   else return new ListOfLabelandDeltas();
 }                

 @Override
 public TYPE typeOf() 
 { throw new UnsupportedOperationException("Not supported yet."); 
 }
@Override
 public CandidatesList refine(TypeT obj) 
 { throw new UnsupportedOperationException("Not supported yet."); 
 }
}
