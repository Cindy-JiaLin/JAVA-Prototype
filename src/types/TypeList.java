
package types;

import delta.Deletion;
import delta.Delta;
import delta.DeltaList;
import delta.Id;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import solution.CandidatesList;
import solution.StepList;
import typeSystem.TYPE;

public class TypeList extends TypeT
{ private final TYPE baseType;
  private final TypeT head;
  private final TypeList rest;
  // Constructor for empty list
  public TypeList(TYPE baseType){ this.baseType=baseType; this.head=null; this.rest=null;}
  // Constructor for non-empty List
  public TypeList(TYPE baseType, TypeT head, TypeList rest)
  { if(!head.typeOf().equals(baseType)||!rest.baseType.equals(baseType)) throw new RuntimeException("head type doesn't match the baseType");
    this.baseType=baseType; this.head=head; this.rest=rest;
  }

  public static TypeList EMPTY_LIST(TYPE baseType){ return baseType.getEmptyList(); }
  
  public boolean isEmptyList(){ return this.head==null&&this.rest==null;}
  
  @Override
  public TYPE typeOf(){ return TYPE.LIST(this.baseType);}  
  public TypeT getFst()
  { if(!this.isEmptyList()){ return this.head;}
    else{ throw new RuntimeException("This is an empty list, no head to be returned.");}
  }
  public TypeList getRest()
  { if(!this.isEmptyList()){ return this.rest;}
    else if(this.isEmptyList()){ return EMPTY_LIST(this.baseType);}  
    else { throw new RuntimeException("Illegal constructor usage in TypeList getRest() method.");}
  }
  // insert an element in front of this list
  public TypeList ins(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be inserted.");}
    else{ return new TypeList(this.baseType, a, this);}
  } 
  // append an element at the end of this list
  public TypeList append(TypeT a)
  { if(!a.typeOf().equals(this.baseType))
    { throw new RuntimeException("The a.typeOf()="+a.typeOf()+" is different from"+this.baseType+" cannot be appended.");}
    else if(!this.isEmptyList()){ return new TypeList(this.baseType, this.head, this.rest.append(a));}
    else if(this.isEmptyList()){ return new TypeList(this.baseType, a, new TypeList(this.baseType));}
    else { throw new RuntimeException("Illegal constructor usage in TypeList append() method.");}
  }         
  
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeList)
    { TypeList that=(TypeList)obj;
      if(!this.baseType.equals(that.baseType)){ throw new RuntimeException("They have different baseType, cannot be compared.");}
      else
      { if(!this.isEmptyList()&&!that.isEmptyList())
        { return this.head.equals(that.head)&&this.rest.equals(that.rest);}
        else {return this.isEmptyList()&&that.isEmptyList();}//if both empty return true; else return false.
      }    
    }  
    else { throw new RuntimeException("This obj="+obj+" is not of TypeList");}
  } 
  @Override
  public String toString()
  { StringBuffer buf=new StringBuffer();
    buf.append("[");
    if(!isEmptyList())dump(buf);
    buf.append("]"); 
    return buf.toString();
  }
  private void dump(StringBuffer buf)
  { buf.append(this.head);
    if(!rest.isEmptyList()){ buf.append(","); this.rest.dump(buf);}  
  } 
  @Override
  public double weight()
  { if(!this.isEmptyList()){ return this.head.weight()+this.rest.weight();}  
    else if(this.isEmptyList()){ return 0.0;}
    else { throw new RuntimeException("Illegal constructor usage in TypeList weight() method.");}
  }
  
  @Override
  public CandidatesList refine(TypeT obj)
  { if(obj.typeOf().equals(this.typeOf()))
    { TypeList that= (TypeList)obj;
      //If these two lists both are non-empty lists.
      //This is the general case, there will be three possibilities.
      //Each possibility only extend one step.
      if(!this.isEmptyList()&&!that.isEmptyList()) 
      { DeltaList path1, path2, path3;
        path1=new DeltaList(new StepList(new Unknown(this.head, that.head), new StepList(new UnknownRest(this.rest, that.rest), new StepList())));  
        path2=new DeltaList(new StepList(new Deletion(this.head), new StepList(new UnknownRest(this.rest, that), new StepList())));
        path3=new DeltaList(new StepList(new Insertion(that.head), new StepList(new UnknownRest(this, that.rest), new StepList())));
        return new CandidatesList(path1,new CandidatesList(path2, new CandidatesList(path3, new CandidatesList())));
      }
      //If one list is empty, while the other is not
      //Only refine one step, the first element in "that" list will be inserted.
      else if(this.isEmptyList()&&!that.isEmptyList())
      { DeltaList path=new DeltaList(new StepList(new Insertion(that.head), new StepList(new UnknownRest(this, that.rest), new StepList())));
        return new CandidatesList(path,new CandidatesList());
      }  
      //If one list is non-empty, while the other is empty
      //Only refine one step, the first element in "this" list will be deleted.
      else if(!this.isEmptyList()&&that.isEmptyList())
      { DeltaList path=new DeltaList(new StepList(new Deletion(this.head), new StepList(new UnknownRest(this.rest, that), new StepList())));
        return new CandidatesList(path,new CandidatesList());
      } 
      //If both lists are empty
      //here used to implement the recursion when (UnknownRest [][]) to empty lists
      //The case of two empty lists are dealt in the Unknown refine method.
      else if(this.isEmptyList()&&that.isEmptyList())
      { Delta path=new DeltaList(new StepList());
        return new CandidatesList(path,new CandidatesList());
      }
      else{ throw new RuntimeException("Illegal constructor usage in TypeList refine() method.");}          
    }
    else{ throw new RuntimeException(obj+" is NOT of TypeList.");}  
  }        
}
