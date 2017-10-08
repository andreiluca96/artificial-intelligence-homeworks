import re


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


def get_similar_template(given_input):
    f = open("basic_chat.aiml")
    file_content = f.read()
    while True:
        patterns = re.search('<pattern>((\w)*\**(\s)*)*</pattern>', file_content)

        if patterns is None:
            break

        template = file_content[patterns.start() + 9: patterns.end() - 10]

        file_content = file_content[patterns.end():]
        if are_similar(template, given_input, given_input / 4):
            return template

    return None
