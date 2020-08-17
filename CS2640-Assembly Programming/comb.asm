########################################################################
# Program: chuck (Chuck-A-Luck)				Programmer: Jeffrey McDonald
# Due Date: Dec 12, 2019					Course: CS2640.03
########################################################################
# Overall Program Functional Description:
#	The program will recurisvely calculate a combination for a given n and r value
#
########################################################################
# Register usage in Main:
#	[ List your register usage here ]
#
########################################################################
# Pseudocode Description:
# 1. Asks user for a value n
# 2. Asks user for a value r
#    - asks for a new value r if either bigger than n or less than 0
# 
#######################################################################
.data
welcome:	.asciiz"\n Welcome to combination!"	
userN:		.asciiz"\n Enter a value for n:"
userR:		.asciiz"\n Enter a value for r:"
warningN:	.asciiz"\n Sorry but the n value must be above zero \n Please try again!\n"
warningR:	.asciiz"\n Sorry but the r value must be above zero and above n. \n Please try again!\n"
comboPrint:	.asciiz"\n The combonation is "
.globl main

.text
###################################
# main function
# 1. Asks user for a n value
#     - will enter a loop to make sure the n is in range of n>=0 
# 2. Asks user for a r value 
#     - enter a loop to make sure the n is greater than 0 and less than n
# 3. calls comb function
#
####################################
# register usage
# 1. $t0 [n value]
# 2. $t1 [r value]
# 3. $t3 [soluction of the Comb(n-1,r) call]
# 4. $t9 register to print the value 
# 5  $a0 loads value for n
# 6. $a1 laods value for r
###############################
##
# programming overlook forgot to check bounds on N value so if it ends up negative then it might cause and endless loop as it will
# keep comparing to r 
# make sure value of n is above 0
main:
	li	$v0,4
	la	$a0,welcome
	syscall			# prints the welcome message
	
	li	$v0,4
	la	$a0,userN
	syscall			# prints the prompt for the n value
	
	li	$v0,5
	syscall			# reads the value for the n value
	move	$t0,$v0		# moves the n value to $s0 for storage	
	
	blt	$t0,$zero,retryN # chckes if the n value is above zero
##
# retrives value for r
##
getR:	
	li	$v0,4
	la	$a0,userR
	syscall      	 	# prints the prompt for the r value
	
	li	$v0,5
	syscall			# reads the value for the r value
	move	$t1,$v0		# moves the r value to $s1 for storage
#
# checks if r<n and r>0
#		
chckR:	
	bgt	$t1,$t0,retryR		# checks if value of r>n jumps if true 
	blt	$t1,$zero,retryR       # checks the value r>=0	
	j	loadNR	               # junmps to label loadNR to load those values into the argument
	
##
# asks for the r again if it failed chckR
##	
retryN:
	li	$v0,4
	la	$a0,warningN
	syscall		        # prints the warning to keep value in bounds	
	
	li	$v0,4
	la	$a0,userN
	syscall      	 	# prints the prompt for the r value
	
	li	$v0,5
	syscall			# reads the value for the r value
	move	$t0,$v0		# moves the r value to $s1 for storage
	
	blt	$t0,$zero,retryN # chckes if the n value is above zero
	j	getR
#
# checks the value of r again and goes into a loop if it fails the condition r<=n or r<0
#
retryR:	
	li	$v0,4
	la	$a0,warningR
	syscall		      # prints the warning to keep value in bounds	
	
	li	$v0,4
	la	$a0,userR
	syscall      	 	# prints the prompt for the r value
	
	li	$v0,5
	syscall			# reads the value for the r value
	move	$t1,$v0		# moves the r value to $s1 for storage
	
	bgt	$t1,$t0,retryR		# checks if value of r>n 
	blt	$t1,$zero,retryR       # checks the value r>=0
#
# loads the values for n and r
loadNR:	
	move	$a0,$t0
	move	$a1,$t1		 # moves the values of n and r to $a0 and $a1 to call on the comb
	jal	comb
	
	move	$t9,$v0
	
	li	$v0,4
	la	$a0,comboPrint # prints the prompt
	syscall
	li	$v0,1
	move	$a0,$t9 # prints solution
	syscall
	j	exit
		
#############################################
# combo function calculates the amount of combonations out of an input
# of n and r
# Comb(n,r)= n == r 
# Comb(n,r)= Comb(n-1,r)+ Comb(n-1,r-1)
# Register Usage
##################################################
# $a0 is our n value
# $a1 is our r value
# $sp our stack pointer
# $t5 temp register that will hold the first func call for operations
#############################################
	
comb:
	# checking base cases
	beq	$a0,$a1,base	# n==r then the base case has been found
	beq	$a1,$zero,base  # r==0 then the base case has been found
	
        
	addi	$sp,$sp,-20 #loads up space for the two args and return address
	sw	$ra,16($sp) # stores return address 
	sw	$a1,12($sp) # stores the r value of each call
	sw	$a0,8($sp)  # stores the n value
	
	sub	$a0,$a0,1   # decrements n
		
	jal	comb # calls for  Comb(n-1,r)
	sw	$v0,4($sp) # stores first solution
	
	lw	$a1,12($sp) # loads r
	lw	$a0,8($sp) # loads n
	sub	$a0,$a0,1 # n-1
	sub	$a1,$a1,1 # r-1
	jal	comb # calls for Comb(n-1,r-1)	
	lw	$t5,4($sp) # adds the  first call into the stack
	add	$v0,$v0,$t5 # adds the first func call with the second call
	j	done # finishes up the 
	
base:	
       li	$v0,1
       jr	$ra # returns from the base case

done:	
	lw	$ra,16($sp) # grabs ra from stack
	addi	$sp,$sp,20 #restores stack
	jr	$ra # returns from original func call
	
exit:	
	li	$v0,10
	syscall			# exits the program



