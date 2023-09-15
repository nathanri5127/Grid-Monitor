/**
 * GridMonitor class 
 * 
 * @author nrich5690
 *
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GridMonitor implements GridMonitorInterface{
	private double[][] baseGrid;
	private double[][] sumGrid;
	private double[][] avgGrid;
	private double[][] deltaGrid;
	private boolean[][] dangerGrid;
	int col;
	int row;
	
	/**
	 * Constructor for GridMonitor Class Creates a 2d array read off input from a given text file
	 * 
	 * @param testFile - The name of the file that you are testing
	 * @return 
	 * @throws FileNotFoundException 
	 */
	
	public GridMonitor(String testFile) throws FileNotFoundException {
		Scanner text = new Scanner(new File(testFile));
		
		String[] gridLayout = text.nextLine().trim().split(" ");
		row = Integer.parseInt(gridLayout[0]);
		col = Integer.parseInt(gridLayout[1]);
				
		double[][] grid = new double[row][col];
		
		while(text.hasNextLine()) {
	         for (int i= 0; i < grid.length; i++) {
	            String[] line = text.nextLine().trim().split(" ");
	            for (int j= 0; j < line.length; j++) {
	               grid[i][j] = Double.parseDouble(line[j]);
	            }
	         }
	      }
		grid.toString();
	    baseGrid = Arrays.copyOf(grid, grid.length);
	}

	/**
	 * toString method that lists the numbers in the constructed array
	 * 
	 * @return gridString- Numbers of the 2d array
	*/
	public String toString() {
		String gridString = "";
		for (int i = 0; i < baseGrid.length; i++) {
			for(int j = 0; j < baseGrid[i].length; j++) {
				gridString += baseGrid[i][j] + " ";
			}
		}
		return gridString;
	}

	
	/**
	 * getBaseGrid Returns the unedited constructed 2d array as read from the text file
	 * 
	 * @return baseGrid- Copy of the original 2d array
	 */
	@Override
	public double[][] getBaseGrid() {
		return Arrays.copyOf(baseGrid, baseGrid.length);
	}
	
	/**
	 * getSurroundingSumGrid takes a cell from the original 2d array and takes the sum of the nearby cells
	 * 
	 * @return sumGrid- 2d array with the sums of the nearby cells
	 */
	@Override
	public double[][] getSurroundingSumGrid() {
		double[][]sumGrid = new double[row][col];
		double[][]ogGrid = Arrays.copyOf(baseGrid, baseGrid.length);
		double sum;
		if (row == 1 && col== 1) {
			sumGrid[0][0] = (ogGrid[0][0] * 4);
			return sumGrid;
		}
		for (int i = 0; i < ogGrid.length; i++) {
			for (int j = 0; j < ogGrid[i].length; j++) {
				sum = 0;
				if(i == 0) {
					if(j == 0) {
						sum = (ogGrid[i][j] + ogGrid[i+1][j] + ogGrid[i][j] + ogGrid[i][j+1]);
						sumGrid[i][j] = sum;
					}
					else if(j == (ogGrid[i].length - 1)) {
						sum = (ogGrid[i][j] + ogGrid[i+1][j] + ogGrid[i][j-1] + ogGrid[i][j]);
						sumGrid[i][j] = sum;
					}
					else {
						sum = (ogGrid[i][j-1] + ogGrid[i+1][j] + ogGrid[i][j] + ogGrid[i][j+1]);
						sumGrid[i][j] = sum;
					}
				}
				else if(i == ogGrid.length - 1) {
					if(j == 0) {
						sum = (ogGrid[i-1][j] + ogGrid[i][j] + ogGrid[i][j+1] + ogGrid[i][j]);
						sumGrid[i][j] = sum;
					}
					else if(j == (ogGrid[i].length - 1)) {
						sum = (ogGrid[i][j] + ogGrid[i-1][j] + ogGrid[i][j] + ogGrid[i][j-1]);
						sumGrid[i][j] = sum;
					}
					else {
						sum = (ogGrid[i][j-1] + ogGrid[i-1][j] + ogGrid[i][j] + ogGrid[i][j+1]);
						sumGrid[i][j] = sum;
					}
				}
				else {
					if(j == 0) {
						sum = (ogGrid[i][j] + ogGrid[i-1][j] + ogGrid[i+1][j] + ogGrid[i][j+1]);
						sumGrid[i][j] = sum;
					}
					else if(j == (ogGrid[i].length - 1)) {
						sum = (ogGrid[i][j] + ogGrid[i-1][j] + ogGrid[i+1][j] + ogGrid[i][j-1]);
						sumGrid[i][j] = sum;
					}
					else {
						sum = (ogGrid[i][j-1] + ogGrid[i-1][j] + ogGrid[i+1][j] + ogGrid[i][j+1]);
						sumGrid[i][j] = sum;
					}
				}
			}
		}
		return Arrays.copyOf(sumGrid, sumGrid.length);
	}
	
	/**
	 * getSurroundingAvgGrid runs through each of the cells in the sumGrid and divides them by four to find the average
	 * 
	 * @return avgGrid- 2d array with the average of the sumGrid cells
	 */
	@Override
	public double[][] getSurroundingAvgGrid() {
		avgGrid = getSurroundingSumGrid();
		for (int i = 0; i < avgGrid.length; i++) {
			for (int j = 0; j < avgGrid[i].length; j++) {
				double avg = (avgGrid[i][j] / 4);
				avgGrid[i][j] = avg;
			}
		}
		return Arrays.copyOf(avgGrid, avgGrid.length);
	}
	
	/**
	 * getDeltaGrid runs through the avgGrid dividing each cell by two to find the delta of each cell
	 * 
	 * @return deltaGrid- 2d array with the half of each cell in the avgGrid
	 */
	@Override
	public double[][] getDeltaGrid() {
		deltaGrid = getSurroundingAvgGrid();
		for (int i = 0; i < deltaGrid.length; i++) {
			for (int j = 0; j < deltaGrid[i].length; j++) {
				double delta = (Math.abs(deltaGrid[i][j]) / 2);
				deltaGrid[i][j] = delta;
			}
		}
		return Arrays.copyOf(deltaGrid, deltaGrid.length);
	}
	
	/**
	 * getDangerGrid runs through the deltaGrid and subtracts and adds the cells with the avgGrid and compares the baseGrid
	 * values to see if the value is dangerous, and sets the value in deltaGrid to either true or false
	 * 
	 * @return dangerGrid- 2d array with booleans that are false if corresponding baseGrid cell is "safe" and true if they are "unstable" 
	 */
	@Override
	public boolean[][] getDangerGrid() {
		deltaGrid = getDeltaGrid();
		avgGrid = getSurroundingAvgGrid();
		baseGrid = getBaseGrid();
		dangerGrid = new boolean[row][col];
		for(int i = 0; i < deltaGrid.length; i++) {
			for(int j = 0; j < deltaGrid[i].length; j++) {
				double lowDelta = (avgGrid[i][j] - deltaGrid[i][j]);
				double highDelta = (avgGrid[i][j] + deltaGrid[i][j]);
				if(baseGrid[i][j] > highDelta || baseGrid[i][j] < lowDelta) {
					dangerGrid[i][j] = true;
				} else {
					dangerGrid[i][j] = false;
				}
			}
		}
		return Arrays.copyOf(dangerGrid, dangerGrid.length);
	}

}
