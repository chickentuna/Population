package model.technology;

import model.nature.Land;

public interface Building {
	public Land.Type getLand();
	public BType getType();
}
