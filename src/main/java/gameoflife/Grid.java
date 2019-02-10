package gameoflife;

import java.util.ArrayList;
import java.util.List;

import cell.Cell;
import cell.CellState.State;
import cell.LiveState;

public class Grid {
	
	private List<Axis> rows;
	
	// Initialize the grid
    public void initializeGrid(String[] board) 
    {
        rows =  new ArrayList<Axis>();
        
        for (int i = 0; i < board.length; i++) {
            Axis axis = new Axis();
            List<Cell> row = new ArrayList<Cell>();
            axis.setCells(row);
            for (int j = 0; j < board[0].length(); j++) {
                row.add(new Cell());
            }
            rows.add(axis);
        }
        addNeighbors();
        createGrid(board);
        setCellInfo();
    }
    
    public void addNeighbors()
    {
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).getCells().size(); j++) {
                Cell cell = rows.get(i).getCells().get(j);
                int maxRows = rows.size() - 1;
                int maxCols = rows.get(i).getCells().size() - 1;
                // Add observers to the neighboring 8 cells
                if (i > 0 && j > 0) {
                	cell.addObserver(rows.get(i-1).getCells().get(j-1));
                }
                if (i > 0) {
                	cell.addObserver(rows.get(i-1).getCells().get(j));
                }
                if (i > 0 && j < maxCols) {
                	cell.addObserver(rows.get(i-1).getCells().get(j+1));
                }
                if (j > 0) {
                	cell.addObserver(rows.get(i).getCells().get(j-1));
                }
                if (j < maxCols) {
                	cell.addObserver(rows.get(i).getCells().get(j+1));
                }
                if (i < maxRows && j > 0) {
                	cell.addObserver(rows.get(i+1).getCells().get(j-1));
                }
                if (i < maxRows) {
                	cell.addObserver(rows.get(i+1).getCells().get(j));
                }
                if (i < maxRows && j < maxCols) {
                	cell.addObserver(rows.get(i+1).getCells().get(j+1));
                }
            }
        }
    }
    
    // Creating the grid; setting '*' to live state
    public void createGrid(String[] board)
    {
        int i = 0, j = 0;
        for (String row : board) {
            Axis axis = rows.get(i);
            j = 0;
            for (char character : row.toCharArray()) {
                List<Cell> cells = axis.getCells();
                if (character == '*') {
                    cells.get(j).setCellState(new LiveState());
                }
                j++;
            }
            i++;
        }
    }
    
    // Setting no. of live neighbors for each cell
    public void setCellInfo()
    {
        for (Axis axis : rows) {
            for (Cell cell: axis.getCells()) {
                cell.setNoOfLiveNeighbors(cell.getNewNoOfLiveNeighbors());
            }
        }
    }
    
    public String[] nextGen(String[] board)
    { 
        Axis topRow = instantiateNewRow(0);
        Axis bottomRow = instantiateNewRow(rows.size()-1);
        List<Cell> leftColumn = instantiateNewColumn(0);
        int rightColumnIndex = rows.get(0).getCells().size()-1;
        List<Cell> rightColumn = instantiateNewColumn(rightColumnIndex);
        int index = 0;
        
        for (Axis axis : rows) {
            axis.nextStep();
        }
        
        // handling edges
        if (topRow != null) {
        	rows.add(0, topRow);
        }
        if (bottomRow != null) {
        	rows.add(bottomRow);
        }
        if (leftColumn != null) {
        	insertNewColumn(leftColumn, index);
        }
        index = rows.get(0).getCells().size();
        if (rightColumn != null) {
        	insertNewColumn(rightColumn, index);
        }
        
        String[] newBoard = gridToArray();
        initializeGrid(newBoard);
        
        return newBoard;
    }
    
    public Axis instantiateNewRow(int i)
    {
        int flag = 0, index = -1;
        Axis tempRow = rows.get(i);
        
        for (Cell cell : tempRow.getCells()) {
            index++;
            if (cell.getCellState().getState() == State.LIVE) {  
                flag++;
                if (flag == 3) {
                	break;
                }
                continue;
            }
            flag = 0;
        }
        index--;
        
        if (flag == 3) {
            return createNewRow(tempRow.getCells().size(), index);
        }
        return null;
    }
    
    private Axis createNewRow(int size, int index)
    {
        Axis newRow = new Axis();
        List<Cell> cells = new ArrayList<Cell>();
        newRow.setCells(cells);
        
        for (int i = 0; i < size; i++) {
            Cell cell = new Cell();
            cells.add(cell);
            if (i == index) {
                cell.setCellState(new LiveState());
            }
        }
        return newRow;
    }
    
    public List<Cell> instantiateNewColumn(int j)
    {
    	int flag = 0, index = -1;
    	List<Cell> tempColumn = new ArrayList<Cell>();
        for (Axis axis : rows) {
            tempColumn.add(axis.getCells().get(j));
        }
        
        for (Cell cell : tempColumn) {
            index++;
            if (cell.getCellState().getState() == State.LIVE) {  
                flag++;
                if (flag == 3) {
                	break;
                }
                continue;
            }
            flag = 0;
        }
        index--;
        
        if (flag == 3) {
            return createNewColumn(tempColumn.size(), index);
        }
        return null;
    }
    
    private List<Cell> createNewColumn(int size, int index)
    {
        List<Cell> newColumn = new ArrayList<Cell>();
        
        for (int j = 0; j < size; j++) {
            Cell cell = new Cell();
            newColumn.add(cell);
            if (j == index) {
                cell.setCellState(new LiveState());
            }
        }
        return newColumn;
    }
    
    public void insertNewColumn(List<Cell> column, int index)
    {
        int j = 0;
        for (Axis axis : rows) {
            axis.getCells().add(index, column.get(j));
            j++;
        }
    }
    
    public String[] gridToArray()
    {
        List<String> gridList = new ArrayList<String>();
        for (Axis axis : rows) { 
            gridList.add(rowToString(axis));  
        }
        return gridList.toArray(new String[0]);
    }
    
    // converting grid row to string; live and dead states to * and - respectively
    private String rowToString(Axis axis)
    {
        StringBuilder sb = new StringBuilder();
        
        for (Cell cell : axis.getCells()) {
            if (cell.getCellState().getState() == State.LIVE)  {
                sb.append('*');
            } else { 
            	sb.append('-');
            }
        }
        return sb.toString();
    }
    
    public void printGrid(String[] board)
    {
        for(String row : board) {
            System.out.println(row);
        }
    }
}
