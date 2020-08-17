import random

"""
fractionalKnapSack
params : weight(int),profit(int),ratio(int),maxcap(int)

"""

def fractionalKnapSack(weight_list,profit_list,maxCap):
    maxValue = 0 # Sets the maxvalue currently in the bag
    indexes = list(range(len(weight_list))) # creates the indexes list filled with the indexes ex [0,1,2,3,..n] to iterate through the lists
    objects = [0]*len(profit_list) # creates an objects

    # creates the ratio list from each element from the profit weight and dividing them for each index
    ratio=[profit/weight for profit,weight in zip(profit_list,weight_list)]

    indexes.sort(key=lambda i: ratio[i], reverse=True) # sorts the ratio list in nondecreasing order based

    # starts the loop with i=0 to the max indexes
    for i in indexes:
        if maxCap-weight_list[i]>=0:     # while the maxCap is above 0 it will take from the weight list
            maxCap -= weight_list[i]     # subtracts the current weight from the maxCap
            maxValue += profit_list[i]   # adds the max value from the profit list
            objects[i]=1                 # sets the objects list to 1 as it took the entire object

        else:  # the maxcap is close to 0 and the last item will be picked
            objects[i]=maxCap/weight_list[i]   # adds the last item as a fraction
            maxValue += profit_list[i] * maxCap / weight_list[i] # adds the fraction of the value from the last value
            break #ends the loop early

    objects = ['%.2f' % elem for elem in objects] # formats the items in the list to be only 2 decimal


    print('The max value you can grab is:%.2f' %maxValue)
    print('The objects to grab is', objects)

def main():
    answer='y'
    print('Welcome to fractional KnapSack!')
    while(answer=='y' or answer== 'Y'):
        object = int(input('Enter the amount of objects:'))  # asks for the amount of objects
        maxCap = int(input('Enter the max knapsack:'))  # asks for the max weight
        weight_list = []
        profit_list = []
        for x in range(object):  # the loop starts and goes up to the amount of objects by the user
            print('Object ' + str(x + 1))
            profit = int(input('Enter the profit:'))
            while (profit < 0):
                print('Sorry profit can\'t be negative')
                profit = int(input('Enter the profit:'))
            profit_list.append(profit)

            weight = int(input('Enter the weight:'))
            while (weight < 0):
                print('Sorry weight can\'t be negative')
                weight = int(input('Enter the weight:'))
            weight_list.append(weight)

        fractionalKnapSack(weight_list, profit_list, maxCap)
        answer=raw_input('Do you want to store another knapsack?(y/n):')

    print('Program over!')

main()