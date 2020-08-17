########################################################################
# Program: Input/Output				Programmer: Jeffrey McDonald
# Due Date: Dec 12,2019					Course: CS2640.03
########################################################################
# Overall Program Functional Description:
#	To have the program take 20 integers and print them accordingly
# 1. Print them line by line (done)
# 2. Have the user pick a number and have it be printed up to that number
# 3. Print it out on the same line by space (done)
########################################################################
# 	psuedocode 
# 1. Start with asking how each integer value for 20 times. Goes through the loop.
# 2. Goes through three loops that print out the info in diff. ways
# 3. The first loop prints out one element on one line and goes to the next loop
# 4. The second loop prints all of the elements on one line
# 5. The third loop first asks how much to print out on one line and the prints that amount for 20 times
# 6. ends program
#
#####################################################################
# Register usage in Main:
#	[ List your register usage here ]
#       %t1[base pointer]
#       $t2[loop counter]
#       $t3[size counter]
#       $s1 register to load the index
#       $t7 index of the 3rd func to print the lines
#       $t9 laods counter for loop
########################################################################
                	.data
#	[ Enter your global data requirements here! ]
arr:            .space 80
prompt:         .asciiz "\n Hello world "
Input:          .asciiz "\n Enter an integer:"
line:           .asciiz"\n Enter the amount to print:"
warning:        .asciiz"\n It needs to be a number between 1 and 20"
newLine:        .asciiz "\n"
space:          .asciiz " " 
size:           .word 20
		.globl main
		.text
main:
                li   $v0,4
                la   $a0,prompt 
                syscall          # prints out prompt to load into array.                      

##
# initializes the values for the input loop
##
firstinit:                
                lw $t3, size # Load the size of the array
                la $t1, arr # Set the pointer to the first element
                li $t2, 0 # Initialize the loop counter
                
Inputloop:      beq $t2, $t3, secinit # ends the loop
                lw $a0, 0($t1) # Fetch the (next) value from array
                move $t4,$a0   # moves the value from $a0 to $t4 for calculation
                li $v0,4           
                la $a0,Input       # asks for input
                syscall
                li $v0,5           # stores it into $v0
                syscall
                sw $v0, 0($t1)      # stores it back into the array 
                addi $t2, $t2, 1 # Increment the loop counter
                addi $t1, $t1, 4 # Point to the next element in the array
                j    Inputloop
                
#
# Initializes the array for the 2nd time 
#                
secinit:        
                lw $t3, size # Load the size of the array
                la $t1, arr # Set the pointer to the first element
                li $t2, 0 # Initialize the loop counter
                
##
# Reads the values from the array and 
##              
Readloop:       beq $t2, $t3, thrdinit # ends the loop
                lw $s1, 0($t1) # Fetch the (next) value from array               
                li $v0,1         # 
                move $a0,$s1     # prints the value in the index
                syscall          # 
      
                li $v0,4          # prints a space for each line
                la $a0,newLine
                syscall
                       
                addi $t2, $t2, 1 # Increment the loop counter
                addi $t1, $t1, 4 # Point to the next element in the array
                j    Readloop # jumps back to the loop
                                     
thrdinit:        
                lw $t3, size # Load the size of the array
                la $t1, arr # Set the pointer to the first element
                li $t2, 0 # Initialize the loop counter
                
                
lineLoop:       beq $t2, $t3, forthinit # ends the loop
                lw $s1, 0($t1) # Fetch the (next) value from array               
                li $v0,1         # 
                move $a0,$s1     # prints the value in the index
                syscall          # 
                
                li $v0,4
                la $a0,space
                syscall          #  prints out the a space in between numbers
      
                addi $t2, $t2, 1 # Increment the loop counter
                addi $t1, $t1, 4 # Point to the next element in the array
                j    lineLoop
##
# Asks user how much to print how much integers
##
forthinit:      
                li $v0,4
                la $a0,warning
                syscall      # tells the user to stay in the bound
                
                li $v0,4
                la $a0,line # asks how much to print
                syscall 
                
                li $v0,5     # reads the response
                syscall
                
                bgt  $v0,20,forthinit # checks if n>20
                blt  $v0,1,forthinit # checks if n<0
                
                # values for outer loop to print for the 20 lines
                li $t9,20 # loads for the 20 lines
                li $t7,0  # initilizes the counter at 0 for printing everyline
                

                move $t3,$v0  # adds the input value
                # values in the inner loop to print to how many values wanted
# initilizes the array every line to print out the amount of user specified integers
fifthinit:          
                beq	$t7,$t9,end
                addi	$t7,$t7,1
                la $t1, arr # Set the pointer to the first element
                li $t2, 0 # Initialize the loop counter
                li	$v0,4
                la	$a0,newLine # prints a newline after the first print
                syscall
                         
###
# nLoop prints n elements depending on the user input
###
nLoop:                          
                beq $t2, $t3, fifthinit
                
                lw $s1, 0($t1) # Fetch the (next) value from array               
                li $v0,1         # 
                move $a0,$s1     # prints the value in the index
                syscall                           
                
                li $v0,4
                la $a0,space
                syscall          #  prints out the a space in between numbers  
                              
                addi $t2, $t2, 1 # Increment the loop counter
                addi $t1, $t1, 4 # Point to the next element in the array
                j    nLoop     
end:               
                li $v0,10
                syscall      # system exit call           
	
