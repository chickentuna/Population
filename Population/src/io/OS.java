package io;

public class OS {

	public static String getSlash() {
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			return "\\";
		else
			return "/";
	}

}
