package kernel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import model.World;
import model.nature.Land;
import static model.nature.Land.*;

public class WorldParser {
	
	private static Land getLandFromId(int i) {
		Land l = null;
		switch (i) {
		case 0 : l = PLAIN; break;
		case 1 : l = SEA; break;
		case 2 : l = BEACH; break;
		case 3 : l = WOOD; break;
		case 4 : l = HILL; break;
		}
		return l;
	}
	
	public static World parseWorld(File file) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ressource\\"+file));
		return parseWorld(f);
	}
	
	public static World parseWorld(BufferedReader br) throws IOException {
		String buf;
		int ratio, width, height;
		
		ratio = Integer.parseInt(nextLine(br).trim());
		width = Integer.parseInt(nextLine(br).trim());
		height = Integer.parseInt(nextLine(br).trim());
		World world = new World(width, height);
		
		for (int y = 0; y < (height)/ratio; y++) {
			buf = nextLine(br);
			for (int x = 0; x < (width)/ratio; x++) {
				int id = Integer.parseInt(buf.substring(x*ratio, x*ratio+ratio));
				world.set(x, y, getLandFromId(id));
			}
		}
		
		return world;
	}
	public static World parseWorld(String content) throws IOException {
		BufferedReader f = new BufferedReader(new StringReader(content));
		return parseWorld(f);
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
