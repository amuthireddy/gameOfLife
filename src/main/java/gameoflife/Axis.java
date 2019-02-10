package gameoflife;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;

public class Axis {

	private List<Cell> cells;
    
	public Axis() 
    {
        cells = new ArrayList<Cell>();
    }
    
    public void nextStep()
    {
        for (Cell cell : cells) {
            cell.nextStep();
        }
    }
    
    // Getter and Setter
    public List<Cell> getCells() 
    {
    	return cells;
    }
    
    public void setCells(List<Cell> cells) 
    {
        this.cells = cells;
    }
}
