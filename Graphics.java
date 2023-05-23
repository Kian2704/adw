package me.hydram.game;

import javafx.scene.image.Image;

public class Graphics {
	private static Image pointTexture;
	private static Image powerUpTexture;
	private static Image spawnBlockerTexture;
	private static Image eatableGhost;
	private static Image ghost1;
	private static Image ghost2;
	private static Image ghost3;
	private static Image ghost4;
	private static Image ghost5;
	
	
	
	public Graphics()
	{
		pointTexture = new Image(this.getClass().getResourceAsStream("graphics/point.png"));				//läd sie Textur für den Punkt
		powerUpTexture = new Image(this.getClass().getResourceAsStream("graphics/powerup.png"));			//läd die Textur für Powerups
		spawnBlockerTexture = new Image(this.getClass().getResourceAsStream("graphics/spawnBlocker.png"));	//läd die Textur für den Spwan Blocker
		eatableGhost = new Image(this.getClass().getResourceAsStream("graphics/eat.gif"));					//läd die Textur für den Essbaren Geist
		ghost1 = new Image(this.getClass().getResourceAsStream("graphics/red.gif"));						//läd die Textur für Geist 1
		ghost2 = new Image(this.getClass().getResourceAsStream("graphics/green.gif"));						//läd die Textur für Geist 2
		ghost3 = new Image(this.getClass().getResourceAsStream("graphics/blue.gif"));						//läd die Textur für Geist 3
		ghost4 = new Image(this.getClass().getResourceAsStream("graphics/orange.gif"));						//läd die Textur für Geist 4
		ghost5 = new Image(this.getClass().getResourceAsStream("graphics/purple.gif"));						//läd die Textur für Geist 5
		
	}
	
	
	public static Image getPointTexture() {return pointTexture;}				//gibt die Textur für den Punkt wieder
	public static Image getPowerUpTexture()	{return powerUpTexture;}			//gibt die Textur für das Powerup wieder
	public static Image getSpawnBlockerTexture() {return spawnBlockerTexture;}	//gibt die Textur für den Spawn Blocker wieder
	public static Image getEatableGhostTexture() {return eatableGhost;}			//gibt die Textur für die Essbaren Geister wieder
	public static Image getGhostTexture(Ghost ghost) {
		if(ghost.isEatable() == false){
			switch(ghost.getGhostId()){
			case 0: return ghost1;	//gibt die Textur für Geist	1 wieder
			case 1:	return ghost2;	//gibt die Textur für Geist	2 wieder
			case 2:	return ghost3;	//gibt die Textur für Geist	3 wieder
			case 3:	return ghost4;	//gibt die Textur für Geist	4 wieder
			case 4:	return ghost5;	//gibt die Textur für Geist	5 wieder
			}
		}
		else{
			return eatableGhost;
		}
		return null;
	}
	
}
