package cell;

public interface CellState {
	
	public enum State 
	{
		LIVE, DEAD
	}
	
	// gets the state of the cell - LIVE or DEAD
	public State getState();
	
	// updates no. of live neighbors depending on the state of the currentCell
	public void updateNoOfLiveNeighbors(Cell currentCell);
	
	// determines the next state of the currentCell
    public void nextState(Cell currentCell);
}
