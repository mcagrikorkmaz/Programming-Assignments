import sys
input_file = open(sys.argv[1], "r")
list_of_input = [line.split() for line in input_file.readlines()]
input_file.close()
for j in range(len(list_of_input)):
    list_of_input[j][0] = int(list_of_input[j][0])
    list_of_input[j][1] = int(list_of_input[j][1])
list_of_input.sort()
for j in range(len(list_of_input)):
    list_of_input[j][0] = str(list_of_input[j][0])
    list_of_input[j][1] = str(list_of_input[j][1])
message_counter = 0
k = 0
output_file = open(sys.argv[2], "w")
for sublist in list_of_input:
    if sublist[1] == "0":
        message_counter += 1
        output_file.write(f"Message {message_counter}")
        output_file.write("\n")
        output_file.write(" ".join(sublist))
        k += 1
    else:
        output_file.write(" ".join(sublist))
        k += 1
    if k < len(list_of_input):
        output_file.write("\n")
output_file.close()
