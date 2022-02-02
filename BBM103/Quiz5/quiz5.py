import sys


class kaBoomOne(Exception):
    pass


l = "------------"

try:
    operands = open(sys.argv[1], "r")
    comparison = open(sys.argv[2], "r")
    for i in operands.readlines():
        a = i.strip("\n").split(" ")
        c = comparison.readline().strip("\n").split(" ")
        if len(a) < 4:
            try:
                raise IndexError("IndexError: number of operands less than expected.")
            except IndexError as e:
                print(f"{l}\n{e}\nGiven input:", *a)
        elif len(a) > 4:
            try:
                raise kaBoomOne("kaBOOM: run for your life!")
            except kaBoomOne as e:
                print(f"{l}\n{e}")
        if a[0] == "0" or a[1] == "0":
            try:
                raise ZeroDivisionError("ZeroDivisionError: You can’t divide by 0.")
            except ZeroDivisionError as e:
                print(f"{l}\n{e}\nGiven input:", *a)
        else:
            if len(a) == 4:
                result = []
                try:
                    b = list(map(lambda x: int(float(x)), a))
                    for j in range(min(b[2], b[3]), max(b[2], b[3])+1):
                        if j % b[0] == 0 and j % b[1] != 0:
                            result.append(str(j))
                    if sorted(result) == sorted(c):
                        print(f"{l}\nMy results:\t\t", *result, f"\nResult to compare:\t", *c, f"\nGoool!!!")
                    else:
                         raise AssertionError("AssertionError: results don’t match.")
                except AssertionError as e:
                                print(f"{l}\nMy results:\t\t", *result, f"\nResult to compare:\t", *c, f"\n{e}")
                except ValueError:
                    print(f"{l}\nValueError: only numeric input is accepted.\nGiven input:", *a)
                except:
                    print(f"{l}\nkaBOOM: run for your life!")
except IOError as e:
    print(f"IOError: cannot open {e.filename}")
except IndexError:
    print("IndexError: number of input files less than expected.")
except:
    print("kaBOOM: run for your life!")
finally:
    print("\n˜ Game Over ˜")
