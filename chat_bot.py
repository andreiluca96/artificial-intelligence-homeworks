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

    if message == "quit":
        exit()
    else:
        bot_response = kernel.respond(message, session_id)

        if kernel.getBotPredicate('name') is not None:
            conversation_metadata[kernel.getBotPredicate('name')]['occupation'] = kernel.getBotPredicate('occupation')
            conversation_metadata[kernel.getBotPredicate('name')]['age'] = kernel.getBotPredicate('age')

        write_dictionary(conversation_metadata, conversation_metadata_filename)


        print (bot_response)