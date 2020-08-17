
def zeroOneKnapsack(weight_list, profit_list, KnpSckWght, obj):
    table = [[0 for i in range(KnpSckWght + 1)] for i in range(obj + 1)]
    # creates the knapsack with the
    """
    Creates the knapsack columns from the knapSckweight plus 1
    and creates the rows with the number of objects plus 1
    """

    for x in range(obj+1):
        for y in range(KnpSckWght+1): # traverses rows and columns of the table, +1 due to the 0 row and column
            if x==0 or y==0:
                table[x][y]=0 # sets the first row and column to 0


            elif weight_list[x-1]<=y: # continues the interation while the num of weights is less or equal to the columns
                table[x][y]=max(profit_list[x-1]+
                                table[x-1][y-weight_list[x-1]],
                                table[x-1][y])
            #compares the value that we can obtain with or without x and sets the max to that index in the matrix

            # if the weight is over y then it's max weight has already been reached
            # the value know is the max reached from the other condition
            else:
                table[x][y]=table[x-1][y]

    result = table[obj][KnpSckWght] # assigns the result to the
    print('The max value is '+str(result)) # prints the last value to show the max value
    objectFind(table,obj,KnpSckWght,result,weight_list,profit_list) # calls the function to find the objects that gave the max value

def objectFind(table,obj,KnpSckWght,result,weight_list,profit_list):

    objects=obj
    weight=KnpSckWght
    final_list=[]    # list to hold the objects that give the maxvalue

    for i in  range(objects,0,-1): # starts a loop going through number of objects given
        if result<=0: # if the result is less than or equal to zero than we're done with the loop
            break
        # if the result equals the column above then it continues to another loop
        if result == table[i-1][weight]:
            continue
        # if the result isn't equal to the value in the column above then a value has been found
        else:
            final_list.append(i-1) # adds the value i-1 as to not to include the 0 column
            result = result-profit_list[i-1] # subtracts the value of the profit list at that point from the result
            weight=weight-weight_list[i-1] # subtracts the weight from the result and continues to the next loop

    final_list.reverse()  # reverse the list since the values are added backwards
    for i in range(len(final_list)):
        final_list[i]+=1

    print(final_list)


def main():
    answer='y'
    print('Welcome to 0-1 Knapsack')
    while(answer=='y' or answer =='Y'):
        objects = int(input('How many objects do you have?:'))  # asks user to input how much objects
        knapSackWeight = int(input('How heavy is the knapsack?:'))  # asks the knapsackweight
        profit_list = []
        weight_list = []

        for x in range(objects):  # grabbing the info of each object
            print('Object ' + str(x + 1))
            profit = int(input('Enter the profit:'))  # enters the profit
            while (profit < 0):  # checks for negative values
                print('Sorry profit can\'t be negative')
                profit = int(input('Enter the profit:'))
            profit_list.append(profit)  # adds the value to the list

            weight = int(input('Enter the weight:'))  # enters weight
            while (weight < 0):  # checks for negative values
                print('Sorry weight can\'t be negative')
                profit = int(input('Enter the weight:'))
            weight_list.append(weight)  # adds the value to the list

        zeroOneKnapsack(weight_list, profit_list, knapSackWeight, objects)
        answer = raw_input('Do you want to store another knapsack?(y/n):')
        print('\n')


if __name__=='__main__':
    main()
