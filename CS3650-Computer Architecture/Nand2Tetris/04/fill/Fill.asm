// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.
//    PsuedoCode
//-----------------------------------------------
//    screen_address = SCREEN
//    screen_max=SCREEN+8192
//    while(TRUE){
//       
//       if(KBD==0){
//           *screen_address=0
//       }  
//       
//       else{       
//           *screen_address=-1
//       }
//
//       screen_address = screen_address+1
//
//       if(screen_address>=SCREEN+8192){
//           screen_address=SCREEN
//       }
//    }
//    
// Put your code here.

    // Memory pointer 
@SCREEN
D=A  // load the screen_address value into the d-register

@SCREENPOINTER
M=D  // put the value of the screen_address into ram[16] as this is the first variable

@8191
D=A  // loading the 8191 or 512^2/32

@SCREEN
D=D+A  // doing SCREEN+8192

@MAX_SCREEN
M=D     // MAX_SCREEN=SCREEN+8192


(STARTLOOP)
        @KBD
        D=M  // loading up what is in D

        @WHITEDRAW
        D;JEQ  // checking if (KBD==0)
  
        @BLACKDRAW
        0;JMP   // else

(BLACKDRAW)
        // setting one pixel black
        @SCREENPOINTER
        A=M // setting the new pointer 
        M=-1 // RAM[SCREENPOINTER] = -1  to set black
        @CONTINUE
        0;JMP // jump to the continue after setting the pixel black


(WHITEDRAW)
        @SCREENPOINTER
        A=M  // setting the new pointer
        M=0  // RAM[SCREENPOINTER] = 0
        @CONTINUE

(CONTINUE)
        @SCREENPOINTER
        D=M+1 // incremeting screenpointer
        M=D

        @MAX_SCREEN
        D=D-M // loading the Value for SCREEN+8191
        @STARTLOOP
        D;JNE // if(screen_address>=SCREEN+8192)
   
        @SCREEN
        D=A    // loading the beginning value of screen
        @SCREENPOINTER // loading screen pointer
        M=D // 
        @STARTLOOP // going to next loop
        0;JMP