package cell;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import cell.CellState.State;

public class CellTest {

	Cell cell;
	List<Cell> neighbors;
    Integer countNeighbors;
    
    @Before
    public void setUp() 
    {
        this.cell = new Cell();
        this.neighbors = new ArrayList<Cell>();
        this.countNeighbors = 0;
        for (int i = 0; i < 9; i++) {
            if (i != 4) {
                Cell neighbor = new Cell();
                neighbor.addObserver(cell);
                double rand = new Random().nextInt(100);
                if(rand %2 == 0) {
                    neighbor.setCellState(new LiveState());
                    countNeighbors++;
                }
                neighbors.add(neighbor);
            }
        }
    }
    
    @Test
    public void noOfLiveNeighbors()
    {
        assertEquals(countNeighbors, cell.getNewNoOfLiveNeighbors());
    }
	
    @Test
    public void noOfLiveNeighboursAfterKillingOneNeighbor()
    {
        for(Cell neighbor : neighbors) {
            if(neighbor.getCellState().getState() == State.LIVE) {
                neighbor.setCellState(new DeadState());
                countNeighbors--;
                break;
            }
        }
        assertEquals(countNeighbors, cell.getNewNoOfLiveNeighbors());
    }
    
	@Test
	public void cellDiesDueToUnderPopulation() 
	{
        cell.setCellState(new LiveState());
        cell.setNewNoOfLiveNeighbors(1);
        cell.nextStep();
        assertEquals(State.DEAD, cell.getCellState().getState());
	}
	
	@Test
	public void cellLivesOnToNextGeneration() 
	{
		cell.setCellState(new LiveState());
        cell.setNoOfLiveNeighbors(2);
        cell.nextStep();
        assertEquals(State.LIVE, cell.getCellState().getState());
        
        cell.setCellState(new LiveState());
        cell.setNoOfLiveNeighbors(3);
        cell.nextStep();
        assertEquals(State.LIVE, cell.getCellState().getState());
	}
	
	@Test
	public void cellDiesDueToOverPopulation() 
	{
		cell.setCellState(new LiveState());
        cell.setNewNoOfLiveNeighbors(4);
        cell.nextStep();
        assertEquals(State.DEAD, cell.getCellState().getState());
	}
	
	@Test
	public void cellGetsReproduced() 
	{
		cell.setCellState(new DeadState());
        cell.setNoOfLiveNeighbors(3);
        cell.nextStep();
        assertEquals(State.LIVE, cell.getCellState().getState());
	}
}
