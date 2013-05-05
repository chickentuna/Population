package model.technology;

import model.nature.Land;

public interface Building {
	public Land getLand();

	public BType getType();
}
