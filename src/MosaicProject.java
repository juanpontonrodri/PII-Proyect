import java.io.*;
import java.util.*;

public class MosaicProject{

    public static void main(String[] args) {	
        String file=args[0];
        Mosaic mosaic=null;	

        Scanner input=null;
	    try {
		    input = new Scanner (new FileInputStream(file)) ;
        }
	    catch (FileNotFoundException e) {            
    		System.exit(-1);
        }   

        String line=null;
        while(input.hasNextLine()){
            line=input.nextLine();
            String inst=line.substring(0, line.indexOf(" "));
            String argument=line.substring(line.indexOf(" ")+1,line.length());

            switch (inst) {
                case "ReadMosaic":
                    mosaic=new Mosaic(argument);
                    break;
                case "CreateRegion":
                    String name=argument.substring(0,argument.indexOf(","));
                    int coma1=argument.indexOf(",", argument.indexOf(",")+1);
                    int r0=Integer.parseInt(argument.substring(argument.indexOf(",")+1, coma1));
                    int coma2=argument.indexOf(",",coma1+1);
                    int c0=Integer.parseInt(argument.substring(coma1+1, coma2));
                    int coma3=argument.indexOf(",",coma2+1);
                    int h=Integer.parseInt(argument.substring(coma2+1, coma3));
                    int w=Integer.parseInt(argument.substring(coma3+1,argument.length()));

                    mosaic.addRegion(mosaic.new RectangularRegion(mosaic,name, r0, c0, h, w));
                    break;
                case "ChangeLuminosityMosaic":
                    int valueLM=Integer.parseInt(argument);
                    mosaic.changeLuminosity(valueLM);
                    break;
                case "ChangeLuminosityRegion":
                    int valueLR=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    String regionnameL=argument.substring(argument.indexOf(",")+1,argument.length());
                    mosaic.getRegion(regionnameL).changeLuminosity(valueLR);
                    break;
                case "ChangeLuminosityTile":
                    int valueLT=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    int rowL=Integer.parseInt(argument.substring(argument.indexOf(",")+1,argument.indexOf(",",argument.indexOf(",")+1)));
                    int columnL=Integer.parseInt(argument.substring(argument.indexOf(",",argument.indexOf(",")+1)+1, argument.length()));
                    mosaic.getTile(new Coordinate(rowL, columnL)).changeLuminosity(valueLT);
                    break;
                case "ChangeStatusMosaic":
                    mosaic.changeStatus(Integer.parseInt(argument));
                    break;
                case "ChangeStatusRegion":
                    int statusR=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    String regionnameS=argument.substring(argument.indexOf(",")+1,argument.length());
                    mosaic.getRegion(regionnameS).changeStatus(statusR);
                    break;
                case "ChangeStatusTile":
                    int statusT=Integer.parseInt(argument.substring(0,argument.indexOf(",")));
                    int rowT=Integer.parseInt(argument.substring(argument.indexOf(",")+1,argument.indexOf(",",argument.indexOf(",")+1)));
                    int columnT=Integer.parseInt(argument.substring(argument.indexOf(",",argument.indexOf(",")+1)+1,argument.length()));
                    mosaic.getTile(new Coordinate(rowT, columnT)).setStatus(statusT);
                    break;
                case "SortRegionsByArea":
                    mosaic.sortRegionsByAreaAsc();

                    PrintWriter output = null;       
                    try {      
                        output = new PrintWriter (new FileOutputStream(argument));              
                    }
                    catch (FileNotFoundException e) {
                        PrintWriter eprint = null;       
                        try {      
                            eprint = new PrintWriter (new FileOutputStream("error.txt"));              
                        }
                        catch (FileNotFoundException ef) {
                            System.exit(-1);
                        }        
                        eprint.println("FileNotFoundException");
                        eprint.close();
                        System.exit(-1);
                    }        

                    output.print(mosaic.toStringRegions());
                    output.close();
                    break;
                case "SaveMosaic":
                    mosaic.saveToFile(argument);
                    break;
                default:
                    break;
            }
        }
        input.close();
    }
}
