import java.util.*;

public class Test {

    public static void main(String[] args) {		
    boolean var=true;
    int index=0;
    Scanner scan = new Scanner(System.in);
    Mosaic mosaic =null;

    while(var){
        System.out.println("\nSelect the options in order:");
        System.out.println("1) Create Mosaic");
        System.out.println("2) Save to File");
        System.out.println("3) Total number of tiles by figure class");
        System.out.println("4) Exit");
        
        try{
            System.out.println("Select one option:\n");
            index=Integer.parseInt(scan.nextLine());


        switch ( index) {
        case 1:
            mosaic = new Mosaic("TileMosaic.txt");
            break;
        case 2:
            if (mosaic!=null){      
                mosaic.saveToFile("OutputMosaic.txt");
            }
            else System.out.println("\nCreate a mosaic first(chose option 1)\n");
            break;
        case 3:
            if (mosaic!=null){     
                int number=0; 
                for (String string : mosaic.listFigureClasses()) {
                    if (mosaic.totalNumberFiguresClass(string)>0) System.out.println("Tiles with "+string+ " figure: "+ mosaic.totalNumberFiguresClass(string));
                    number=number+ mosaic.totalNumberFiguresClass(string);
                }
                if (number>0) System.out.println("Tiles without figure: "+((mosaic.getColumn()*mosaic.getRow())-number));

            }
            else System.out.println("\nCreate a mosaic first(chose option 1)\n");
            break;
        case 4:
            var=false;
            break;
        default:
            System.err.println ( "Unrecognized option" );
            break;
        }
    }
    catch(Exception e){
        System.out.println("Enter a number");

    }
}
    scan.close();
    }
}
