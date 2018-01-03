
package dcprototype;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import typeSystem.ParseTYPEresult;
import typeSystem.ParseVALUEresult;
import typeSystem.TYPE;
import types.TypeT;
import delta.Delta;
import types.TypeBool;
import types.TypeChar;
import types.TypeList;
import types.TypeMapping;
import types.TypeMultiset;
import types.TypeNat;
import types.TypeProduct;
import types.TypeReal;
import types.TypeRec;
import types.TypeSet;
import types.TypeUnion;
import types.TypeUnit;
import utility.ListOfVars;
import utility.FileParser;

public class DIFF {

     public static void main(String[] args) throws IOException 
     { 
      final long startTime = System.currentTimeMillis();
 
      if(args.length==0||args.length==1)
      { System.out.print("Thank you to use this program, there are three options:\n");
        System.out.print("(1) Please enter -t path and name of a .TYPE file to present a type.\n");
        System.out.print("(2) Please enter -v path and name of a .TYPE file and a .VALUE file to build a value of this type.\n");
        System.out.print("(3) Please enter -d path and name of a .TYPE file and two .VALUE files to compare these two values.\n");
      }
      else if(args.length==2)//testTYPEparser.sh
      { if(!args[0].equals("-t")||!args[1].endsWith(".TYPE"))
        { System.out.print("Please enter the correct option '-t .TYPE file' to present a type.");}
        
        System.out.print("\nFileName: "+args[1]);
        String strTYPE=FileParser.readFileToString(args[1]);//read the file to a String
        ParseTYPEresult resTYPE=ParseTYPEresult.parseTYPE(new ListOfVars(), strTYPE);//parse TYPE
        System.out.print(" Unfold this type: \n"+TYPE.unfold(resTYPE.getResult())+"\n");
        String fileName="testResult/TYPE_Res/"+cutoff(args[1],".TYPE")+".RES";
        String msg="\nFileName: "+args[1]+"\nparseTYPE: \n"+resTYPE+"\n"+"Unfold this type: \n"+TYPE.unfold(resTYPE.getResult())+"\n";
        writefile(fileName, msg.toString());

      }
      else if(args.length==3)//testVALUEparser.sh
      { if(!args[0].equals("-v")||!args[1].endsWith(".TYPE")||!args[2].endsWith(".VALUE"))
        { System.out.print("Please enter the correct option '-v .TYPE file .VALUE file ' to build a value of the type.");}  
        
        String strTYPE=FileParser.readFileToString(args[1]);//read the .TYPE file to a String
        ParseTYPEresult resTYPE=ParseTYPEresult.parseTYPE(new ListOfVars(), strTYPE);//parse TYPE

        String strVALUE=FileParser.readFileToString(args[2]);//read the .VALUE file to a String
        TypeT resV=ParseVALUEresult.parseVALUE(resTYPE.getResult(), strVALUE).getResult();//parse VALUE
        System.out.print("\nTypeT: "+resV+"\n");
        

        String fileName="testResult/VALUE_Res/"+cutoff(args[2],".VALUE")+".RES";
        String msg="\nTypeT: "+resV+"\n";
        writefile(fileName, msg.toString());

      } 
      else// the args.length==4  present_Sim_Delta.sh
      { if(!args[0].equals("-d")||!args[1].endsWith(".TYPE")||!args[2].endsWith(".VALUE")||!args[3].endsWith(".VALUE"))
        { System.out.print("Please enter the correct option '-d .TYPE file .VALUE file1 .VALUE file2' to compare these two values of the type.");}  
        
        // The sequence is important, as the readFileToString operation copies comment lines to stdout

        System.out.println("-----------------------------------------------------");
        System.out.println("diff "+args[0]+" "+args[1]+" "+args[2]+" "+args[3]);
        String strTYPE=FileParser.readFileToString(args[1]);//read the .TYPE file to a String
        ParseTYPEresult resTYPE=ParseTYPEresult.parseTYPE(new ListOfVars(), strTYPE);//parse TYPE
        System.out.println("TYPE:   "+resTYPE);

        String strVALUE1=FileParser.readFileToString(args[2]);//read the first .VALUE file to a String
        TypeT resV1=ParseVALUEresult.parseVALUE(resTYPE.getResult(), strVALUE1).getResult();//parse VALUE1
        //System.out.println("VAL1:   "+resV1);

        String strVALUE2=FileParser.readFileToString(args[3]);//read the second .VALUE file to a String
        TypeT resV2=ParseVALUEresult.parseVALUE(resTYPE.getResult(), strVALUE2).getResult();//parse VALUE2
        //System.out.println("VAL2:   "+resV2);
        
        TypeT model1 = model(resTYPE.getResult(), resV1);
        TypeT model2 = model(resTYPE.getResult(), resV2);
        System.out.println("VAL1:   "+model1);
        System.out.println("VAL2:   "+model2);
        
        Delta d= TypeT.delta(model1, model2);
        System.out.println("Delta:  "+d);
        System.out.println("Sim:    "+d.sim());
        
        //Write delta to a textfile
        String fileName="testResult/Delta_Res/"+cutoff(args[2],".VALUE")+"_vs_"+cutoff(args[3],".VALUE")+".RES";
        String msg="\nTYPE: "+resTYPE+"\nTypeT 1:\n"+resV1+"\nTypeT 2:\n"+resV2+"\n"+"Delta :\n"+d+"\n";
        writefile(fileName, msg.toString());
       } 
       ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       final long endTime   = System.currentTimeMillis();
       final long totalTime = (endTime - startTime)/1000;
       System.out.println("duration:"+totalTime+"s");
    }
    //Writing the test result to a text file allows us to re-organise the format of the result
    //See the result, in particular, the delta format more clearly.
    private static void writefile(String fileName, String text) throws IOException 
    { Path newFile = Paths.get(fileName);
      try { Files.deleteIfExists(newFile);
            newFile = Files.createFile(newFile);
          } 
      catch (IOException ex) { System.out.println("Error creating file");}
      Files.write(newFile, text.getBytes());
    }
    //Different from the cutoff method in ParseVALUEresult class
    //This method is used to get the file name without its suffix
    //The suffix either is .TYPE or .VALUE
    private static String cutoff(String str, String suffix)
    { str=str.trim();
      for(int i=str.indexOf("/"); i>-1 ; i=str.indexOf("/"))
      {str=str.substring(i+1);}
      if(str.endsWith(suffix)){ return str.substring(0, (str.length()-suffix.length())).trim();}
      else{ return str;}  
    }   
    
