import sys
import random

print('Welcome to Script!' + 'It does nothing important')
print('Platform type : ' + sys.platform)

ls=[1,2,3,4,5,6,7,8,9]

def stat_count(iter):
    count = {}
    for x in ls :
        count[x] = 0
    var = 0
    while var < iter :
        x  = random.choice(ls)
        count[x] = count[x]+1
        var = var + 1
        
    string = ''
    for x in ls :
        string = string + str(x) + ' = ' + str(count[x])+ ' times \n'
    print(string)
    return string

stat_count(100)