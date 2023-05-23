package me.hydram.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/*Managed die Anzeige der Lebensaneige*/

public class LiveCounter extends HBox {
	
	public static Image liveHeartImage;
	public void update(int lives)
	{
		this.getChildren().clear();
		for(int i = 0; i < lives;i++)
		{
			ImageView liveHeart = new ImageView(liveHeartImage);
			liveHeart.setFitHeight(Game.tileSize);	//Setzt die Größe des Bildes in der Höhe
			liveHeart.setFitWidth(Game.tileSize);	//Setzt die Größe des Bildes in der Breite
			liveHeart.opacityProperty().set(0.6);
			this.getChildren().add(liveHeart);
		}
	}
	public LiveCounter()
	{
		super();
		 liveHeartImage = new Image(this.getClass().getResourceAsStream("graphics/live.png"));
	}
}
