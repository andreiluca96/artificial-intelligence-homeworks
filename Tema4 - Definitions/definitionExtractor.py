from storage import load_dictionary, write_dictionary
file1 = open("document.txt", "r")
file2 = open("words.txt", "r")
document = file1.read()
document = document.lower()

print document

patterns_file = "patterns.yaml"
extracted_definition_file = "extracted_definition.yaml"

patterns = load_dictionary(patterns_file)
if patterns is None:
    patterns = dict()

extracted_definition = load_dictionary(extracted_definition_file)
if extracted_definition is None:
    extracted_definition = dict()


def get_definitions():
    for word in file2:
        word = word.lower()
        word = word.strip('\n')
        if word in document :
            index = document.find(word)
            if index != -1:
                for pattern in patterns:
                    for text in patterns[pattern]:
                        full_def = word + ' ' + text
                        index_of_definition = document.find(full_def)
                        if index_of_definition != -1:
                            index_of_meaning = index_of_definition + len(full_def) + 1
                            text += ' '
                            while document[index_of_meaning].isalpha():
                                text += document[index_of_meaning]
                                index_of_meaning += 1
                            if word not in extracted_definition:
                                extracted_definition[word] = []
                            aux_dict = dict()
                            if pattern != 'antonim':
                                aux_dict['definition'] = text
                            else:
                                aux_dict['not a definition'] = text
                            aux_dict['offset'] = index_of_definition
                            extracted_definition[word].append(aux_dict)
        write_dictionary(extracted_definition, extracted_definition_file)

get_definitions()