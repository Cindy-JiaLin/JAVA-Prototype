
package utility;


import typeSystem.TYPE;

  //This class is used to represent the label and TYPE in PRODUCT and UNION TYPE.
  //The benefit of using it is that it is easy to get the corresponding TYPE from a specific label
  //It will be used as a member of class ListOfLabelandTYPEs
  public class LabelandTYPE 
  { private final String label;
    private final TYPE T;  
    public LabelandTYPE(String label, TYPE T){ this.label=label; this.T=T;}
    
    public String getLabel(){ return this.label;}
    public TYPE getTYPE(){ return this.T;} 
    @Override
    public boolean equals(Object obj)
    { if(obj instanceof LabelandTYPE)
      { LabelandTYPE that=(LabelandTYPE)obj;
        return this.label==that.label&&this.T.equals(that.T);
      }
      else { throw new RuntimeException("This obj="+obj+" is not instance of LabelandTYPE.");}      
    } 
    @Override
    public String toString()
    { StringBuilder buf=new StringBuilder();
      buf.append(this.label);buf.append(".");buf.append(this.T);
      return buf.toString();
    }           
  }