package io;

import static model.nature.Land.Type.BEACH;
import static model.nature.Land.Type.HILL;
import static model.nature.Land.Type.PLAIN;
import static model.nature.Land.Type.SEA;
import static model.nature.Land.Type.WOOD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import model.World;
import model.nature.Land;
import model.nature.Land.Type;

public class WorldParser {

	private static Land createLandFromId(int i) {
		Type l = Type.NULL;
		
		switch (i) {
		case 0:
			l = PLAIN;
			break;
		case 1:
			l = SEA;
			break;
		case 2:
			l = BEACH;
			break;
		case 3:
			l = WOOD;
			break;
		case 4:
			l = HILL;
			break;
		}
		return new Land(l);
	}

	public static World parseWorldFromFile(String world_file) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("resource" + File.separatorChar + world_file));
		return parseWorld(f);
	}

	public static World parseWorld(BufferedReader br) throws IOException {
		String buf;
		int ratio, width, height;

		ratio = Integer.parseInt(nextLine(br).trim());
		width = Integer.parseInt(nextLine(br).trim());
		height = Integer.parseInt(nextLine(br).trim());
		World world = new World(width, height);

		for (int y = 0; y < (height) / ratio; y++) {
			buf = nextLine(br);
			for (int x = 0; x < (width) / ratio; x++) {
				int id = Integer.parseInt(buf.substring(x * ratio, x * ratio + ratio));
				world.set(x, y, createLandFromId(id));
			}
		}

		return world;
	}

	public static World parseWorldFromString(String content) throws IOException {
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
//	
//	public static World parseWorldFromImage(String world_file) throws IOException {
//		BufferedInputStream f = new BufferedInputStream(new FileInputStream("resource"+File.separator+world_file));
//		byte[] col = new byte[3];
//		boolean eof = false;
//		
//		while (!eof) {
//			f.read(col, 0, 3);
//			System.out.println((int)col[1]);
//			Color c = new Color((int)col[0],(int)col[1],(int)col[2]);
//			System.out.println(c.getGreen());
//			System.out.println(c.getGreenByte());
//			
//		}
//		return null;
//		
//	}

}
