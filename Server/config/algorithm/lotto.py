# -*- coding: utf-8 -*-

# Made by Henry

from random import shuffle
import csv
import sys

lst = []

with open('lotto.csv') as csvfile:
    readCSV = csv.reader(csvfile, delimiter = ',')
    for row in readCSV:
        for i in range(7):
            lst.append(int(row[i]))

    shuffle(lst)

    st = set({})

    for num in lst:
        if len(st) == 7:
            break
        st.add(num)

    if len(st) is not 7:
        print('Something is wrong')
        sys.exit(0)

    res = sorted(st)
    
    for num in res:
        print int(num)
    #sys.stdout.write(str(num) + ' ')