package cell;

public class LiveState implements CellState {
	
	public State getState()
	{
		return State.LIVE;
	}

	// incrementing no. of live neighbors
	public void updateNoOfLiveNeighbors(Cell currentCell) 
    {
        int temp = currentCell.getNewNoOfLiveNeighbors() + 1;
        currentCell.setNewNoOfLiveNeighbors(temp);
    }

    public void nextState(Cell currentCell) 
    {
        /* Any live cell with fewer than two live neighbors dies, as if by under-population.
         * Any live cell with two or three live neighbors lives on to the next generation.
         * Any live cell with more than three live neighbors dies, as if by over-population. */
        if (currentCell.getNoOfLiveNeighbors() < 2 || currentCell.getNoOfLiveNeighbors() > 3)  {
            currentCell.setCellState(new DeadState());
        }
    }
}
