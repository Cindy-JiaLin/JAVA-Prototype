//Used in product types.
package utility;

import similarity.Sim;

public class ListOfLabelandDeltas 
{ private final LabelandDelta head;
  private final ListOfLabelandDeltas rest;
  public ListOfLabelandDeltas(){ this.head=null; this.rest=null;}//Constructor for empty ListOfDeltas.
  public ListOfLabelandDeltas(LabelandDelta head, ListOfLabelandDeltas rest)
  { this.head=head; this.rest=rest;}//Constructor for non-empty ListOfLabelandDeltas.        
  
  public boolean isEmptyListOfLabelandDeltas(){ return this.head==null&&this.rest==null;}
  
  public LabelandDelta head()
  { if(!this.isEmptyListOfLabelandDeltas()){ return this.head;}
    else if(this.isEmptyListOfLabelandDeltas()){ throw new RuntimeException("Empty ListOfLabelandDeltas has no head element.");}
    else { throw new RuntimeException("Illegal constructor usage in ListOfLabelandDeltas head() method.");}
  }       
  public ListOfLabelandDeltas rest()
  { if(!this.isEmptyListOfLabelandDeltas()){ return this.rest;}
    else if(this.isEmptyListOfLabelandDeltas()) { return new ListOfLabelandDeltas();}
    else{ throw new RuntimeException("Illegal constructor usage in ListOfLabelandDeltas rest() method.");}
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
  public double weight()
  { if(!this.isEmptyListOfLabelandDeltas()){ return this.head.getDelta().weight()+this.rest.weight();}
    else if(this.isEmptyListOfLabelandDeltas()){ return 0.0;}
    else { throw new RuntimeException("Illegal constructor usage in ListOfLabelandDeltas weight() method.");}
  }   
 
  public Sim sim()
  { if(!this.isEmptyListOfLabelandDeltas()) 
    { double lwb=(this.head.getDelta().weight()*this.head.getDelta().sim().lwb()+this.rest.weight()*this.rest.sim().lwb())/this.weight();
      double upb=(this.head.getDelta().weight()*this.head.getDelta().sim().upb()+this.rest.weight()*this.rest.sim().upb())/this.weight();
      return new Sim(lwb,upb);
    }
    else if(this.isEmptyListOfLabelandDeltas()){ return new Sim(0.0,0.0);}
    else { throw new RuntimeException("Illegal constructor usage in ListOfLabelandDeltas sim() method.");}
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
    else if(this.isEmptyListOfLabelandDeltas()){ return new ListOfLabelandDeltas();}
    else { throw new RuntimeException("Illegal constructor usage in ListOfDeltas refine() method.");}
  }        
    
}