    // model values with their TYPE
    private static TypeT model(TYPE T, TypeT t)
    { //model primitive types one by one
      if(T.isUNIT() && t.typeOf().isUNIT())
      { return new TypeUnit(T);
      }  
      else if(T.isBOOL() && t.typeOf().isBOOL()) 
      { TypeBool v = (TypeBool)t;
        return new TypeBool(T, v.getValue());
      }
      else if(T.isREAL() && t.typeOf().isREAL())
      { TypeReal v = (TypeReal)t;
        return new TypeReal(T, v.getValue());
      }    
      else if(T.isNAT() && t.typeOf().isNAT())
      { TypeNat v = (TypeNat)t;
        return new TypeNat(T, v.getValue());
      }    
      else if(T.isCHAR() && t.typeOf().isCHAR())
      { TypeChar v = (TypeChar)t;
        return new TypeChar(T, v.getValue());
      }  
      // model structured types one by one
      else if(T.isPRODUCT() && t.typeOf().isPRODUCT() && T.equals(t.typeOf()))
      { TypeProduct v =(TypeProduct)t;
        return new TypeProduct(T, v.getValues());
      }   
      else if(T.isUNION() && t.typeOf().isUNION() && T.equals(t.typeOf()))
      { TypeUnion v = (TypeUnion)t;
        return new TypeUnion(T, v.getLabel(), v.getValue());
      }    
      else if(T.isREC() && TYPE.unfold(T).equals(t.typeOf()))
      { return new TypeRec(T, t);
      }    
      else if(T.isLIST() && t.typeOf().isLIST() && T.getBaseTYPE().equals(t.typeOf().getBaseTYPE()))
      { TypeList v = (TypeList) t;
        if(v.isEmptyList()) return new TypeList(T.getBaseTYPE());
        else{ return new TypeList(T.getBaseTYPE(), v.getFst(), v.getRest());}
      }    
      else if(T.isSET() && t.typeOf().isSET() && T.getBaseTYPE().equals(t.typeOf().getBaseTYPE()))
      { TypeSet v = (TypeSet) t;
        if(v.isEmptySet()) return new TypeSet(T.getBaseTYPE());
        else{ return new TypeSet(T.getBaseTYPE(), v.getFst(), v.getRest());}
      }    
      else if(T.isMSET() && t.typeOf().isMSET() && T.getBaseTYPE().equals(t.typeOf().getBaseTYPE()))
      { TypeMultiset v = (TypeMultiset)t;
        if(v.isEmptyMultiset()) return new TypeMultiset(T.getBaseTYPE());
        else { return new TypeMultiset(T.getBaseTYPE(), v.getFst(), v.getRest());}
      }
      else if(T.isMAPPING() && t.typeOf().isMAPPING() && T.equals(t.typeOf()))
      { TypeMapping v = (TypeMapping)t;
        if(v.isEmptyMapping()) return new TypeMapping(T);
        else{ return new TypeMapping(T, v.getDomFst(), v.getCodFst(), v.getRest());}
      }    
      else { throw new RuntimeException("There is no other TYPE currently");}
    }        
}
