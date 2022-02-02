import sys
enc_dict = {"A":1,"B":2,"C":3,"D":4,"E":5,"F":6,"G":7,"H":8,"I":9,
            "J":10,"K":11,"L":12,"M":13,"N": 14,"O":15,"P":16,"Q":17,
            "R":18,"S":19,"T":20,"U":21,"V":22,"W":23,"X":24,"Y":25,"Z":26,
            "a":1,"b":2,"c":3,"d":4,"e":5,"f":6,"g":7,"h":8,"i":9,
            "j":10,"k":11,"l":12,"m":13,"n":14,"o":15,"p":16,"q":17,
            "r":18,"s":19,"t":20,"u":21,"v":22,"w":23,"x":24,"y":25,"z":26," ":27}
dec_dict = {1:"A",2:"B",3:"C",4:"D",5:"E",6:"F",7:"G",8:"H",9:"I",
            10:"J",11:"K",12:"L",13:"M",14:"N",15:"O",16:"P",17:"Q",
            18:"R",19:"S",20:"T",21:"U",22:"V",23:"W",24:"X",25:"Y",26:"Z",27:" "}

# --------- PARAMETER NUMBER ERROR ----------
parameter_number = 0
try:
    for i in range(6):
        if sys.argv[i]:
            parameter_number += 1
except IndexError:
    pass
try:
    if parameter_number != 5:
        raise Exception("Parameter number error")
except Exception as e:
    print(e)
    exit()


# --------- UNDEFINED PARAMETER ERROR ----------
if sys.argv[1] != "enc" and sys.argv[1] != "dec":
    try:
        raise Exception("Undefined parameter error")
    except Exception as e:
        print(e)
        exit()


# -------------- KEY FILE ERRORS ---------------
try:
    if not sys.argv[2].endswith(".txt"):
        try:
            raise Exception("Key file could not be read error")
        except Exception as e:
            print(e)
            exit()
    k = open(sys.argv[2], "r")
    if len(k.readlines()) == 0:
        try:
            raise Exception("Key file is empty error")
        except Exception as error:
            print(error)
            k.close()
            exit()
    try:
        k = open(sys.argv[2], "r")
        for i in k.readlines():
            for j in i.strip("\n").split(","):
                int(j)
            for t in i.strip("\n"):
                if t != ",":
                    int(t)
    except ValueError:
        print("Invalid character in key file error")
        k.close()
        exit()
except FileNotFoundError:
    print("Key file not found error")
    exit()

# -------------- INPUT FILE ERRORS ---------------
try:
    if not sys.argv[3].endswith(".txt"):
        try:
            raise Exception("The input file could not be read error")
        except Exception as e:
            print(e)
            exit()
    f = open(sys.argv[3], "r")
    if len(f.readlines()) == 0:
        try:
            raise Exception("Input file is empty error")
        except Exception as error:
            print(error)
            f.close()
            exit()
    if sys.argv[1] == "enc":
        try:
            f = open(sys.argv[3], "r")
            for i in f.readlines():
                for j in i:
                    if j not in enc_dict.keys():
                        raise Exception("Invalid character in input file error")
        except Exception as e:
            print(e)
            f.close()
            exit()
except FileNotFoundError:
    print("Input file not found error")
    exit()


def transpose(a):
    # I used mathematical operation to transpose matrices.
    # number of row and column that I have to regard is decreasing at the same time.
    for i in range(len(a)):
        for j in range(1, len(a)-i):
            a[i][i+j], a[i+j][i] = a[i+j][i], a[i][i+j]
    return a


def minor(m, i, j):
    minor_matrix = []
    last_minor = []
    # If the number has a same row or column with the number that I am getting matrix of minor of it,I obstructed it.
    for k in range(len(m)):
        for z in range(len(m)):
            if k != i and z != j:
                minor_matrix.append(m[k][z])
    for y in range(len(m)-1):
        last_minor.append(minor_matrix[(y*(len(m)-1)):((len(m)-1)*(y+1))])
    return last_minor


def determinant(x):
    # I implemented recursion in this function to calculate determinant of the nxn matrices.
    if len(x) == 2:
        return (x[0][0]*x[1][1])-(x[1][0]*x[0][1])
    else:
        d = 0
        for i in range(len(x)):
            if i % 2 == 0:
                d += x[0][i]*determinant(minor(x, 0, i))
            else:
                d += (-1)*x[0][i]*determinant(minor(x, 0, i))
    return d


