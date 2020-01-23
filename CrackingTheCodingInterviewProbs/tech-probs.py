#!/usr/bin/env python3

def check_perm(s1, s2):
    temp = s2
    length1 = len(s1)

    outer_found = True
    j = 0
    while (j < length1 and outer_found):
        letter1 = s1[j]
        j += 1
        i = 0
        inner_found = False
        length2 = len(temp)
        while (length2 > i and not(inner_found)):
            if (letter1 == temp[i]):
                temp.replace(temp[i],"")
                inner_found = True
            else:
                i += 1

            if (i == length2 and not(inner_found)):
                outer_found = False

    if (outer_found):
        return True
    return False

def URLify(s, n):
    i = 0
    while (i < n):
        if (s[i] == " "):
            s = s.replace(" ","%20",1)
        i += 1
    print(s)

def pal_perm(s):
    length = len(s)
    count = {}
    i = 0
    keys = []
    while (i < length):
        if (s[i] in count):
            count[s[i]] += 1
        else:
            keys.append(s[i])
            count[s[i]] = 1
        i += 1

    print(count)

    odd_flag = 0
    i = 0
    valid = True
    while (i < len(keys) and valid):
        if (count[keys[i]] & 1):
            if (odd_flag):
                valid = False
            else:
                odd_flag = 1
        i += 1

    return valid

def str_compression(s):
    i = 0
    while (i < len(s)):
        j = i + 1
        while(j < len(s) and s[j] == s[i]):
            j += 1
        if (not(j-1 == i)):
            s = s[0:i+1] + str(j - i) + s[j:]
        else:
            s = s[0:i+1] + "1" + s[j:]
        i += 2
    print(s)

def perf_sub(s, k):
    length = len(s)
    keys = []
    counter = 0

    count = {}
    for letter in s:
        if (not(letter in count)):
            count[letter] = 0

    end = k
    i = 0
    j = end
    while (end < length):
        temp = s[i:j]
        for letter in temp:
            if (not(letter in keys)):
                keys.append(letter)
            count[letter] += 1

        g = 0
        match = True
        while (g < len(keys) and match):
            if (count[keys[g]] != k):
                match = False
            else:
                g += 1

        if (match):
            print(temp)
            counter += 1

        for key in keys:
            count[key] = 0
        keys = []

        if (j == length):
            i = 0
            end += k
            j = end
        else:
            i += 1
            j += 1

    return counter

def main():
    #s1 = "hero"
    #s2 = "hore"
    #print(check_perm(s1, s2))
    #s = "Mr John Smith    "
    #n = 13
    #URLify(s, n)
    #print(pal_perm("obbozzcd"))
    #str_compression("abc")
    print(perf_sub("654687464684354646876413546877987641346847684164", 2))

if __name__ == "__main__":
    main()
