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


def potentialWords(word, retWords, length, string="", index=0, depth=-1):
    # base case
    if (depth == length):
        return
    elif (depth >= 0):
        key = word[depth]
        char = numDict[key][index]

        string += char

        if (d.check(string) and not string in retWords):
            retWords.append(string)

        potentialWords(word, retWords, length, "", 0, depth + 1)
        potentialWords(word, retWords, length, "", 1, depth + 1)
        potentialWords(word, retWords, length, "", 2, depth + 1)

    potentialWords(word, retWords, length, string, 0, depth + 1)
    potentialWords(word, retWords, length, string, 1, depth + 1)
    potentialWords(word, retWords, length, string, 2, depth + 1)

    return


def main():
    retWords = []
    word = "377"
    length = len(word)

    potentialWords(word, retWords, length)

    print(retWords)


if __name__ == "__main__":
    main()
