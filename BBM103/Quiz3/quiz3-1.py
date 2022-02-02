import sys
number = sys.argv[1]
exponent_of_number = sys.argv[2]
result = str(int(number) ** int(exponent_of_number))
print("Output : {}^{} = {}".format(number, exponent_of_number, result), end=" ")


def digit_finder(x):
    if int(x) >= 10:
        list_of_digits = []
        sum_of_digits = 0
        for i in x:
            list_of_digits += str(i) + '+'
            sum_of_digits += int(i)
        list_of_digits.pop()
        list_of_digits.append("=")
        if int(sum_of_digits) >= 10:
            print("=", " ".join(list_of_digits), sum_of_digits, end=" ")
        elif int(sum_of_digits) < 10:
            print("=", " ".join(list_of_digits), sum_of_digits)
        sum_of_digits = str(sum_of_digits)
        if int(sum_of_digits) >= 10:
            return digit_finder(sum_of_digits)


digit_finder(result)