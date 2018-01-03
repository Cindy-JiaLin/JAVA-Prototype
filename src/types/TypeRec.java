
package types;

import delta.Deletion;
import delta.Delta;
import delta.Insertion;
import delta.Unknown;
import delta.UnknownRest;
import delta.DeltaRec;
import solution.CandidatesList;
import solution.StepList;
import typeSystem.ParseVALUEresult;
import typeSystem.TYPE;
import utility.LabelandTYPE;
import utility.ListOfLabelandTYPEs;
import utility.ListOfLabelandTypeTs;

public class TypeRec extends TypeT
{ private final TYPE T;
  private final TypeT typeBody;
  public TypeRec(TYPE T, TypeT typeBody)
  {//In the constructor of TypeRec, the TYPE of typeBody has already been UNION of PRODUCT TYPE.
   //This case has been considered in the equals method in TYPE class.
    if(!T.isREC()||!TYPE.unfold(T).equals(typeBody.typeOf()))
    { throw new RuntimeException("This recursive type does not match its TYPE=\n"+T);}
    //System.out.println("T="+T+"\n");
    //System.out.println("TYPE.unfold(T)=\n"+TYPE.unfold(T)+"\n");
    //System.out.println("typeBody.typeOf()=\n"+typeBody.typeOf()+"\n");
    //System.out.println(TYPE.unfold(T).equals(typeBody.typeOf()));
    //System.out.println(T.isREC());
    this.T=T; this.typeBody=typeBody;
  }
  @Override
  public TYPE typeOf() { return this.T;}
  public TypeT getBody(){ return this.typeBody;}
  
  @Override
  public boolean equals(Object obj)
  { if(obj instanceof TypeRec)
    { TypeRec that=(TypeRec)obj;
      return this.T.equals(that.T)&&this.typeBody.equals(that.typeBody);
    }
    else { throw new RuntimeException("This obj="+obj+" is not instance of TypeRecursive.");}
  }          
  @Override
  public String toString()
  { StringBuilder buf=new StringBuilder(); buf.append(this.typeBody); return buf.toString();}          
 
