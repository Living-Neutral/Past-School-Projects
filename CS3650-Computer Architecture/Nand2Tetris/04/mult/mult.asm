// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//    i=1 
//    n=R1
//    product=R0
//    LOOP:
//         if i > n goto STOP
//         product=product+R0
//         
//         i = i+1     
//         goto LOOP
//    STOP:
//         R2=product
//
// Put your code here.
       @R1
       D=M  // D=R1
       @n 
       M=D  // n=R1

       @i
       M=1 // i=1
                               
       @product
       M=0    // product = 0
       
    (LOOP)
       @i
       D=M  //D=i=0
       @n   // 
       D=D-M //n=D-R1 or 2 
       @STOP
       D;JGT //if i>n goto STOP
       
       @product
       D=M
       @R0
       D=D+M
       @product
       M=D  // product = product +R0
       @i
       M=M+1  // i=i+1
       @LOOP
       0;JMP

    (STOP)
       @product
       D=M
       @R2
       M=D   // RAM[2] = sum

    (END)
       @END
       0;JMP

          
     