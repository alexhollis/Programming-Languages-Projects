'use strict';
/*global require, clearInterval, setInterval*/
var Drone = require('drone'),
    blocks = require('blocks'),
	MAX_PHASE = 5;

/********************************************************************
* File: SierpenskiCarbet.js
* Author(s): Brian Mauldin, Alex Hollis, Ronnie Brewer, Supriya Thapa
* Date Modified: 09/12/2016
* 
* Description: sierpenskiCarpet method builds a sierpenskiCarpet in ScriptCraft with size and depth
* dictated by the number of recursions (numPhases) the user wants to run. 
* 
* Example Usage: /js sierpenskiCarpet(2, blocks.glowstone)
*                /js SierpenskiCarpet(2, blocks.iron)
*                                   .
*                                   .
*******/                                  .

/**
* sierpenskiCarpet method builds a sierpenskiCarpet in ScriptCraft with size and depth
* dictated by the number of recursions (numPhases) the user wants to run. You can also choose
* which type of block you want it to by built with.
* @param numPhases is the number of iterations to run of the Sierpenski Carpet recursion. The
* higher the value the larger the square and the more recursion are run on that square.
* @param blockType is the type of block to build the carpet with. See ScriptCraft "blocks" object
* for more info.
*/
function sierpenskiCarpet (numPhases, blockType)
{
	if (numPhases > MAX_PHASE)
	{
		echo (self, "INPUT ERROR: The max number of phases is set to " + MAX_PHASE + ". Please choose a lower value.");
		return;
	}
	if (numPhases < 1)
	{
		echo (self, "INPUT ERROR: numPhases must be > 0 and < " + MAX_PHASE);
		return;
	}
	if (numPhases == "undefined" || blockType == "undefined")
	{
		echo (self, "INPUT ERROR: Please enter valid numPhases value or (1-" + MAX_PHASE + ") or block type value");
		return;
	}
	var carpetSize = Math.pow(3, numPhases); // Legth and width of square
	var localCoordinates = { x: 0, y: 0}; // local coorinate system used for the square
	var placeBlock = false; // boolean to capture whether a block should be placed at the given coordinates
	var scriptCraftDrone = new Drone(self).up((carpetSize)); // Sprictcraft drone object used to build the structure
	for (var i = 0; i < carpetSize; i++)
	{
		for (var j = 0; j < carpetSize; j++)
		{
			placeBlock = isSierpinskiCarpetBlockFilled(localCoordinates.x, localCoordinates.y);
			if (placeBlock)
				scriptCraftDrone.box(blockType);
			else
				scriptCraftDrone.box(blocks.air);
			if (j == (carpetSize - 1))
				break;
			if ((i+1)%2 == 0)
			{
				scriptCraftDrone.left();
				localCoordinates.x = localCoordinates.x - 1;
			}
			else
			{
				scriptCraftDrone.right();
				localCoordinates.x = localCoordinates.x + 1;
			}
		}
		scriptCraftDrone.down();
		localCoordinates.y = localCoordinates.y + 1;
	}
}





/**
* The algorithm below was taken from the Sierpenski Carpet Wikipedia page and was modified slightly 
* to work for the ScriptCraft adaptation. We in no way take any credit for the work behind it. 
* Source: https://en.wikipedia.org/wiki/Sierpinski_carpet
*
* Decides if a point at a specific location is filled or not.  This works by iteration first checking if
* the pixel is unfilled in successively larger squares or cannot be in the center of any larger square.
* @param x is the x coordinate of the point being checked with zero being the first pixel
* @param y is the y coordinate of the point being checked with zero being the first pixel
* @return 1 if it is to be filled or 0 if it is open
*/
function isSierpinskiCarpetBlockFilled (x, y)
{
    while(x>0 || y>0) // when either of these reaches zero the pixel is determined to be on the edge 
                               // at that square level and must be filled
    {
        if(x%3==1 && y%3==1) //checks if the pixel is in the center for the current square level
            return false;
        x = Math.floor((x/3)); //x and y are decremented to check the next larger square level
        y = Math.floor((y/3));
    }
    return true; // if all possible square levels are checked and the pixel is not determined 
                   // to be open it must be filled
}

Drone.extend( sierpenskiCarpet );