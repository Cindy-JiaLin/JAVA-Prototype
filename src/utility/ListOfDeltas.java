
package utility;

import delta.Delta;
import similarity.Sim;

public class ListOfDeltas 
{ private final Delta head;
  private final ListOfDeltas rest;
  public ListOfDeltas(){ this.head=null; this.rest=null;}//Constructor for empty ListOfDeltas.
  public ListOfDeltas(Delta head, ListOfDeltas rest){ this.head=head; this.rest=rest;}//Constructor for non-empty ListOfDeltas.        
  
  public Delta head()
  { if(!this.isEmptyListOfDeltas()){ return this.head;}
    else if(this.isEmptyListOfDeltas()){ throw new RuntimeException("Empty stepList has no head element.");}
    else { throw new RuntimeException("Illegal constructor usage in ListOfDeltas head() method.");}
  }       
  public ListOfDeltas rest()
  { if(!this.isEmptyListOfDeltas()){ return this.rest;}
    else if(this.isEmptyListOfDeltas()) { return new ListOfDeltas();}
    else{ throw new RuntimeException("Illegal constructor usage in ListOfDeltas rest() method.");}
  }        
  public boolean isEmptyListOfDeltas(){ return this.head==null&&this.rest==null;}
  
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    if(!isEmptyListOfDeltas()) dump(buf);
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if (!rest.isEmptyListOfDeltas()){ buf.append(", "); this.rest.dump(buf);}
  }  
  public double weight()
  { if(!this.isEmptyListOfDeltas()){ return this.head.weight()+this.rest.weight();}
    else if(this.isEmptyListOfDeltas()){ return 0.0;}
    else { throw new RuntimeException("Illegal constructor usage in ListOfDeltas weight() method.");}
  }   
 
  public Sim sim()
  { if(!this.isEmptyListOfDeltas()) 
    { double whole_weight=this.weight();
      double lwb=(this.head.weight()*this.head.sim().lwb()+this.rest.weight()*this.rest.sim().lwb())/whole_weight;
      double upb=(this.head.weight()*this.head.sim().upb()+this.rest.weight()*this.rest.sim().upb())/whole_weight;
      return new Sim(lwb,upb);
    }
    else if(this.isEmptyListOfDeltas()){ return new Sim(0.0,0.0);}
    else { throw new RuntimeException("Illegal constructor usage in ListOfDeltas sim() method.");}
  }        
  //insert a Delta in front of this list of delta      
  public ListOfDeltas insert(Delta d){ return new ListOfDeltas(d,this);}
  
  //concatenate two listOfDeltas together.
  public ListOfDeltas concat(ListOfDeltas deltas)
  { if(deltas.isEmptyListOfDeltas()){ return this;}
    else return this.insert(deltas.head).concat(deltas.rest);
  }        
  
  public ListOfDeltas refine()
  { if(!this.isEmptyListOfDeltas()){return new ListOfDeltas(this.head.refine().solutions().fstDelta(), this.rest.refine());}
    else if(this.isEmptyListOfDeltas()){ return new ListOfDeltas();}
    else { throw new RuntimeException("Illegal constructor usage in ListOfDeltas refine() method.");}
  }        
}
