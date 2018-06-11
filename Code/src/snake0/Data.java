package snake0;

import map.Map;

/**
 * Class pour stocker toutes les données relatives au jeu
 * @author Alexandre,Damien,Elie
 *
 */
public class Data
{

    
    public static short CASESIZE = 20;
    public static short SNAKESIZE = 3;
    public static short NBRCASEX = 30;
    public static short NBRCASEY = 30;
    public static int SNAKESPEED = 80;
    public static Map MAP = new Map(Data.CASESIZE*Data.NBRCASEX, Data.CASESIZE*Data.NBRCASEY);
	
    public static void reborn()
	{
		MAP = new Map(Data.CASESIZE*Data.NBRCASEX, Data.CASESIZE*Data.NBRCASEY);
	}
}
