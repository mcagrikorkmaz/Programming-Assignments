import sys
numbers = sys.argv[1]
even_numbers = []
sum_of_evens = 0
sum_of_all = 0
for i in numbers.split(","):
    if int(i) > 0:
        sum_of_all += int(i)
    if int(i) > 0 and int(i) % 2 == 0:
        sum_of_evens += int(i)
        even_numbers += [i]
print("Even Numbers:", '"'+",".join(even_numbers)+'"')
print("Sum of Even Numbers:", sum_of_evens)
print("Even Number Rate:", round(sum_of_evens/sum_of_all, 3))
