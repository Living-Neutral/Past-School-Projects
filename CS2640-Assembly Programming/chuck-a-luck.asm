########################################################################
# Program: chuck (Chuck-A-Luck)				Programmer: Jeffrey McDonald
# Due Date: Oct 31, 2019					Course: CS2640.03
########################################################################
# Overall Program Functional Description:
#	The program plays the Chuck-A-Luck game.  The player starts with
#	a purse of $500.  For each round, the player selects a wager, then
#	picks a number from 1 to 6.  The program then rolls three dice.
#	If none of the dice match the chosen number, the player loses the
#	wager.  For each dice that matches the chosen number, the player
#	earns the wager (so, for example, if two dice show the chosen number,
#	the player earns twice the wager).  The program ends when the
#	player enters a wager of 0.
#
########################################################################
# Register usage in Main:
#	[ List your register usage here ]
#       $s0[wager],$t1[holdings],$s2[holds the success],$t2[chosen number],$t3[success counter],$t4 [roll incrementer],$t5 [random],$t6[add wager incrementer]
########################################################################
# Pseudocode Description:
#	1. Print a welcome message
#	2. Get a value from the user, use it to seed the random number generator  (done)
#	3. Seed the player's holdings with 500. (done)
#	4. Loop:
#		a. Print their holdings, receive the wager.  If 0, break the loop. (done)
#		b. Get the chosen number for this round.  (done)
#		c. Looping 3 times:
#			1. Get a random dice roll
#			2. If it matches the chosen number, increment the success counter
#		d. Print a message based on the success counter, and adjust their
#			holdings based on this same counter.
#		e. If the holdings get to 0, print a 'bye' message.
#	5. Clean up, print a 'bye' message, and leave.(done)
#
########################################################################
		.data
	# [ Enter your global data requirements here! ]
welcome:        .asciiz"\n Welcome to Chuck-a-Luck!\n"
seedPrompt:     .asciiz"\n Enter a seed number:"
winnings:        .asciiz"\n You matched "
bye:            .asciiz"\n Bye!"

		.globl main
		.text
main:
	# [ Write your main routine here!]
	li ,$v0  ,4       
	la ,$a0  ,welcome      # prints the welcome 
	syscall
	li ,$v0  ,4
	la ,$a0  ,seedPrompt  # prints the seed prompt
	syscall
	li ,$v0 , 5            # read the answer
	syscall
	jal    seedrand
	
	addi,$t1,$t1, 500      # seed the wager with 500
	li ,$v0,1
	move ,$a0,$t1

	
loop:
        jal    getwager
        move   $s0,$v0    # gets the wager and moves it to t0
       	
	beq   $t1,$zero, end  # ends if holdings hits 0
	beq   $s0,$zero, end  # ends if wager hits 0
	
	jal   getguess       # moves the guess to be held in $t2
	move  $t2,$v0
	
        li    $t4,3	    # counter for roll incrementer
        li    $t3,0         # success counter
        
roll:   
        jal   rand          # calls the rand function 
        move  $t5,$v0
        beq   $t2,$t5,L1      # compares the user choice to the rand functions and adds to the success counter
        move  $s2,$t3
       # add   $s2,$s2,$t3         # adds the amount of $s2[to print out success],$t3[success]
        
rollCon:
        sub  $t4,$t4,1           # decrements the counter by one to proceed to next iteration
        bgt  $t4,$zero,roll     # while the counter is above zero
        
        beq $t3,$zero,subWager  # goes the the sub wager lable in memory if the success $t3==0
        
        
addLoop:
        sub $t3,$t3,1                # counts the success down to add that amount
        bgt $t3,$zero,addWager   # goes to the add wager label in memory if the success $t3>0
        #beq $t3,$zero,subWager   # goes the the sub wager lable in memory if the success $t3==0
         
rollEnd:
        li $v0,4                     
        la $a0,winnings  # prints out the winnings
        syscall
        
        li   $v0,1
        move $a0,$s2      # amount of success in that roll
        syscall
                   
        move  $a0,$t1  # moves the holdings to $a0 for the next loop  
	j     loop

addWager:
         add  $t1,$t1,$s0               # adds the wager and the holdings together and moves it to $a0
                                       # moves it back from $a0 to $t1
                                       # decrements the wager count by 1
         j    addLoop
         
subWager:
         sub $t1,$t1,$s0              # subtracts the wager and the holdings 
         # move $t1,$a0                #  moves it to $a0
         j   rollEnd



L1:     addi $t3,$t3,1   # adds the amount of success	
        j    rollCon     # jumps back to further the loop    	
        
end:    
        li, $v0,  4       
	la, $a0,  bye      # prints the  bye
	syscall
	
        li,$v0,10
        syscall               # exits program

	
########################################################################
# Function Name: int getwager(holdings)
########################################################################
# Functional Description:
#	This routine is passed the player's current holdings, and will return
#	the player's wager, or the value 0 if the player wants to quit the
#	program.  It displays the holdings, then prompts for the wager.
#	It then checks to see if the wager is in the proper range.  If so,
#	it returns the wager.  Otherwise, it prints an error message, then
#	tries again.
#
########################################################################
# Register Usage in the Function:
#	$v0, #a0 -- for subroutine linkage and general calculations
#	$t8 -- a temporary register used to store the holdings
#
########################################################################
# Algorithmic Description in Pseudocode:
#	1. Display the current holdings to the player
#	1. Print the prompt, asking for the wager
#	2. Read in the number
#	3. If the number is between 0 and holdings, return with that number
#	4. Otherwise print an error message and loop back to try again.
#
########################################################################
	.data
