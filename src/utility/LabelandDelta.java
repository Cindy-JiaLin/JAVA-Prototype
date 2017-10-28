
package utility;

import delta.Delta;

public class LabelandDelta 
{ private final String label;
  private final Delta d;  
  public LabelandDelta(String label, Delta d){ this.label=label; this.d=d;}
    
    public String getLabel(){ return this.label;}
    public Delta getDelta(){ return this.d;} 
    @Override
    public boolean equals(Object obj)
    { if(obj instanceof LabelandDelta)
      { LabelandDelta that=(LabelandDelta)obj;
        return this.label==that.label&&this.d.equals(that.d);
      }
      else { throw new RuntimeException("This obj="+obj+" is not instance of LabelandDelta.");}      
    } 
    @Override
    public String toString()
    { StringBuilder buf=new StringBuilder();
      buf.append(this.label);buf.append(".");buf.append(this.d);
      return buf.toString();
    }         
    
}
