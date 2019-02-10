package cell;

public class DeadState implements CellState {
	
	public State getState() 
	{
		return State.DEAD;
	}
	
    // decrementing no. of live neighbors; if any
	public void updateNoOfLiveNeighbors(Cell currentCell) 
    {
        int temp = currentCell.getNewNoOfLiveNeighbors();
        if (temp > 0) {
        	currentCell.setNewNoOfLiveNeighbors(--temp);
        }
    }

    public void nextState(Cell currentCell) 
    {
        /* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction. */
        if (currentCell.getNoOfLiveNeighbors() == 3) {
            currentCell.setCellState(new LiveState());
        }
    }
}
