package core;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This framework is written for the Winterschool 2013 Otto-von-Guericke
 * University - FIN/ITI
 * 
 * This class represents a GridWorld with a specified width and height, a goal
 * and a startPosition. Furthermore some additional mines can be added.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

public class GridWorld {

	private int width = 1;
	private int height = 1;
	private Point goal = null;
	private ArrayList<Point> startPosition = new ArrayList<Point>();
	private ArrayList<Point> mines = new ArrayList<Point>();
	private int counter = 0;

	/**
	 * Constructor creates a GridWorld with a given width and height.
	 * 
	 * @param width
	 *            Integer
	 * @param height
	 *            Integer
	 */
	public GridWorld(int width, int height) {
		if (width > 0 && height > 0) {
			this.width = width;
			this.height = height;
		} else {
			throw new IllegalArgumentException(
					"width and height must be greater than zero");
		}
	}

	/**
	 * Constructor creates a GridWorld with a given width, height and a goal. A
	 * goal is the Point where the agent succeed by reaching it.
	 * 
	 * @param width
	 *            Integer
	 * @param height
	 *            Integer
	 * @param goal
	 *            Point
	 */
	public GridWorld(int width, int height, Point goal) {
		this(width, height);

		if (goal.x < this.width && goal.x >= 0 && goal.y < this.height
				&& goal.y >= 0)
			this.goal = goal;
		else
			throw new IllegalArgumentException("defined goal not in grid");
	}

	/**
	 * Constructor creates a GridWorld with a path of a gridWorldFile. This
	 * consists of the grid's dimensions in the first row and symbols afterwards
	 * (.-empty, O-mine, X-goal) like: 
	 * 5,2\nO   O\nX O    
	 * 
	 * @param path
	 *            Path of the GridWorld-File
	 */
	public GridWorld(String path) {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String firstLine = reader.readLine();
			String[] firstLineTupel = firstLine.split(" ");
			
			this.width = strToInt(firstLineTupel[0]);
			this.height = strToInt(firstLineTupel[1]);

			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null){
				// search for mines
				int j = line.indexOf('O');
				while (j >= 0) {
					addMine(new Point(j,i));
					
				    j = line.indexOf('O', j + 1);
				}
				// search for the goal
				j = line.indexOf('X');
				if (j >= 0){
					this.goal = new Point(j,i);
				}
				++i;
			}
			reader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		this(width, height);
//
//		if (goal.x < this.width && goal.x >= 0 && goal.y < this.height
//				&& goal.y >= 0)
//			this.goal = goal;
//		else
//			throw new IllegalArgumentException("defined goal not in grid");
	}

	/**
	 * Adds a mine at a given Point
	 * 
	 * @param mine
	 *            Point of the mine to add
	 */
	public void addMine(Point mine) {
		if (isGoal(mine) || !isNotMine(mine))
			System.out
					.println("Warning: mine coordinates not inside grid -> ignored");
		else
			this.mines.add(mine);
	}

	/**
	 * Sets the start Position of the agent to startPosition. If Point is not
	 * set, a random Point is chosen.
	 * 
	 * @param startPosition
	 *            Point of start position.
	 */
	public void setStartPosition(ArrayList<Point> startPosition) {
		this.counter = 0;
		this.startPosition = startPosition;
	}

	public void addStartPosition(Point startPosition) {
		if (isNotMine(startPosition))
			this.startPosition.add(startPosition);
		else
			throw new IllegalArgumentException(
					"defined startPosition not in grid");
	}

	public ArrayList<Point> generateRandomStartPositions(int n) {
		ArrayList<Point> res = new ArrayList<Point>();
		for (int i = 0; i < n; ++i) {
			res.add(getRandomValidPoint());
		}
		return res;
	}

	/**
	 * Returns true, if this Point is not a mine.
	 * 
	 * @param p
	 *            Requested Point in the GridWorld
	 * @return Boolean True if p is not a mine
	 */
	public boolean isNotMine(Point p) {
		if (p.x < 0 || p.y < 0 || p.x >= this.width || p.y >= this.height)
			return false;
		for (Point mine : this.mines) {
			if (mine.x == p.x && mine.y == p.y)
				return false;
		}
		return true;
	}

	/**
	 * Returns true, if Point the the goal's position
	 * 
	 * @param p
	 *            Requested Point
	 * @return Boolean True if p is at goal's position
	 */
	public boolean isGoal(Point p) {
		if (goal == null)
			return false;
		else if (goal.x == p.x && goal.y == p.y)
			return true;
		else
			return false;
	}

	/**
	 * Returns the start position of an agent in this GridWorld. If no start
	 * position is specified, the Point will be chosen randomly.
	 * 
	 * @return
	 */
	public Point getStartPosition() {
		if (this.startPosition.size() > 0) {
			Point p = (Point) this.startPosition.get(
					counter % startPosition.size()).clone();
			++counter;
			return p;
		} else {
			return getRandomValidPoint();
		}
	}

	/**
	 * Returns a valid randomly chosen Point
	 * 
	 * @return
	 */
	private Point getRandomValidPoint() {
		Point p = new Point(0, 0);
		do {
			int randX = (int) (Math.random() * this.getWidth());
			int randY = (int) (Math.random() * this.getHeight());
			p = new Point(randX, randY);
		} while (!this.isNotMine(p));
		return p;
	}

	/**
	 * Gets Height
	 * 
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets Width
	 * 
	 * @return
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Gets Goal
	 * 
	 * @return
	 */
	public Point getGoal() {
		return this.goal;
	}

	/**
	 * Gets List of mines
	 * 
	 * @return
	 */
	public ArrayList<Point> getMines() {
		return this.mines;
	}

	/**
	 * Some example GridWorlds for your own testing.
	 */
	public static GridWorld createSRWorld() {
		GridWorld gridWorld = new GridWorld(8, 8);
		gridWorld.addMine(new Point(2, 2));
		gridWorld.addMine(new Point(3, 2));
		gridWorld.addMine(new Point(4, 2));
		gridWorld.addMine(new Point(5, 2));
		gridWorld.addMine(new Point(2, 3));
		gridWorld.addMine(new Point(5, 3));
		gridWorld.addMine(new Point(5, 4));
		gridWorld.addStartPosition(new Point(1, 3));
		return gridWorld;
	}

	public static GridWorld createReWorld() {
		GridWorld gridWorld = new GridWorld(8, 8, new Point(7, 5));
		gridWorld.addMine(new Point(2, 3));
		gridWorld.addMine(new Point(3, 2));
		gridWorld.addMine(new Point(4, 1));
		return gridWorld;
	}
	
	private static int strToInt(String str){
		char[] chararray = str.toCharArray();
		int result=0;
		short vorzeichen = 1;
		int i=0;
		//Vorzeichenbehandlung
		if (chararray[i] == '-'){
			vorzeichen = -1;
			i++;
		}
		while(i<str.length()){
			result = (int)(chararray[i]-48)+result*10;
			i++;
		}
		result *= vorzeichen;
		return result;
	}

}
