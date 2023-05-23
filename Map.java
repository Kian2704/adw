package me.hydram.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Scanner;

import javafx.scene.layout.Pane;

/*Läd die Map, auf der der Spieler sich bewegt*/

public class Map {
	public static Tile[][] tiles; //cols - rows
	public static Tile[] tiles1d;
	public static Vec2 pacmanSpawn = null;
	public static Vec2 pacmanSpawn2 = null;
	public static int numberPathTiles;
	public static Vec2 ghostSpawn;
	public static Tile[][] getMap(int level,int cols, int rows)
	{
		numberPathTiles = 0;
		try {
			InputStream is;
			if(Main.enableMultiplayer == false)
			is = Map.class.getResourceAsStream("maps/map_" + level + ".pacmanmap");
			else
			is = Map.class.getResourceAsStream("maps/map_localmultiplayer_" + level + ".pacmanmap");
		      //File mapFile = new File("maps/map_" + level + ".pacmanmap");
			BufferedReader mapFile = new BufferedReader(new InputStreamReader(is));
		      
		      
		      Scanner myReader = new Scanner(mapFile);
		      if(tiles == null)	//Sendet Fehlermeldung, falls Map Datei leer
	        	{
	        		System.out.println("Error Tile Array not initialized!");
	        		myReader.close();
	        		return null;
	        	}
		      
		      int count = 0;
		      while (myReader.hasNextLine()) {	//solange nächste Zeile in Map Datei vorhanden
		    	  if(count >= rows)	//Sendet Fehlermeldung, falls in der Map Datei mehr Zeilen definiert sind, als in das Fenster passen
		    	  {
		    		  System.out.println("Map size doesn't match Screen size!");
		    	  }
		        String line = myReader.nextLine();	//Setzt den String "line" auf die nächste Zeile der Map Datei 
		        for(int i = 0; i < line.length();i++) //für jedes Zeichen in der aktuellen Zeile
		        {
		        	if(i > cols) //Sendet Fehlermeldung, falls in der Map Datei mehr Spalten definiert sind, als in das Fenster passen
		        	{
			    		  System.out.println("Map size doesn't match Screen size!");
			    		  myReader.close();
			    		  return null;
			    	}
		        	
		        	switch(line.charAt(i)) { 
		        	case 'X' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "X" ist den jeweiligen Wert im Map Array auf den Wert für Weg
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,1);
		        		tiles[i][count].setEnt(new Point());
		        		break;
		        	}
		        	case 'N' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "N" ist den jeweiligen Wert im Map Array auf den Wert für Leeres Feld
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,4);
		        		break;
		        	}
		        	case 'W' : tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,0);break; //wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "W" ist den jeweiligen Wert im Map Array auf den Wert für Wand
		        	case 'P' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "P" ist den jeweiligen Wert im Map Array auf den Wert für PacMan
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,3);
		        		if(pacmanSpawn == null)
		        		{	
		        			pacmanSpawn = new Vec2(tiles[i][count].getX(),tiles[i][count].getY());
		        		}
		        		else if(pacmanSpawn != null && Main.enableMultiplayer == true)
		        		{
		        			pacmanSpawn2 = new Vec2(tiles[i][count].getX(),tiles[i][count].getY());
		        		}
		        		break;
		        	}
		        	case 'G' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "G" ist den jeweiligen Wert im Map Array auf den Wert für Geist
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,2);
		        		ghostSpawn = new Vec2(tiles[i][count].getX(),tiles[i][count].getY());
		        		break;
		        	}
		        	case 'B' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "B" ist den jeweiligen Wert im Map Array auf den Wert für Spawn Blocker
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,5);
		        		tiles[i][count].setEnt(new SpawnBlocker());
		        		break;
		        	}
		        	case 'U' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "X" ist den jeweiligen Wert im Map Array auf den Wert für Weg
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,1);
		        		tiles[i][count].setEnt(new Powerup());
		        		break;
		        	}
		        	case 'D' : {	//wenn das Zeichen an Stelle i in der aktuellen Zeile der map Datei "D" ist den jeweiligen Wert im Map Array auf den Wert für Zerstörbare Wand
		        		tiles[i][count] = new Tile(Game.tileSize*i,Game.tileSize*count,Game.tileSize,Game.tileSize,7);
		        		break;
		        	}
		        	}
		        	tiles[i][count].col = i;
		        	tiles[i][count].row = count;
		        }
		        count++;
		        
		      }
		      myReader.close();
		    } catch (Exception e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		if(pacmanSpawn == null || (Main.enableMultiplayer == true && pacmanSpawn2 == null))
		{
			Scenes.setMainMenuScene();
		}
		return tiles;
		  }
	
	public static void loadMap(Pane gameScene) 
	{
		for(int i = 0; i < tiles[0].length; i++)
		{
			for(int j = 0; j < tiles.length;j++)
			{
				gameScene.getChildren().addAll(tiles[j][i]);
				if(tiles[j][i].getEnt() != null)
				{
					gameScene.getChildren().addAll(tiles[j][i].getEnt());
				}
			}
		}
		
		
	}}


