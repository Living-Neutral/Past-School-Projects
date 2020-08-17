########################################################################
# Program:Frequency				Programmer: Jeffrey McDonald
# Due Date: Dec,12 2019					Course: CS2640.03
########################################################################
# Overall Program Functional Description:
#  the program takes a string from the user and then counts the frequency of 
#  each character that appears in the string 
#  afterwards it prints back each char and the num of occurances 
#  and prints the string backwards
########################################################################
# Register usage in Main:
#	[ List your register usage here ]
# 1.$s0[user string]
#2. $s1[sorted string]
########################################################################
# Pseudocode Description:
# 1. Welcomes the user
# 2. Asks user for input and stores the input into a global variable
# 3. Go into a loop that have both the Capital and lower array
# 4. Start the loop at load both cap[i] and low[i]
#    - take alpha[i] and gointo another loop with the string
#    - increment if the alphaLow[i] or alphaCap == string[i] increment a register
#    - continue until string is done
#    - when string is done if the register >0 print out the frequency in num
#    - return to loop and go to next letter in alphabet
# 5. Print the string in reverse
#
#################################################################


.data
	.asciiz"ABCDEDF"
	#freq:	     .space 0:26 # creates an array of frequency count which is initialized at 0
	alphaCap:   .byte 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','Z'  # comparison array
	alphaLow:   .byte 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','z'  #  
	alphaSize:  .word 26 # size of the two arrays
	stringSize:  .word 32 # max amount of the string allowed
	string:      .space 32
	Welcome:     .asciiz "\nWelcome to Frequency!\n"
	UserPrompt:  .asciiz "Enter a string:"
	newLine:     .asciiz "\n"
	message1:    .asciiz "The amount of "
	message2:    .asciiz ":"
	message3:    .asciiz "\'s"

.text
.globl main
main:
	li	$v0,4          # print string
	la	$a0,Welcome # prints out the prompt to ask the user
	syscall	
        
        li	$v0,4          # print string
	la	$a0,UserPrompt # prints out the prompt to ask the user
	syscall	
        
        
        
        li	$v0,8          #read string           
        la 	$a0,string     #loads the user string from $a0 to string variable
        li      $a1,32         # allots the space to read string
        syscall                # reads byte space for string
        j	alphaInit
        
	li	$v0, 4			# We print the string for comparison
	la	$a0, string
	syscall
	
	
reverse:
  	j	reverseString
	j	exit
           
 #
 # loads up the values of both arrays to compare to string
 #                                
 alphaInit:
        la	$s0,alphaCap # loads address of array to $s0
        la      $s1,alphaLow # loads address of array to $s0
        li      $s2,1  # our index 
        #lw      $s3,alphaSize # grabs 26 so it can be the max value of the alphabet

 # loop to load the value of both alphabet arrays        
alphaLoop:
        beq $s2,26,reverse # our for loop condition
        lb  $s4,0($s0)   # loads lowercase from the alphaCap     
        lb  $s5,0($s1)   # loads lowercase from the alphaLow 
        j   	stringInit   # jumps to compare that with the string
# increments the value of the array        
alphaContinue:
        addi 	$s2,$s2,1   # increments index of both alpha arrays         
        addi 	$s0,$s0,1   # points to next value in alphaLow
        addi 	$s1,$s1,1   # points to the next value in alphaCap
        j    	alphaLoop      

	#grab the string to start loop
        #load string to traverse

stringInit:
	la	$t0,string # load the string 
	li      $t1,1      # index of string 
	lw      $t2,stringSize # loading the user string size
	
	li	$t4,0 	   # reinitilize the string freq count
	
stringLoop:	
	beq     $t1,$t2,print # checks with the string length
	lb  	$t3,($t0) #loads the char from the string
	beq 	$t3,$s4,incrementFreq # compares to alphaCap 
	beq 	$t3,$s5,incrementFreq # compares to alphaLow

stringContinue:
	addi    $t0,$t0,1 # increment to point to next array
	addi    $t1,$t1,1 # increments index
	j	stringLoop
	
incrementFreq:
	addi	$t4,$t4,1 # increment 
	j	stringContinue
# prints the frequency of each letter that appeared in the string	
print:
	beq	$t4,$zero, alphaContinue # if zero it will return back to next iteration of loop	
	li	$v0,4
	la	$a0,message1
	syscall            # print the first part of the message
	
	li	$v0,11
	lb	$a0,($s0) 
	syscall           # prints the letter it found
	
	li	$v0,4
	la	$a0,message3 
	syscall           # prints the letter it found
	
	
	li      $v0,4
	la	$a0,message2
	syscall # prints the colon of the message
	
	li	$v0,1
        move    $a0,$t4
	syscall          # loads the number of occurances found
	
	li	$v0,4
	la	$a0,newLine
	syscall # prints a newline
	
	j	alphaContinue # jumps back to the next letter in the loop 

reverseString:
	la	$t0,string # grabs string to evaluate on
	li	$t1,1 # loads index with 1 
	lw	$t2,stringSize # grabs value
# goes to the end of the loop to prepare string to print backwards
forwardLoop:
	beq	$t1,$t2,backwardsLoop # goes to the backwards loop which prints out the string backwards
	addi	$t1,$t1,1 # increases the index
	addi	$t0,$t0,1 # points to next value in string
	j	forwardLoop # jumps back to beg. of loop
# prints string backwards by starting of at the end of the 	
backwardsLoop:
	beq	$t1,$zero,exit # jumps to exit program
	lb	$t3,0($t0) # loads char from string
	
	li	$v0,11 
	move	$a0,$t3
	syscall # print char
	subi	$t0,$t0,1 # goes back to last value
	subi	$t1,$t1,1 # decrements counter
	j	backwardsLoop	

exit:
	li	$v0,10
	syscall   # exits the program
                       

              
        
     
        
