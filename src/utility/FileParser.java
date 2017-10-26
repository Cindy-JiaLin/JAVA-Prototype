
package utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import typeSystem.TYPE;
import types.TypeChar;
import types.TypeList;


public class FileParser 
{ 
  private final String fileName1, fileName2;
  public FileParser(String f1, String f2)
  { this.fileName1=f1;
    this.fileName2=f2;
  }       
   
  public static void writeStringToFile(String filePathName, String contents) throws IOException          
  { Files.write(Paths.get(filePathName), contents.getBytes());}

  //Comments starts with # will be printed out
  //TYPE or VALUE need to be parsed will be returned. 
  public static String readFileToString(String filePathName) throws IOException
  { String file=new String(Files.readAllBytes(Paths.get(filePathName)));
    String[] lines=file.split("\n");
    StringBuffer buf=new StringBuffer();
    for (int i=0; i<lines.length; i++)
      if (lines[i].startsWith("#")) System.out.println(lines[i]);
      else buf.append(lines[i]);
    return buf.toString();
  }              
}
