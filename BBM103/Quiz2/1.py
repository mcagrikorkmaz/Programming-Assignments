import sys
a = float(sys.argv[1])
b = float(sys.argv[2])
c = float(sys.argv[3])
delta = (b**2)-(4*a*c)
if delta > 0:
    print("There are two solutions")
    print(f"Solution(s): {(-b+delta**(1/2))/2*a} {(-b-delta**(1/2))/2*a}")
elif delta == 0:
    print("There is a repeated real number solution")
    print(f"Solution(s): {(-b/2*a)}")
else:
    print("There is no real solution")