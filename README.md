# Portfolio
Various works and projects I've put together

README
Copyright 2018 Timothy Lawrence

Hub for works I've done in school or in my free time.  Mainly Java, will specify if it's non-Java.

Descriptions:

A0X:

All works starting with A0X (X being 1-6) are from my CSIS 2420 - Data Structures & Algorithms course, Spring 2018.  This course delved into creating many different programs (The A0X programs being the most comprehensive exercises) and creating them not only correctly, but as efficiently as possible, using very little processing power to achieve the greatest results.  All A0X assignments were created in a group setting with Dillon Embreus, another student in the class who I got along with well for the entire semester.  Together, we were able to create some really cool stuff.

----------------------------------------------------

A01 - Perc

Percolation assignment
    
The goal of percolation is to create a grid of squares, each square is either blocked, open, or filled.  We want to know when the filled squares have reached from the top of the grid to the bottom, as if we were pouring water down and opening up sites for it to fill into until it reaches the bottom.
     
Recommended is to first run PercolationVisualizer.java, which will create a 50x50 grid which you can then click to open up sites (Click and drag works, here, try it out!).  After you've seen how the program works visually, run the Percolation.java to really see the efficiency in action.  By default, it is set to run a 50x50 grid 10,000 times, opening sites at random until the grid percolates, then recording the mean percentage of sites required to open before percolating, and even including a standard deviation and high and low confidences of how many sites will need to be opened to percolate.

The GUI for this assignment was provided - We did NOT code the GUI, rather using it more like an API and fitting our code into it.
     
Reflection of assignment -- 
A challenging first assignment, Percolation really started to nail home what Java is all about, and how powerful it can be.

----------------------------------------------------

A02 - Deque

----------------------------------------------------

A03 - Autocomplete

Autocompletion Assignment

Much as Google autocompletes your searches, we were tasked with creating a program that will autocomplete words as you type them into the search bar.

To try it out, run the AutocompleteGUI.java and try typing a letter.  It will pull terms from the Wiktionary.txt file and show what your letters could autocomplete to.

The GUI for this assignment was provided - We did NOT code the GUI, rather using it more like an API and fitting our code into it.

Reflection of assignment -- 
This assignment was difficult for us, and we never fully finished; to this day it has bugs and issues that I have not returned to.  Functionality is only halfway there, unfortunately, but it was a fun assignment nonetheless.

----------------------------------------------------

A04 - 8Puzzle

Making an 8 Puzzle that solves itself

You've probably seen 8 puzzles or 15 puzzles before.  They're the little puzzles with one open slot in a grid that you slide the other puzzle pieces into until solves.  We had to make a program that generates an 8 puzzle from a text file and solves it (or tells whether it is unsolvable) in the shortest amount of time possible.

First, run SolverVisualizer.java.  This will open a GUI interface that shows an 8 puzzle being solved (To change what puzzle is being solved, go ahead and try changing line 133 from file5 to any of the other file variables listed directly above there).

From there, check out PuzzleChecker.java. It takes a similar input, but will tell you how quickly the program solves it in hundredths of a second time (By default it's set to puzzle3x3-31.txt, a 3x3 puzzle that takes a minimum of 31 moves to solve.  The program will output that it takes that many moves, and how quickly it solves it).

Reflection of assignment -- 
This assignment was probably the most satisfying once we got it to work!  Watching how quickly the program can work through a solution by algorithms alone is pretty incredible.

----------------------------------------------------

A05 - KdTrees

----------------------------------------------------

A06 - WordNet

