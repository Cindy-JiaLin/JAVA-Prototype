
package solution;

public class ListOfStepList 
{ private final StepList head;
  private final ListOfStepList rest;
  public ListOfStepList(){ this.head=null; this.rest=null;}//Constructor for empty list.
  public ListOfStepList(StepList head, ListOfStepList rest){ this.head=head; this.rest=rest;}//Constructor for non-empty list.
  
  public boolean isEmptyListOfStepList(){ return this.head==null&&this.rest==null;}
  
  public StepList head()
  { if(!this.isEmptyListOfStepList()) return this.head;
    else throw new RuntimeException("Empty list has no head element.");
  }       
  public ListOfStepList rest()
  { if(!this.isEmptyListOfStepList()) return this.rest;
    else return new ListOfStepList();
  }      
  
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("[");
    if(!isEmptyListOfStepList()) dump(buf);
    buf.append("]");
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if (!rest.isEmptyListOfStepList()){ buf.append(", "); this.rest.dump(buf);}
  } 
  //insert a StepList in front of this list of StepList      
  public ListOfStepList insert(StepList stepList){ return new ListOfStepList(stepList,this);}
}
