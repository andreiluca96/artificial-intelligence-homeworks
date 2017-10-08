def are_similar(s1, s2, allowed_number_of_wrong_characters):
    letters1 = []
    letters2 = []

    for i in range(0, 256):
        letters1.append(0)
        letters2.append(0)

    for character in s1.lower():
        letters1[ord(character)] += 1

    for character in s2.lower():
        letters2[ord(character)] += 1

    unmatched_characters = 0
    for i in range(0, 256):
        unmatched_characters += abs(letters1[i] - letters2[i])

    if unmatched_characters <= allowed_number_of_wrong_characters:
        return True

    return False


s1 = raw_input("Enter the first string: ")
s2 = raw_input("Enter the second string: ")

print (are_similar(s1, s2, 2))