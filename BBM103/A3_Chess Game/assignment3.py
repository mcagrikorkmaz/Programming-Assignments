import sys
f = open(sys.argv[1], "r")
commands = [line.split() for line in f.readlines()]
f.close()
_black_ = ["R1", "N1", "B1", "QU", "B2", "N2", "R2", "P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "  "]
_white_ = ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "r1", "n1", "b1", "qu", "b2", "n2", "r2", "  "]
kingW = ["ki"]
kingB = ["KI"]
row_p, column_p = 0, 0
keys_list1 = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
keys_list2 = ['8', '7', '6', '5', '4', '3', '2', '1']


def initialize():
    global board
    board = [["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2"],
             ["P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"],
             ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
             ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
             ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
             ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
             ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"],
             ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2"]]
    return board


board = initialize()


def printing_board():
    print("------------------------")
    for i in board:
        print(*i)
    print("------------------------")


def location(board_list, _piece_):
    for line in board_list:
        if _piece_ in line:
            global row_p
            global column_p
            row_p = board_list.index(line)
            column_p = line.index(_piece_)


def showmoves(piece):
    suitable = []
    location(board, piece)
    if 'p' in piece:
        if 0 <= row_p - 1 <= 7:
            if board[row_p - 1][column_p] in _black_:
                suitable.append(keys_list1[column_p] + keys_list2[row_p-1])
    if 'P' in piece:
        if 0 <= row_p + 1 <= 7:
            if board[row_p + 1][column_p] in _white_:
                suitable.append(keys_list1[column_p] + keys_list2[row_p+1])
    if 'k' in piece or 'K' in piece:
        a = ((1, 0), (-1, 0), (0, 1), (0, -1), (1, 1), (1, -1), (-1, 1), (-1, -1))
        for x, y in a:
            if 0 <= row_p + x <= 7 and 0 <= column_p + y <= 7:
                if 'k' in piece and board[row_p + x][column_p + y] in _black_:
                    suitable.append(keys_list1[column_p + y] + keys_list2[row_p + x])
                if 'K' in piece and board[row_p + x][column_p + y] in _white_:
                    suitable.append(keys_list1[column_p + y] + keys_list2[row_p + x])
    if 'n' in piece or 'N' in piece:
        b = ((-2, 1), (-2, -1), (2, 1), (2, -1), (1, -2), (1, 2), (-1, 2), (-1, -2))
        s = ((1, 1), (1, -1), (-1, 1), (-1, -1))
        for m, n in b:
            if 0 <= row_p + m <= 7 and 0 <= column_p + n <= 7:
                if 'n' in piece and board[row_p + m][column_p + n] in _black_:
                    suitable.append(keys_list1[column_p + n] + keys_list2[row_p + m])
                if 'N' in piece and board[row_p + m][column_p + n] in _white_:
                    suitable.append(keys_list1[column_p + n] + keys_list2[row_p + m])
        for u, t in s:
            if 0 <= row_p + u <= 7 and 0 <= column_p + t <= 7:
                if board[row_p + u][column_p + t] == "  ":
                    suitable.append(keys_list1[column_p + t] + keys_list2[row_p + u])
    if 'r' in piece or 'R' in piece or 'q' in piece or 'Q' in piece:
        for c in range(1, 8):
            if 0 <= row_p + c <= 7:
                if board[row_p + c][column_p] == "  ":
                    suitable.append(keys_list1[column_p] + keys_list2[row_p + c])
                elif ('r' in piece or 'q' in piece) and board[row_p + c][column_p] in _black_:
                    suitable.append(keys_list1[column_p] + keys_list2[row_p + c])
                    break
                elif ('r' in piece or 'q' in piece) and board[row_p + c][column_p] in (_white_ + kingW):
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p + c][column_p] in _white_:
                    suitable.append(keys_list1[column_p] + keys_list2[row_p + c])
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p + c][column_p] in (_black_ + kingB):
                    break
        for c in range(1, 8):
            if 0 <= row_p - c <= 7:
                if board[row_p - c][column_p] == "  ":
                    suitable.append(keys_list1[column_p] + keys_list2[row_p - c])
                elif ('r' in piece or 'q' in piece) and board[row_p - c][column_p] in _black_:
                    suitable.append(keys_list1[column_p] + keys_list2[row_p - c])
                    break
                elif ('r' in piece or 'q' in piece) and board[row_p - c][column_p] in (_white_ + kingW):
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p - c][column_p] in _white_:
                    suitable.append(keys_list1[column_p] + keys_list2[row_p - c])
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p - c][column_p] in (_black_ + kingB):
                    break
        for c in range(1, 8):
            if 0 <= column_p + c <= 7:
                if board[row_p][column_p + c] == "  ":
                    suitable.append(keys_list1[column_p + c] + keys_list2[row_p])
                elif ('r' in piece or 'q' in piece) and board[row_p][column_p + c] in _black_:
                    suitable.append(keys_list1[column_p + c] + keys_list2[row_p])
                    break
                elif ('r' in piece or 'q' in piece) and board[row_p][column_p + c] in (_white_ + kingW):
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p][column_p + c] in _white_:
                    suitable.append(keys_list1[column_p + c] + keys_list2[row_p])
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p][column_p + c] in (_black_ + kingB):
                    break
        for c in range(1, 8):
            if 0 <= column_p - c <= 7:
                if board[row_p][column_p - c] == "  ":
                    suitable.append(keys_list1[column_p - c] + keys_list2[row_p])
                elif ('r' in piece or 'q' in piece) and board[row_p][column_p - c] in _black_:
                    suitable.append(keys_list1[column_p - c] + keys_list2[row_p])
                    break
                elif ('r' in piece or 'q' in piece) and board[row_p][column_p - c] in _white_:
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p][column_p - c] in _white_:
                    suitable.append(keys_list1[column_p - c] + keys_list2[row_p])
                    break
                elif ('R' in piece or 'Q' in piece) and board[row_p][column_p - c] in (_black_ + kingB):
                    break
    if 'b' in piece or 'B' in piece or 'q' in piece or 'Q' in piece:
        for w in range(1, 8):
            if 0 <= row_p + w <= 7 and 0 <= column_p + w <= 7:
                if ('B' in piece or 'q' in piece or 'Q' in piece) and board[row_p + w][column_p + w] == "  ":
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p + w])
                elif ('B' in piece or 'Q' in piece) and board[row_p + w][column_p + w] in _white_:
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p + w])
                    break
                elif ('B' in piece or 'Q' in piece) and board[row_p + w][column_p + w] in (_black_ + kingB):
                    break
                elif 'q' in piece and board[row_p + w][column_p + w] in _black_:
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p + w])
                    break
                elif 'q' in piece and board[row_p + w][column_p + w] in (_white_ + kingW):
                    break
        for w in range(1, 8):
            if 0 <= row_p + w <= 7 and 0 <= column_p - w <= 7:
                if ('B' in piece or 'q' in piece or 'Q' in piece) and board[row_p + w][column_p - w] == "  ":
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p + w])
                elif ('B' in piece or 'Q' in piece) and board[row_p + w][column_p - w] in _white_:
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p + w])
                    break
                elif ('B' in piece or 'Q' in piece) and board[row_p + w][column_p - w] in (_black_ + kingB):
                    break
                elif 'q' in piece and board[row_p + w][column_p - w] in _black_:
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p + w])
                    break
                elif 'q' in piece and board[row_p + w][column_p - w] in (_white_ + kingW):
                    break
        for w in range(1, 8):
            if 0 <= row_p - w <= 7 and 0 <= column_p + w <= 7:
                if ('b' in piece or 'q' in piece or 'Q' in piece) and board[row_p - w][column_p + w] == "  ":
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p - w])
                elif ('b' in piece or 'q' in piece) and board[row_p - w][column_p + w] in _black_:
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p - w])
                    break
                elif ('b' in piece or 'q' in piece) and board[row_p - w][column_p + w] in (_white_ + kingW):
                    break
                elif 'Q' in piece and board[row_p - w][column_p + w] in _white_:
                    suitable.append(keys_list1[column_p + w] + keys_list2[row_p - w])
                    break
                elif 'Q' in piece and board[row_p - w][column_p + w] in (_black_ + kingB):
                    break
        for w in range(1, 8):
            if 0 <= row_p - w <= 7 and 0 <= column_p - w <= 7:
                if ('b' in piece or 'q' in piece or 'Q' in piece) and board[row_p - w][column_p - w] == "  ":
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p - w])
                elif ('b' in piece or 'q' in piece) and board[row_p - w][column_p - w] in _black_:
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p - w])
                    break
                elif ('b' in piece or 'q' in piece) and board[row_p - w][column_p - w] in (_white_ + kingW):
                    break
                elif 'Q' in piece and board[row_p - w][column_p - w] in _white_:
                    suitable.append(keys_list1[column_p - w] + keys_list2[row_p - w])
                    break
                elif 'Q' in piece and board[row_p - w][column_p - w] in (_black_ + kingB):
                    break
    return suitable


def move(moved_piece, place):
    location(board, moved_piece)
    destination = list(place)
    board[keys_list2.index(destination[1])][keys_list1.index(destination[0])] = board[row_p][column_p]
    board[row_p][column_p] = "  "


for line in commands:
    if line[0] == 'print':
        print("> print")
        printing_board()
    elif line[0] == 'initialize':
        initialize()
        print(f"> initialize\nOK")
        printing_board()
    elif line[0] == 'exit':
        print("> exit")
        exit()
    elif line[0] == 'showmoves':
        if showmoves(line[1]) == []:
            print(f"> showmoves {line[1]}\nFAILED")
        else:
            print(f"> showmoves {line[1]}")
            print(*sorted(showmoves(line[1])))
    elif line[0] == 'move':
        print(f"> move {line[1]} {line[2]}")
        showmoves(line[1])
        if line[2] in showmoves(line[1]):
            move(line[1], line[2])
            print("OK")
        else:
            print("FAILED")