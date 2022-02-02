import sys
a = int(sys.argv[1])


def rec(n):
    if n == 2 * a - 1:
        return print((n-a) * " "+((2*((2*a)-n)-1)*"*"))
    if n <= a:
        print((a-n) * " "+(2*n-1)*"*")
    if a < n:
        print((n-a) * " "+(2*(2*a-n)-1)*"*")
    return rec(n+1)


rec(1)