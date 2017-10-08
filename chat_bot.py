import aiml

kernel = aiml.Kernel()
kernel.learn("std-startup.xml")

while True:
    message = raw_input("Enter your message to the bot: ")
    if message == "quit":
        exit()
    else:
        bot_response = kernel.respond(message)