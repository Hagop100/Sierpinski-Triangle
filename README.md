# Sierpinski-Triangle
Programming Assignment for Algorithms Course Fall 2020

Important Note:
  This course was extremely strict and did not allow for any collaboration or research for any of the programming assignments. All programming assignments were to be done entirely on our own and the ONLY places to receive help were from the textbook and the lectures. There was zero assistance from the professor, lab professor, or any tutor in the school. Any collaboration between students or online research was considered cheating and if discovered, would result in a fail for the class and an academic dishonesty report to the dean. I mention this as I STRICTLY followed these rules and the entirety of this assignment was completed by me and took 3 weeks total to accomplish. It took this long because I spent an entire 3 weeks sitting at my desk thinking and only thinking. The textbook only proved to be useful for half of the assignment. The other half was a unique solution completely generated by me. I will now describe how I designed the solution for this problem.
  
Solution:
  If you read the assignment prompt pdf, then you will understand that this problem has two parts. First, is drawing the fractal itself. We will discuss this section later. For now we will focus on the second half of the problem which is finding if the line segment provided in the professor's test case will intersect with the fractal that is drawn by our program. There was a reasonably simple solution for this part as the textbook provided an algorithm that did this. Two line segments will intersect if one of these properties holds true: Either line 1 straddles line 2, or line 1 has a point that directly falls on line 2. We will check if the line straddles or has a point that is directly on line 2 through the use of cross products which give us the orientation of a line. The mathematics and pseudo code is further described in the textbook (Introduction to Algorithms 3rd edition pg 1015-1019). Our remaining issue now was that we needed to draw the fractal to obtain line segment 1 that will be compared with line segment 2. This proved to be the most difficult portion of the assignment as none of the lectures or the textbook provided any guidance on how to do this. Furthermore, I would like to mention that this was only my second semester of my computer science degree and I did not have much experience with recursion or anything like this before. Initially, I imagined the solution would be a recursive one. In fact, in the assignment prompt, you can see clearly detailed instructions for how to draw the fractal step by step. However, I did not understand how I was meant to represent this in code. It would take me three weeks of inordinate pondering to generate a solution. Finally, I noticed a pattern in the fractal provided in the example images in the prompt. Using this pattern, I was able to entirely circumvent the trigonometry delineated in the instructions to draw the fractal. If you take a look at the shape n=1, it begins by going up-right, then right, then down right. If you look at n=2 you will notice that the first three steps go right, up-right, and up-left. If you continue to look through the shapes you will notice that each step of the previous shape will directly map exactly one step for the current shape. For example, up-right will always map to right for the next shape. Once I realized this, I made a map, using integers to represent the directions of the lines, to figure out how the next shape will be drawn. However, this mapping will only work for the left portion of the shape (each has a left, middle, and right portion). The middle portion is actually the previous shape itself and requires no mapping, while the right portion is a precise mirror of the left. And we have now solved the problem by simply recognizing a pattern in the fractal. I would go on to submit the assignment and receive full credit as I passed all the test cases.
