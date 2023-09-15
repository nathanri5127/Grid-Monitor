********************
*Grid Moniter
*CS 221
*
*Nathan Richardson
********************

OVERVIEW:
	This program creates a 2d array from a text file, adds nearby cells, divides the average, finds the deltas of the cells,
	and determines if the original cells are in a safe range.

INCLUDED FILES:
	GridMoniter.java - Source file
	README - This file

PROGRAM DESIGN:
	GridMoniter constructs a 2D double array taken from a text file read from a scanner that runs through each of the text file lines and parses values into
	a 2d double array. This grid is assigned to the baseGrid 2D array, and a toString method prints out the values in the baseGrid. The getBaseGrid method returns
	a copy of the original unedited grid.
	
	getSurroundingGrid iterates through the original grid and adds the nearby cells. If the grid is only one cell, that cell is multiplied by 4. Cells are summed with
	the cells above, below, and to the right. Cells on the border have their own value added where the would be added cells are missing. This is done with a for loop that
	runs through the original array and adds the sum to a new array. The function returns a copy of the sumGrid.

	getSurroundingAvgGrid uses a for loop that iterates through the sumGrid and divides each of the cells by 4 to find the average of the values, and returns a copy
	of the array. getDeltaGrid is a similar process that runs through the avgGrid and divides each cell by 2 and turns them into an absolute value. These numbers are used
	in getDangerGrid which takes the deltaGrid and adds and subtracts them with the corresponding cells in the avgGrid, creating the safe range. This is compared with cells
	in the original grid, and if cells are above or below this value, they are considered unsafe and the 2D boolean array dangerGrid assigns the corresponding cell as true,
	revealing which cells are stable.

TESTING:
	Comparisons with the SetTester file was used to make sure my program was working correctly. Any bugs found would require a step by step edit through the debugger.
	Testing included proper encapsulation, correct math and iterations through the arrays, and a toString test. Most of my arrays were index out of bounds when dealing with
	finding the sum of the nearby cells. Encapsulation was not achieved with the getBaseGrid function according to SetTester.

DISCUSSION:
	I found it fun to relearn java after a long time away from programming. More experience with 2D arrays was also nice, even if it was annoying at times. Most of the issues
	found were encapsulation and going out of bounds, which are both errors I am not used to (Most 2D arrays, out of bound errors in general are not new to me), so anything I 
	had to research pertained to those. This was a fun project to work on, and I look forward to this class.