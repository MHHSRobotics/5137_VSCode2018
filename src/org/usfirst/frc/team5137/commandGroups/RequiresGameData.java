package org.usfirst.frc.team5137.commandGroups;

/*
 * Use for all CommandGroups that need gameData to make decisions
 */
public interface RequiresGameData {

	void setGameData(String gameData);
	
}
