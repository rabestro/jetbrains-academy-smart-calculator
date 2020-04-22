# JetBrains Academy Project: Smart Calculator 

## About 
Calculators are a very helpful tool that we all use on a regular basis. Why not create one yourself, and make it really special? In this project, you will write a calculator that not only adds, subtracts, and multiplies, but is also smart enough to remember your previous calculations.

## Learning outcomes
Apart from writing a useful program (everyone uses calculators!), you will learn a lot about arrays, stacks, strings, and queues. You will also get closer experience with 2 important data structures: the stack and the queue. You will also get a closer experience with BigInteger class that allows storing large volumes of data with precision for geo-data or physical quantities.

### Work on project. Stage 6/8: Variables

At this stage, your program should support variables. We suppose that the name of a variable (identifier) can contain only Latin letters. The case is also important; for example, **n** is not the same as **N**. The value can be an integer number or a value of another variable.

Use Map to support variables.

The assignment statement may look like the following:

```
n = 3
m=4
a  =   5
b = a
v=   7
n =9
```
A variable can have a name consisting of more than one letter.
```
count = 10
```
To print the value of a variable you should just type its name.
```
N = 5
N
5
```
It should be possible to set a new value to an existing variable.
```
a = 1
a = 2
a = 3
a
3
```
If an identifier or value of a variable is invalid, the program must print a message like the one below.
```
a1 = 8
Invalid identifier
n = a2a
Invalid assignment
a = 7 = 8
Invalid assignment
```
If a variable is not declared yet, the program should print *"Unknown variable"*.
```
a = 8
b = c
Unknown variable
e
Unknown variable
```
Handle as many incorrect inputs as possible. The program must never throw the *NumberFormatException* or any other exception.

It is important to note, all variables must store their values between calculations of different expressions.

#### Input/Output example

```
a  =  3
b= 4
c =5
a + b - c
2
b - c + 4 - a
0
a = 800
a + b + c
809
BIG = 9000
BIG
9000
big
Unknown variable
/exit
Bye!
```
The program should not stop until the user enters the **/exit** command.


### Work on project. Stage 7/8: I’ve got the power 

At this stage, your program should support for multiplication *, integer division / and parentheses (...). They have a higher priority than addition + and subtraction -. Do not forget about variables; they, and the unary minus operator, should still work. Modify the result of the /help command to explain all possible operators.

Here is an example of an expression that contains all possible operations:
```
3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)
```
The result is 121.

A general expression can contain many parentheses and operations with different priorities. It is difficult to calculate such expressions if you do not use special methods. Fortunately, there is a fairly effective and universal solution, using a stack, to calculate the most general expressions.

#### Input/Output example
```
8 * 3 + 12 * (4 - 2)
48
2 - 2 + 3
3
4 * (2 + 3
Invalid expression
-10
-10
a=4
b=5
c=6
a*2+b*3+c*(2+3)
53
1 +++ 2 * 3 -- 4
11
3 *** 5
Invalid expression
4+3)
Invalid expression
```

The program should not stop until the user enters the **/exit** command.

Note that a sequence of + (like +++ or +++++) is an admissible operator that should be interpreted as a single plus. A sequence of - (like -- or ---) is also an admissible operator and its meaning depends on the length. If a user enters a sequence of * or /, the program must print a message that the expression is invalid.

**As a bonus**, you may add the power operator ^ that has higher priority than * and /.
```
2^2
4
2*2^3
16
```

### Work on project. Stage 8/8: Very big 

At this stage, your program must support arithmetic operations (+, -, *, /) with very large numbers as well as parentheses to change the priority within an expression.

There are two ways to solve it. As an easy way, you may use the standard class for working with large numbers, just correctly apply it to your solution. If you want to practice algorithms, you may develop your own class for large numbers and implement algorithms for the listed arithmetic operations.

#### Input/Output example
```
112234567890 + 112234567890 * (10000000999 - 999)
1122345679012234567890
a = 800000000000000000000000
b = 100000000000000000000000
a + b
900000000000000000000000
/exit
Bye!
```
The program should not stop until the user enters the **/exit** command.

