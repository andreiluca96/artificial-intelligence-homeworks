import aiml

kernel = aiml.Kernel()
kernel.learn("std-startup.xml")
kernel.respond("load aiml b")

while True:
    message = raw_input("Enter your message to the bot: ")
    if message == "quit":
        exit()
    else:
        bot_response = kernel.respond(message)
        print (bot_response)