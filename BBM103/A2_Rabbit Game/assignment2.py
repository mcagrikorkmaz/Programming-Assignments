_map_ = input("Please enter feeding map as a list:\n")
movements = input("Please enter direction of movements as a list:\n")

# I started this variable from -1 because there is an extra "]" at the end of the input for my mathematical operation.
amount_of_row = -1

elements_of_map = []
counter_of_element = 0
row_rabbit = 0
column_rabbit = 0
your_score = 0

# I obtained the map's amount of rows and columns by using this for loop.
for i in _map_:
    if i == 'W' or i == 'X' or i == 'C' or i == 'A' or i == 'M' or i == 'P' or i == '*':
        elements_of_map += [i]
        counter_of_element += 1
    elif i == ']':
        amount_of_row += 1
amount_of_column = int(counter_of_element / amount_of_row)

# I used a simple mathematical operation in this for loop to separate the lines from each one of them.
# So that I could obtain each of them as a separate list inside of the general list.
your_map = []
for i in range(amount_of_row):
    your_map.append(elements_of_map[(i * amount_of_column):(amount_of_column * (i + 1))])

print("Your board is:")
for i in range(amount_of_row):
    print(" ".join(your_map[i]))


def location_rabbit(general_list, rabbit):
    for line in general_list:
        if rabbit in line:
            global row_rabbit
            global column_rabbit
            row_rabbit = general_list.index(line)
            column_rabbit = line.index(rabbit)


location_rabbit(your_map, '*')


def end_function():
    print("Your output should be like this:")
    for a in range(amount_of_row):
        print(" ".join(your_map[a]))
    print("Your score is:", your_score)
    exit()


def up_down_direction(x):
    global your_score
    if 0 <= x <= (amount_of_row - 1) and your_map[x][column_rabbit] != 'W':
        if your_map[x][column_rabbit] == 'C':
            your_score += 10
            your_map[x][column_rabbit] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[x][column_rabbit] == 'A':
            your_score += 5
            your_map[x][column_rabbit] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[x][column_rabbit] == 'M':
            your_score -= 5
            your_map[x][column_rabbit] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[x][column_rabbit] == 'X':
            your_map[x][column_rabbit] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[x][column_rabbit] == 'P':
            your_map[x][column_rabbit] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
            end_function()
        location_rabbit(your_map, '*')


def right_left_direction(y):
    global your_score
    if 0 <= y <= (amount_of_column - 1) and your_map[row_rabbit][y] != 'W':
        if your_map[row_rabbit][y] == 'C':
            your_score += 10
            your_map[row_rabbit][y] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[row_rabbit][y] == 'A':
            your_score += 5
            your_map[row_rabbit][y] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[row_rabbit][y] == 'M':
            your_score -= 5
            your_map[row_rabbit][y] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[row_rabbit][y] == 'X':
            your_map[row_rabbit][y] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
        elif your_map[row_rabbit][y] == 'P':
            your_map[row_rabbit][y] = your_map[row_rabbit][column_rabbit]
            your_map[row_rabbit][column_rabbit] = 'X'
            end_function()
        location_rabbit(your_map, '*')


for i in movements:
    if i == 'U':
        up_down_direction(row_rabbit - 1)
    elif i == 'D':
        up_down_direction(row_rabbit + 1)
    elif i == 'R':
        right_left_direction(column_rabbit + 1)
    elif i == 'L':
        right_left_direction(column_rabbit - 1)

end_function()
