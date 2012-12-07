package kernel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import model.World;
import model.nature.Land;

public class WorldParser {
	
	private static Land getLandFromId(int i) {
		Land l = null;
		switch (i) {
		case 0 : l = Land.PLAIN; break;
		case 1 : l = Land.SEA; break;
		case 2 : l = Land.BEACH; break;
		case 3 : l = Land.WOOD; break;
		case 4 : l = Land.HILL; break;
		}
		return l;
	}
	
	public static World parseWorld(String str) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ressource\\"+str));
		String buf;
		int ratio, width, height;
		
		ratio = Integer.parseInt(nextLine(f).trim());
		width = Integer.parseInt(nextLine(f).trim());
		height = Integer.parseInt(nextLine(f).trim());
		World world = new World(width, height);
		
		for (int y = 0; y < (height)/ratio; y++) {
			buf = nextLine(f);
			for (int x = 0; x < (width)/ratio; x++) {
				int id = Integer.parseInt(buf.substring(x*ratio, x*ratio+ratio));
				world.set(x, y, getLandFromId(id));
			}
		}
		
		return world;
	}
	
	private static String nextLine(BufferedReader reader) throws IOException {
		String buf = "";

		while (buf.length() == 0) {
			buf = reader.readLine();
			if (buf == null)
				return null;
			int com = buf.indexOf("#");
			if (com > -1)
				buf = buf.substring(0, com);
		}
		return buf;
	}

}
