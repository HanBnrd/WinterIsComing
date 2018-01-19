package general;

public class Map {
	// map 7 rows 5 col
	public static int[][] map={{1,	10,	1,	1,	1},
													   {1,	10,	1,	1,	1},
													   {1,	10,	10,	1,	5},
													   {1,	1,	10,	1,	1},
													   {1,	5,	5,	5,	1},
													   {1,	1,	1,	1,	10},
													   {1,	1,	1,	1,	10}};
	// grid costs in sec

	public static int[] POSITION = {6,0,0};
	// POSITION[0] is map row
	// POSITION[1] is map col
	// Position[2] is orientation :
		// 0 for north
		// 1 for east
		// 2 for south
		// 3 for west

	public static int[] WHITEWALKERPOSITION = null; // int[2]
	// POSITION[0] is map row
	// POSITION[1] is map col
}
