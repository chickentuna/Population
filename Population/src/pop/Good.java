package pop;

public enum Good {
	
	 NONE, 
	 WHEAT, 
	 STONE, 
	 PLANK, LOG;
	 
	 public boolean isFood(Good type) {
		 if (type == WHEAT)
			 return true;
		 return false;
	 }
	
}
