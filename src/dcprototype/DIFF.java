
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
import utility.ListOfVars;
import utility.FileParser;

public class DIFF {

     public static void main(String[] args) throws IOException 
     { if(args.length==0||args.length==1)
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
        System.out.println("VAL1:   "+resV1);
        System.out.println("VAL1.TYPE="+resV1.typeOf());

        System.out.println("Comparable TYPE:"+resTYPE.equals(resV1.typeOf()));

        String strVALUE2=FileParser.readFileToString(args[3]);//read the second .VALUE file to a String
        TypeT resV2=ParseVALUEresult.parseVALUE(resTYPE.getResult(), strVALUE2).getResult();//parse VALUE2
        System.out.println("VAL2:   "+resV2);
        
        
        Delta d= TypeT.delta(resV1, resV2);
        System.out.println("Delta:  "+d);
        System.out.println("Sim:    "+d.sim());
        
        /* Test the solution process
         * 
        
        Unknown unknown=new Unknown(resV1, resV2);
        
        System.out.println(unknown.sim());
        CandidatesList refine_0=unknown.refine();
        CandidatesList refine_0_sorted=new CandidatesList().insert_cands(refine_0);
        System.out.println("refine_0_sorted\n"+refine_0_sorted+"\n");
        
        CandidatesList refine_1_sorted=refine_0_sorted.restCands().insert_cands(refine_0_sorted.fstDelta().refine());
        System.out.println("refine_1_sorted\n"+refine_1_sorted+"\n");
        
        */
        
        /*Write delta to a textfile
        String fileName="testResult/Delta_Res/"+cutoff(args[2],".VALUE")+"_vs_"+cutoff(args[3],".VALUE")+".RES";
        String msg="\nTYPE: "+resTYPE+"\nTypeT 1:\n"+resV1+"\nTypeT 2:\n"+resV2+"\n"+"Delta :\n"+d+"\n";
        writefile(fileName, msg.toString());
        */ 
       } 
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
}
