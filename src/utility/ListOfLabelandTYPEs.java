
package utility;

import typeSystem.TYPE;

public class ListOfLabelandTYPEs
{ private LabelandTYPE head;
  private ListOfLabelandTYPEs rest;
  public ListOfLabelandTYPEs(){ this.head=null; this.rest=null;}
  public ListOfLabelandTYPEs(LabelandTYPE head, ListOfLabelandTYPEs rest){ this.head=head; this.rest=rest;}
  public boolean isEmptyListOfLabelandTYPEs(){ return this.head==null&&this.rest==null;}

  public LabelandTYPE head()
  { if(!this.isEmptyListOfLabelandTYPEs()) return this.head;
    else throw new RuntimeException("There is no head element in an empty ListOfLabelandTYPEs."); 
  }

  public ListOfLabelandTYPEs rest()
  { if(!this.isEmptyListOfLabelandTYPEs()) return this.rest;
    else return new ListOfLabelandTYPEs();
  }

  public int size()
  { if(!this.isEmptyListOfLabelandTYPEs()) return 1+this.rest.size();
    else return 0;
  }  
  public boolean hasLabelandTYPE(LabelandTYPE lnt)
  { if(!this.isEmptyListOfLabelandTYPEs())
    { if(lnt.getLabel().equals(this.head.getLabel())){ return true;}
      else{ return this.rest.hasLabelandTYPE(lnt);}  
    }
    else return false;
  }    
  public ListOfLabelandTYPEs ins(LabelandTYPE lnt)
  { if(!hasLabelandTYPE(lnt)){return new ListOfLabelandTYPEs(lnt, this);} 
    else { throw new RuntimeException("Instances of LabelandTYPE with the same label cannot be inserted");}
  }
  public ListOfLabelandTYPEs append(LabelandTYPE lnt)
  { if(!this.isEmptyListOfLabelandTYPEs())
    { if(!hasLabelandTYPE(lnt)){ return new ListOfLabelandTYPEs(this.head, this.rest.append(lnt));} 
      else { throw new RuntimeException("Instances of LabelandTYPE with the same label cannot be appended");}
    }
    else return new ListOfLabelandTYPEs(lnt, this);
  }

  @Override
  public boolean equals(Object obj)
  { if(obj instanceof ListOfLabelandTYPEs)
    { ListOfLabelandTYPEs that=(ListOfLabelandTYPEs)obj;
      if(!this.isEmptyListOfLabelandTYPEs()&&!that.isEmptyListOfLabelandTYPEs())
      { return this.head.equals(that.head)&&this.rest.equals(that.rest);}
      else { return this.isEmptyListOfLabelandTYPEs()&&that.isEmptyListOfLabelandTYPEs();}
    }
    else{ throw new RuntimeException("This "+obj+" is not instance of ListOfLabelandTYPEs.");}
  }     
 
  public String toString(String separator)
  { StringBuffer buf=new StringBuffer();
    if(!isEmptyListOfLabelandTYPEs()){ dump(buf, separator);}
    return buf.toString();
  }
  private void dump(StringBuffer buf, String separator)
  { buf.append(this.head);
    if (!this.rest.isEmptyListOfLabelandTYPEs()){ buf.append(separator); this.rest.dump(buf,separator);}  
  } 
  
  //Judge any TYPE in this ListOfLabelandTYPEs contains the varName
    public boolean contains(String varName)
    { if(this.isEmptyListOfLabelandTYPEs()){ return false;}
      else { return this.head.getTYPE().contains(varName)||this.rest.contains(varName);}
    } 
    //Unify each origVarName of each TYPE in this ListOfLabelandTYPEs by the targVarName
    public ListOfLabelandTYPEs unifyVarName(String origVarName, String targVarName)
    { if(this.isEmptyListOfLabelandTYPEs()){ return new ListOfLabelandTYPEs();}
      else { return new ListOfLabelandTYPEs(new LabelandTYPE(this.head.getLabel(),this.head.getTYPE().unifyVarName(origVarName, targVarName)),this.rest.unifyVarName(origVarName, targVarName));}
    }        
    //If any TYPE in this ListOfLabelandTYPEs contains varTYPE, substitute it by the TYPE T.
    public ListOfLabelandTYPEs substitute(String varName, TYPE T)
    { if(this.isEmptyListOfLabelandTYPEs()){ return new ListOfLabelandTYPEs();}
      else { return new ListOfLabelandTYPEs(new LabelandTYPE(this.head.getLabel(),TYPE.substitute(varName, T, this.head.getTYPE())), this.rest.substitute(varName, T));}
    }
    //Unfold each TYPE in this ListOfLabelandTYPEs.
    public ListOfLabelandTYPEs unfold()
    { if(this.isEmptyListOfLabelandTYPEs()){ return new ListOfLabelandTYPEs();}
      else { return new ListOfLabelandTYPEs(new LabelandTYPE(this.head.getLabel(),TYPE.unfold(this.head.getTYPE())),this.rest.unfold());}
    } 
  //Test whether the TYPEs in this listofLabelandTYPEs has the same TYPE with the listofTypeTs one by one.
  public boolean hasSameTYPE(ListOfLabelandTypeTs listofLabelandTypeTs)
  { if(this.size()!=listofLabelandTypeTs.size()){ throw new RuntimeException("The number of members are different.");}
    else if(!this.isEmptyListOfLabelandTYPEs()){ return this.head.getTYPE().equals(listofLabelandTypeTs.head().getTypeT().typeOf())&&this.rest.hasSameTYPE(listofLabelandTypeTs.rest());}
    else return true;
  }  
  //Judge whether this listofLabelandTYPEs has this label or not.
  //It will be used in the parseVALUE method, when is a UNION type, test if the label in the union value has the label
  public boolean hasLabel(String label)
  { if(!this.isEmptyListOfLabelandTYPEs()) return this.head.getLabel().equals(label)||this.rest.hasLabel(label);
    else return false;
  }   
  //This method is used to get the corresponding TYPE in terms of the label
  public TYPE getItsTYPE(String label)
  { if(!hasLabel(label)){ throw new RuntimeException("This ListOfLabelandTYPEs does not have this label.");}
    else if(!this.isEmptyListOfLabelandTYPEs())
    { if(this.head.getLabel().equals(label)){ return this.head.getTYPE();}
      else return this.rest.getItsTYPE(label);
    }
    else throw new RuntimeException("Cannot meet the empty ListOfLabelandTYPEs.");
  }        
  //This method is used to judge whether the first label match the given label
  //It will be used in the parseVALUE method, when is a PRODUCT type, test each label in the TYPE match each label in the value.
  public boolean matchFstLabel(String label)
  { if(!this.isEmptyListOfLabelandTYPEs()) return this.head.getLabel().equals(label);
    else return false;
  }        
}
