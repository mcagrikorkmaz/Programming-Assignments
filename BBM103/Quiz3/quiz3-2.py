import sys
set_of_numbers = sys.argv[1]
set_of_numbers = set_of_numbers.split(",")
numbers = []
for j in set_of_numbers:
    if int(j) > 0:
        numbers.append(int(j))
encountered_numbers = []
i = 0
while i <= len(numbers):
    if i > 1 and i not in encountered_numbers and i in numbers:
        encountered_numbers.append(int(i))
        del numbers[int(i)-1::int(i)]
        i += 1
    else:
        i += 1
print("Output :", *numbers)