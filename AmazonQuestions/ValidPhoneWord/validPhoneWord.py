#!/usr/bin/env python3

import enchant

d = enchant.Dict("en_US")

numDict = {
            "1": "ABC",
            "2": "DEF",
            "3": "GHI",
            "4": "JKL",
            "5": "MNO",
            "6": "PQR",
            "7": "STU",
            "8": "VWX",
            "9": "YZ "
          }


def potentialWords(word, retWords, length, string="", index=0, depth=0):
    # check if at root node or not
    if (depth != 0):
        # base case
        if (depth == length + 1):
            return

        key = word[depth - 1]
        char = numDict[key][index]

        if (d.check(char) and char not in retWords):
            # if (char in validWords and char not in retWords):
            retWords.append(char)

        string += char

        if (d.check(char) and char not in retWords):
            # if (string in validWords and string not in retWords):
            retWords.append(string)

    potentialWords(word, retWords, length, string, 0, depth + 1)
    potentialWords(word, retWords, length, string, 1, depth + 1)
    potentialWords(word, retWords, length, string, 2, depth + 1)
    return


def main():
    retWords = []
    word = "377"
    length = len(word)

    i = 0
    while(i < length):
        temp = word[i:]
        subLength = len(temp)
        potentialWords(temp, retWords, subLength)
        i += 1

    print(retWords)


if __name__ == "__main__":
    main()
