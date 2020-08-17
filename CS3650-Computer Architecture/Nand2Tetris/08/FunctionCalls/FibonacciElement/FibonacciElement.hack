( )	//push constant 4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
	//call Main.fibonacci 1   // computes the 4'th fibonacci element
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
(constant)
(constant)
( )	//push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
	//push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
	//lt                     // checks if n<2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
	//push argument 0        
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
	//push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
	//push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
	//sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1  // computes fib(n-2)
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
	//push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
	//sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//call Main.fibonacci 1  // computes fib(n-1)
@SP
AM=M-1
D=M
A=A-1
M=M-D
	//add                    // returns fib(n-1) + fib(n-2)
@SP
AM=M-1
D=M
A=A-1
M=M-D