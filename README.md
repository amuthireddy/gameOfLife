# gameOfLife

## Conway's Game of Life
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead, or "populated" or "unpopulated". Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.

2. Any live cell with two or three live neighbours lives on to the next generation.

3. Any live cell with more than three live neighbours dies, as if by overpopulation.

4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.


## Instructions to run the code
In the target/ directory, there's a .jar file that was built using maven. To run it from commandline:

      java -jar GameOfLife-0.0.1-SNAPSHOT.jar
      
To run the tests (in the directory that contains pom.xml):

      mvn test
      
This is assuming Java and Maven are installed and setup on your local machine. 

There's an initial seed that gets passed to GameOfLife.java and can be modified before running the code. After the initial seed, number of generations to display is a user-input value. 
