Game made by Ednecia Sewagodimo Matlapeng
Runs on eclipse with a screen size of 1200 by 800
************************************
I implemented normal A* path finding

initially the h calculation is one point for all of the 
moves, this tends to favour diagonal moves as they get further 
but are weighted the same
*************************************
And as an advancement I used a different 

heuristic calculation, I added 10 for left or right moves as 14 
for diagonal moves. THis is in because the squareroot of 2 is 1.4.

*************************************************
The algorithm

It takes a node with the x and y positions, the h, the g and the parent node.
The constructor calculates the f. The initial node has a null parent and as nodes are explored
the current node is added as a parent for the nodes it popped.

Then I add all the children nodes to the openlist and remove the undesired ones to the closed list and 
check against both lists before adding a child.

WHen a child is found that is at the same point as the target all explorations stop and the path is found.
We use the child that found the target and add all its ancestors in order into an arraylist called pathlist
this is the list that is used by the chaser to move. This list is regulary updated at certain time intervals



********************************************************
Hope this explained it well thank you!!!!