def cofactor(x):
    cof = []
    last_cof = []
    for i in range(len(x)):
        for j in range(len(x)):
            cof += [determinant(minor(x, i, j))]
    # I used this for loop to follow the rule "-,+,-,+,-..." when I getting matrix of cofactors
    for r in range(len(cof)):
        if r % 2 != 0:
            cof[r] = (-1)*cof[r]
    for e in range(len(x)):
        last_cof.append(cof[(e*len(x)):((e+1)*len(x))])
    return last_cof


def inverse(x):
    d = determinant(x)
    # inverse of 2x2 matrices
    if len(x) == 2:
        x[0][0],x[1][1] = x[1][1],x[0][0]
        x[0][1],x[1][0] = (-1)*x[0][1],(-1)*x[1][0]
        last_matrix = x
    # inverse of nxn matrices (n>2)
    else:
        last_matrix = transpose(cofactor(x))
    for w in range(len(last_matrix)):
        for a in range(len(last_matrix)):
            last_matrix[w][a] = int(last_matrix[w][a]/d)
    return last_matrix


def matrix_mul(matrix1, matrix2):
    result = []
    for r in range(len(matrix1)):
        result.append([0])
    for w in range(len(matrix1)):
        for u in range(len(matrix2)):
            result[w][0] += matrix1[w][u] * matrix2[u][0]
    return result


# ------ ENCODING --------
if sys.argv[1] == "enc":
    k = open(sys.argv[2], "r")
    n = len(k.readlines())
    k.close()
    f = open(sys.argv[3], "r")
    letter_keys = [[letter] for letter in list(f.readline())]
    f.close()
    letter_matrix = []
    # I obtained equivalents of the letters as a number through enc_dict in this for loop.
    for i in range(len(letter_keys)):
        letter_keys[i][0] = enc_dict[letter_keys[i][0]]
    # I implemented simple operations to learn how many space characters that I need and I added them if I need.
    remainder = len(letter_keys) % n
    part = len(letter_keys) // n
    for i in range(part):
        letter_matrix.append(letter_keys[i * n:(i + 1) * n])
    if remainder != 0:
        letter_matrix.append(letter_keys[-remainder:])
        for i in range(n - remainder):
            letter_matrix[-1].append([27])
    k = open(sys.argv[2], "r")
    key_file = [int(number) for row in k.readlines() for number in row.strip("\n").split(",")]
    k.close()
    key_matrix = []
    result_matrices = []
    for i in range(n):
        key_matrix.append(key_file[(i * n):((i + 1) * n)])
    for y in letter_matrix:
        result_matrices += [matrix_mul(key_matrix, y)]
    out = open(sys.argv[4], "w")
    for z in range(len(result_matrices)):
        for e in range(n):
            out.write(str(result_matrices[z][e][0]))
            # I used a simple mathematical operation to escape from the extra comma being at the end of the line.
            if e + z < n + len(result_matrices)-2:
                out.write(",")
    out.close()

# ------ DECODING -------
if sys.argv[1] == "dec":
    k = open(sys.argv[2], "r")
    n = len(k.readlines())
    k.close()
    f = open(sys.argv[3], "r")
    letter_keys = [[int(letter)] for letter in list(f.readline().split(","))]
    f.close()
    letter_matrix = []
    part = len(letter_keys) // n
    for i in range(part):
        letter_matrix.append(letter_keys[(i * n):((i + 1) * n)])
    k = open(sys.argv[2], "r")
    key_file = [int(number) for row in k.readlines() for number in row.strip("\n").split(",")]
    k.close()
    key_matrix = []
    result_matrices = []
    for i in range(n):
        key_matrix.append(key_file[(i * n):((i + 1) * n)])
    inverse_key = inverse(key_matrix)
    for y in letter_matrix:
        result_matrices += [matrix_mul(inverse_key, y)]
    # I obtained equivalents of the numbers as a letter through dec_dict in the following for loop.
    out = open(sys.argv[4], "w")
    for z in range(len(result_matrices)):
        for e in range(n):
            out.write(dec_dict[result_matrices[z][e][0]])
    out.close()
