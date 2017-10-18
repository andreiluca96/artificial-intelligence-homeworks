import yaml


def load_dictionary(file):
    yaml_file = open(file, 'r')
    return yaml.load(yaml_file)


def write_dictionary(dictionary, file):
    with open(file, 'w+') as outfile:
        yaml.dump(dictionary, outfile)
