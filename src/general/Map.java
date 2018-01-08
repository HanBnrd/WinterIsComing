package general;

public class Map {
	// map 7 rows 5 col
	public static int[][] map={{4,2,1,1,0},
							   {1,2,1,1,1},
							   {1,2,2,1,3},
							   {1,1,2,1,1},
							   {1,3,3,3,1},
							   {1,1,1,4,2},
							   {0,1,1,1,2}	};
	// 0 for white
	// 1 for green
	// 2 for blue
	// 3 for brown
	// 4 for red

	public static int[] POSITION=new int[3];
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