  @Override
  public double weight() { return this.typeBody.weight();}
  @Override
  public CandidatesList refine(TypeT obj) 
  { if(obj.typeOf().isREC() && this.T.isREC())
    { TypeRec that =(TypeRec)obj;
      if(!this.getBody().typeOf().isUNION() || !that.getBody().typeOf().isUNION())
      { throw new RuntimeException("One or both of these recurtive type do not contain union body."); }
      TypeUnion union1 = (TypeUnion) this.getBody();
      TypeUnion union2 = (TypeUnion) that.getBody();
      
      if(union1.isNil() && union2.isNil()) 
      { DeltaRec path=new DeltaRec(new StepList());
        return new CandidatesList(path,new CandidatesList());
      }
      else if(union1.isNil() && !union2.isNil())
      { Delta deltaU = union1.refine(union2).fstDelta();
        DeltaRec path=new DeltaRec(new StepList(deltaU, new StepList()));
        return new CandidatesList(path,new CandidatesList());
      }
      else if(!union1.isNil() && union2.isNil())
      { Delta deltaU = union1.refine(union2).fstDelta();
        DeltaRec path=new DeltaRec(new StepList(deltaU, new StepList()));
        return new CandidatesList(path,new CandidatesList());
      }
      else// if (!union1.isNil() && !union2.isNil())
      { if(!union1.getValue().typeOf().isPRODUCT()||!union2.getValue().typeOf().isPRODUCT())
        { throw new RuntimeException("\nunion1="+union1+"\n union2="+union2); } 
        TypeProduct p1 = (TypeProduct) union1.getValue();
        TypeProduct p2 = (TypeProduct) union2.getValue();
        ListOfLabelandTypeTs l1=p1.getValues();
        ListOfLabelandTypeTs l2=p2.getValues();
        // When the second component in the product is the recursion part (like list) head+rest, the rest is a list  
        if(TYPE.unfold(T).equals(l1.rest().head().getTypeT().typeOf())&&
           TYPE.unfold(T).equals(l2.rest().head().getTypeT().typeOf()))
        { TypeRec restRec1 = new TypeRec(this.T, l1.rest().head().getTypeT());
          TypeRec restRec2 = new TypeRec(this.T, l2.rest().head().getTypeT());
          
          DeltaRec path1, path2, path3;
          path1=new DeltaRec(new StepList(new Unknown(l1.head().getTypeT(), l2.head().getTypeT()), new StepList(new UnknownRest(restRec1, restRec2), new StepList())));  
          path2=new DeltaRec(new StepList(new Deletion(l1.head().getTypeT()), new StepList(new UnknownRest(restRec1, that), new StepList())));
          path3=new DeltaRec(new StepList(new Insertion(l2.head().getTypeT()), new StepList(new UnknownRest(this, restRec2), new StepList())));
                    
          return new CandidatesList(path1,new CandidatesList()).insert_cand(path2).insert_cand(path3);
        }
        // (1) T=
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        // (2) TYPE.unfold(T)=
        // UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))*cdr.VAR(n))))))
        // (3) The l1.rest().head().getTypeT().typeOf()=
        // UNION(nil.UNIT|lst.PRODUCT(car.
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        //                           *cdr.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        //                                                                *cdr.VAR(n))))))
        // (4) The l2.rest().head().getTypeT().typeOf()=
        // UNION(nil.UNIT|lst.PRODUCT(car.
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        //                           *cdr.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        //                                                                *cdr.VAR(n))))))
        // *********************************************************************************************************************
        // Use T to substitute the 
        // REC(t:UNION(emptyTREE.UNIT|tree.PRODUCT(node.NAT*branches.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.VAR(t)*cdr.VAR(n)))))))
        // in (3) and (4), then we got:
        // UNION(nil.UNIT|lst.PRODUCT(car.T * cdr.REC(n:UNION(nil.UNIT|lst.PRODUCT(car.T * cdr.VAR(n))))))
        // 
        else// When the second component in a product is another recursive type (like tree) root+list of subtrees
        { LabelandTYPE fstSubTree = new LabelandTYPE("car", this.T); 
          LabelandTYPE restSubTrees = new LabelandTYPE("cdr", TYPE.VAR("n"));
          ListOfLabelandTYPEs listOfSubTrees = new ListOfLabelandTYPEs(fstSubTree, new ListOfLabelandTYPEs(restSubTrees, new ListOfLabelandTYPEs()));
          
          LabelandTYPE emptySubTree = new LabelandTYPE("nil", TYPE.UNIT);
          LabelandTYPE subTrees = new LabelandTYPE("lst", TYPE.PRODUCT(listOfSubTrees));
          ListOfLabelandTYPEs unionSubTrees = new ListOfLabelandTYPEs(emptySubTree, new ListOfLabelandTYPEs(subTrees, new ListOfLabelandTYPEs()));
          
          TYPE recLIST = TYPE.REC("n", TYPE.UNION(unionSubTrees));
                                                                    
          // the restRec1 is subtrees of this
          // the restRec2 is subtrees of that
          TypeRec restRec1 = new TypeRec(recLIST, l1.rest().head().getTypeT());
          TypeRec restRec2 = new TypeRec(recLIST, l2.rest().head().getTypeT());
          // this and that are both of TYPE this.T (is a Tree), when their subtrees need to be compared with them
          // restRec1 compare to that, when delete the root from this
          // this compare to restRec2, when insert the root from that
          // this and that need to be converted to a list contains one single three [this] and [that].
          String listThis="lst.(car."+this+", cdr.nil.unit)";
          String listThat="lst.(car."+that+", cdr.nil.unit)";
          TypeT thisValue=ParseVALUEresult.parseVALUE(recLIST, listThis).getResult();
          TypeT thatValue=ParseVALUEresult.parseVALUE(recLIST, listThat).getResult();
          TypeRec listContainsThis = new TypeRec (recLIST, thisValue);
          TypeRec listContainsThat = new TypeRec (recLIST, thatValue);
          
          DeltaRec path1, path2, path3;
          path1=new DeltaRec(new StepList(new Unknown(l1.head().getTypeT(), l2.head().getTypeT()), new StepList(new UnknownRest(restRec1, restRec2), new StepList())));  
          path2=new DeltaRec(new StepList(new Deletion(l1.head().getTypeT()), new StepList(new UnknownRest(restRec1, listContainsThat), new StepList())));
          path3=new DeltaRec(new StepList(new Insertion(l2.head().getTypeT()), new StepList(new UnknownRest(listContainsThis, restRec2), new StepList())));
                    
          return new CandidatesList(path1,new CandidatesList()).insert_cand(path2).insert_cand(path3);
        }
      }
    }
    else { throw new RuntimeException("They are not of recursive TYPE");}
  }
}
