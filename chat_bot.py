import aiml
from random import randint

from storage import load_dictionary, write_dictionary

def map_age(age):
    if int(age) < 14:
        return "CHILD"
    if 15 <= int(age) <= 25:
        return "TEENAGER"
    if int(age) > 25:
        return "ADULT"


kernel = aiml.Kernel()
kernel.learn("std-startup.xml")
kernel.respond("load aiml b")

annoyed_bot = aiml.Kernel()
annoyed_bot.learn("std-startup_annoyed.xml")
annoyed_bot.respond("load aiml b")

specialized_bot = aiml.Kernel()
specialized_bot.learn("std-startup_specialized.xml")
specialized_bot.respond("load aiml b")

conversation_metadata_filename = "conversation_metadata.yaml"

session_id = 12345

patterns_used = set()

while True:
    message = raw_input("Enter your message to the bot: ")

    conversation_metadata = load_dictionary(conversation_metadata_filename)
    if conversation_metadata is None:
        conversation_metadata = dict()
    if message == "quit":
        exit()
    else:
        if message.lower() in patterns_used:  # TO DO: Add persistence to these
            print (annoyed_bot.respond("annoyed", session_id))
        else:
            patterns_used.add(message.lower())
            bot_response = kernel.respond(message, session_id)

            username = kernel.getPredicate('username', session_id)
            if len(username) > 0:
                if username in conversation_metadata\
                        and 'occupation' in conversation_metadata[username] \
                        and 'age' in conversation_metadata[username] \
                        and len(conversation_metadata[username]['occupation']) > 0 \
                        and len(conversation_metadata[username]['age']) > 0:
                    kernel.setPredicate('occupation', conversation_metadata[username]['occupation'], session_id)
                    kernel.setPredicate('age', conversation_metadata[username]['age'], session_id)

                conversation_metadata[username] = dict()

                if len(kernel.getPredicate('occupation', session_id)) <= 0:
                    conversation_metadata[username]['occupation'] = ""
                else:
                    conversation_metadata[username]['occupation'] = kernel.getPredicate('occupation', session_id)

                if len(kernel.getPredicate('age', session_id)) <= 0:
                    conversation_metadata[username]['age'] = ""
                else:
                    conversation_metadata[username]['age'] = kernel.getPredicate('age', session_id)

            write_dictionary(conversation_metadata, conversation_metadata_filename)

            print kernel.getPredicate('occupation', session_id)
            print kernel.getPredicate('age', session_id)

            if len(kernel.getPredicate('occupation', session_id)) and len(kernel.getPredicate('age', session_id)):
                if randint(0, 1) == 0:
                    print (specialized_bot.respond(kernel.getPredicate('occupation', session_id)))
                else:
                    print (specialized_bot.respond(map_age(kernel.getPredicate('age', session_id))))

            else:
                print (bot_response)
