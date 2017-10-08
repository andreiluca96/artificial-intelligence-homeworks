import aiml

from storage import load_dictionary, write_dictionary

kernel = aiml.Kernel()
kernel.learn("std-startup.xml")
kernel.respond("load aiml b")

conversation_metadata_filename = "conversation_metadata.yaml"

session_id = 12345

while True:
    message = raw_input("Enter your message to the bot: ")

    conversation_metadata = load_dictionary(conversation_metadata_filename)
    if conversation_metadata is None:
        conversation_metadata = dict()
    if message == "quit":
        exit()
    else:
        bot_response = kernel.respond(message, session_id)

        print kernel.getPredicate('username', session_id)
        print kernel.getPredicate('occupation', session_id)
        print kernel.getPredicate('age', session_id)


        if len(kernel.getPredicate('username', session_id)) > 0:
            conversation_metadata[kernel.getPredicate('username', session_id)] = dict()
            if len(kernel.getPredicate('occupation', session_id)) <= 0:
                conversation_metadata[kernel.getPredicate('username', session_id)]['occupation'] = ""
            else:
                conversation_metadata[kernel.getPredicate('username', session_id)]['occupation'] = kernel.getPredicate('occupation', session_id)

            if len(kernel.getPredicate('age', session_id)) <= 0:
                conversation_metadata[kernel.getPredicate('username', session_id)]['age'] = ""
            else:
                conversation_metadata[kernel.getPredicate('username', session_id)]['age'] = kernel.getPredicate('age', session_id)
        print (conversation_metadata)
        write_dictionary(conversation_metadata, conversation_metadata_filename)

        print (bot_response)