holdmsg:	.asciiz "\nYou currently have $"
wagermsg:	.asciiz "\nHow much would you like to wager? "
big:	.asciiz "\nThat bet is too big."
negtv:	.asciiz "\nYou can't bet a negative amount."
	.text
getwager:
	move 	$t8, $a0		# Save their holdings in $t8
again:
	li		$v0, 4			# Call the Print String I/O Service to print
	la		$a0, holdmsg	#   message about their holdings
	syscall
	move	$a0, $t8		# Call the Print Integer I/O Service to 
	li		$v0, 1			#   print the value
	syscall
	li		$v0, 4			# Call the Print String I/O Service to 
	la		$a0, wagermsg	#  	ask for the wager
	syscall
	li		$v0, 5			# Call the Read Integer I/O Service to
	syscall					#   fetch the wager
	bgt		$v0, $t8, toobig	# If wager > holdings, go to error line
	bltz	$v0, toosmall	# If wager < 0, go to error line
	jr		$ra				# Return with the wager in $v0
toobig:
	li		$v0, 4			# Call the Print String I/O Service to print
	la		$a0, big		#   that the wager was too big
	syscall
	j		again			# Jump back to try again
toosmall:
	li		$v0, 4			# Call the Print String I/O Service to print
	la		$a0, negtv		#   that the wager was too small
	syscall
	j		again			# Jump back to try again

########################################################################
# Function Name: int getguess()
########################################################################
# Functional Description:
#	This routine asks the player to enter the chosen number, which
#	should be between 1 and 6.  If the value is out-of-range, the
#	routine will print a message and ask again, repeating until we
#	get a valid number.
#
########################################################################
# Register Usage in the Function:
#	$v0, #a0 -- for subroutine linkage and general calculations
#	$t0 -- a temporary register used in the calculations
#
########################################################################
# Algorithmic Description in Pseudocode:
#	1. Print the prompt, asking for the chosen number
#	2. Read in the number
#	3. If the number is between 1 and 6, return with that number
#	4. Otherwise print an error message and loop back to try again.
#
########################################################################
	.data
dice:	.asciiz "\nWhat number do you want to bet on? "
limit:	.asciiz "\nThe number has to be between 1 and 6."
	.text
getguess:
	li		$v0, 4			# Call the Print String I/O Service to print
	la		$a0, dice		#   request for their chosen number
	syscall
	li		$v0, 5			# Call the Read Integer I/O Service to get
	syscall					#   the number from the player
	blez	        $v0, bad		# If the number is negative, it is bad
	li		$a0, 6			# If the number is greater than 6, it is bad
	bgt		$v0, $a0, bad
	jr		$ra				# Return with the valid number in $v0
bad:
	li		$v0, 4			# Call the Print String I/O Service to print
	la		$a0, limit		#   that the number is out-of-bounds
	syscall
	j		getguess		# Loop back to try again

########################################################################
# Function Name: int rand()
########################################################################
# Functional Description:
#	This routine generates a pseudorandom number using the xorsum
#	algorithm.  It depends on a non-zero value being in the 'seed'
#	location, which can be set by a prior call to seedrand.  This
#	version of the routine always returns a value between 1 and 6.
#
########################################################################
# Register Usage in the Function:
#	$t0 -- a temporary register used in the calculations
#	$v0 -- the register used to hold the return value
#
########################################################################
# Algorithmic Description in Pseudocode:
#	1. Fetch the current seed value into $v0
#	2. Perform these calculations:
#		$v0 ^= $v0 << 13
#		$v0 ^= $v0 >> 17
#		$v0 ^= $v0 << 5
#	3. Save the resulting value back into the seed.
#	4. Mask the number, then get the modulus (remainder) dividing by 6.
#	5. Add 1, so the value ranges from 1 to 6
#
########################################################################
		.data
seed:	.word 31415			# An initial value, in case seedrand wasn't called
		.text
rand:
	lw		$v0, seed		# Fetch the seed value
	sll		$t0, $v0, 13	# Compute $v0 ^= $v0 << 13
	xor		$v0, $v0, $t0
	srl		$t0, $v0, 17	# Compute $v0 ^= $v0 >> 17
	xor		$v0, $v0, $t0
	sll		$t0, $v0, 5		# Compute $v0 ^= $v0 << 5
	xor		$v0, $v0, $t0
	sw		$v0, seed		# Save result as next seed
	andi	$v0, $v0, 0xFFFF	# Mask the number (so we know its positive)
	li		$t0, 6			# Get result mod 6, plus 1.  We get a 6 into
	div		$v0, $t0		# $t0, then do a divide.  The reminder will be
	mfhi	$v0				# in the special register, HI.  Move to $v0.
	add		$v0, $v0, 1		# Increment the value, so it goes from 1 to 6.
	jr		$ra				# Return the number in $v0
	
########################################################################
# Function Name: seedrand(int)
########################################################################
# Functional Description:
#	This routine sets the seed for the random number generator.  The
#	seed is the number passed into the routine.
#
########################################################################
# Register Usage in the Function:
#	$a0 -- the seed value being passed to the routine
#
########################################################################
seedrand:
	sw $a0, seed
	jr $ra
