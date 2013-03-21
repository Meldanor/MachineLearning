package core;

import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * This framework is written for the Winterschool 2013
 * Otto-von-Guericke University - FIN/ITI 
 * 
 * This class shows the GridWorld with its agent.
 * No further documentation.
 * 
 * @author Daniel Kottke, Georg Krempl
 */

@SuppressWarnings("serial")
public class GridPanel extends JPanel {
	private GridWorld grid = null;
	private Point agentPosition = null;
	
	public GridPanel(GridWorld grid, Point agentPos){
		this.grid = grid;
		this.agentPosition = agentPos;
	}
	
	public void setAgentsPosition(Point p){
		this.agentPosition = p;
	}
	
	public void paint(Graphics g) {
		int xGridSize = (int)(this.getWidth()/(this.grid.getWidth()+2));
		int yGridSize = (int)(this.getHeight()/(this.grid.getHeight()+2));
		
//		Draw Holes
		g.setColor(Color.black);
		for (int i = 0; i < this.grid.getMines().size(); ++i){
			g.fillRect((grid.getMines().get(i).x+1)*xGridSize, (grid.getMines().get(i).y+1)*yGridSize, xGridSize, yGridSize);
		}

//		Draw Goal
		if (grid.getGoal() != null){
			g.setColor(Color.green);
			g.fillRect((grid.getGoal().x+1)*xGridSize, (grid.getGoal().y+1)*yGridSize, xGridSize, yGridSize);
		}
		
//		Draw Grid
		g.setColor(Color.black);
		for (int xInd = 0; xInd <= this.grid.getWidth(); ++xInd){
			g.drawLine(xGridSize*(xInd+1), yGridSize, xGridSize*(xInd+1), yGridSize*(this.grid.getHeight()+1));
		}
		for (int yInd = 0; yInd <= this.grid.getHeight(); ++yInd){
			g.drawLine(xGridSize, yGridSize*(yInd+1), xGridSize*(this.grid.getWidth()+1), yGridSize*(yInd+1));
		}
		
//		Draw Agent
		g.setColor(Color.blue);
		g.fillOval((this.agentPosition.x+1)*xGridSize+(int)(xGridSize*.1), 
				(this.agentPosition.y+1)*yGridSize+(int)(yGridSize*.1),
				(int)(xGridSize*.8), (int)(yGridSize*.8));
		
	}
}