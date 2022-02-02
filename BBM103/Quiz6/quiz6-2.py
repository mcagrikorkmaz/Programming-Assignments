import sys
a = int(sys.argv[1])
comp1 = [[(a-n) * " "+(2*n-1)*"*"] for n in range(1,a+1)]
comp2 = [[(n-a) * " "+(2*(2*a-n)-1)*"*"] for n in range(a+1,2*a)]
for i in comp1:
    print(*i)
for j in comp2:
    print(*j)