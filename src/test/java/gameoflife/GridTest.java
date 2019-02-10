package gameoflife;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest {

	Grid grid;
	
	@Before
    public void setUp() 
    {
		grid = new Grid();
    }
	
	@Test
	public void evaluateBlinkerPattern() 
	{
		String[] input = {"-*-","-*-","-*-"};
        String[] expectedOutput = {"---","***","---"};
        grid.initializeGrid(input);
        System.out.println("Initial Seed:");
        grid.printGrid(input);
        
        input = grid.nextGen(input);
        System.out.println("Next Generation:");
        grid.printGrid(input);
        assertArrayEquals(expectedOutput, grid.gridToArray());
	}
	
	@Test
	public void addingNewRows()
	{
		String[] input = {"-***","***-"};
        String[] expectedOutput = {"--*-","*--*","*--*","-*--"};
        grid.initializeGrid(input);
        System.out.println("\nInitial Seed:");
        grid.printGrid(input);
        
        input = grid.nextGen(input);
        System.out.println("Next Generation:");
        grid.printGrid(input);
        assertArrayEquals(expectedOutput, grid.gridToArray());
	}
	
	@Test
	public void addingNewColumns()
	{
		String[] input = {"*-","**","**","-*"};
        String[] expectedOutput = {"-**-","*---","---*","-**-"};
        grid.initializeGrid(input);
        System.out.println("\nInitial Seed:");
        grid.printGrid(input);
        
        input = grid.nextGen(input);
        System.out.println("Next Generation:");
        grid.printGrid(input);
        assertArrayEquals(expectedOutput, grid.gridToArray());
	}

}
