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
  public double weight()
  { if(!this.isEmptyListOfLabelandDeltas()) return this.head.getDelta().weight()+this.rest.weight();
    else return 0.0;
  }   
 
  public Sim sim()
  { if(!this.isEmptyListOfLabelandDeltas()) 
    { double headWeight=this.head.getDelta().weight();
      double restWeight=this.rest.weight();
      double weight=headWeight+restWeight;
      Sim headSim=this.head.getDelta().sim();
      Sim restSim=this.rest.sim();
      double lwb=(headWeight*headSim.lwb()+restWeight*restSim.lwb())/weight;
      double upb=(headWeight*headSim.upb()+restWeight*restSim.upb())/weight;
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
