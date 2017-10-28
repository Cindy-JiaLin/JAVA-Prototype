package utility;

import types.TypeT;

public class LabelandTypeT
{ private final String label;
    private final TypeT t;  
    public LabelandTypeT(String label, TypeT t){ this.label=label; this.t=t;}
    
    public String getLabel(){ return this.label;}
    public TypeT getTypeT(){ return this.t;} 
    @Override
    public boolean equals(Object obj)
    { if(obj instanceof LabelandTypeT)
      { LabelandTypeT that=(LabelandTypeT)obj;
        return this.label==that.label&&this.t.equals(that.t);
      }
      else { throw new RuntimeException("This obj="+obj+" is not instance of LabelandTypeT.");}      
    } 
    @Override
    public String toString()
    { StringBuilder buf=new StringBuilder();
      buf.append(this.label);buf.append(".");buf.append(this.t);
      return buf.toString();
    }           
}
