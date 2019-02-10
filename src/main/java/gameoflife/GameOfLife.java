package gameoflife;

import java.util.Scanner;

public class GameOfLife {
	
	public static void main(String[] args) 
	{
		// Initial input
		String[] board = {"-*-","--*","***","---"};
		
		// Input number of generations
		System.out.print("Enter number of generations to display: ");
		Scanner in = new Scanner(System.in);
		int noOfGens = in.nextInt();
		in.close();
        
		Grid grid = new Grid();
        grid.initializeGrid(board);
        
        // Generate the grid and print it 'noOfGens' times
        for (int i = 0; i <= noOfGens; i++) {
        	if (i == 0) {
        		System.out.println("Initial Seed:");
        	} else {
        		System.out.println("Generation " + i + ":");
        	}
        	grid.printGrid(board);
        	board = grid.nextGen(board);
        }
	}
}