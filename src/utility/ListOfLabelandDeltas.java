//Used in product types.
package utility;

import similarity.Sim;

public class ListOfLabelandDeltas 
{ private final LabelandDelta head;
  private final ListOfLabelandDeltas rest;
  private final double weight;
  private final double increment;
  private final double decrement;
  public ListOfLabelandDeltas()
  { this.head=null; this.rest=null; this.weight=0.0; this.increment=0.0; this.decrement=0.0;}//Constructor for empty ListOfDeltas.
  public ListOfLabelandDeltas(LabelandDelta head, ListOfLabelandDeltas rest)
  { this.head=head; 
    this.rest=rest;
    this.weight=this.head.getDelta().weight()+this.rest.weight();
    this.increment=this.head.getDelta().increase()+this.rest.increase();
    this.decrement=this.head.getDelta().decrease()+this.rest.decrease();
  }//Constructor for non-empty ListOfLabelandDeltas.        
  
  
  public double weight(){ return this.weight;} 
  public double increase(){ return this.increment;}
  public double decrease(){ return this.decrement;}
  
  public boolean isEmptyListOfLabelandDeltas(){ return this.head==null&&this.rest==null;}
  
  public LabelandDelta head()
  { if(!this.isEmptyListOfLabelandDeltas()) return this.head;
    else throw new RuntimeException("Empty ListOfLabelandDeltas has no head element.");
  }       
  public ListOfLabelandDeltas rest()
  { if(!this.isEmptyListOfLabelandDeltas()) return this.rest;
    else return new ListOfLabelandDeltas();
  }        
  
  
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    if(!isEmptyListOfLabelandDeltas()) dump(buf);
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if (!rest.isEmptyListOfLabelandDeltas()){ buf.append(", "); this.rest.dump(buf);}
  }  
    
 
  public Sim sim()
  { if(!this.isEmptyListOfLabelandDeltas())// non-empty listOfLabelandDeltas the weight is not 0
    { double lwb=increase()/weight();
      double upb=1-decrease()/weight();
      return new Sim(lwb,upb);
    }
    else return new Sim(0.0,0.0);
  }        
  //insert a LabelandDelta in front of this ListOfLabelandDelta      
  public ListOfLabelandDeltas insert(LabelandDelta d){ return new ListOfLabelandDeltas(d,this);}
  
  //concatenate two listOfLabelandDeltas together.
  public ListOfLabelandDeltas concat(ListOfLabelandDeltas deltas)
  { if(deltas.isEmptyListOfLabelandDeltas()){ return this;}
    else return this.insert(deltas.head).concat(deltas.rest);
  }        
  
  public ListOfLabelandDeltas refine()
  { if(!this.isEmptyListOfLabelandDeltas()){return new ListOfLabelandDeltas(new LabelandDelta(this.head.getLabel(),this.head.getDelta().refine().solutions().fstDelta()), this.rest.refine());}
    else return new ListOfLabelandDeltas();
  }        
    
}
