# Eliza Chatbot Replica 

Eliza Chat is a server-side driven chatbot application built using Spring Boot. The application simulates a conversation with Eliza, a Rogerian psychotherapist, and provides responses based on user input.

## Features

- **User Greeting:** On accessing the application, users are greeted and prompted to enter their name.
- **Dynamic Responses:** Eliza provides randomized responses based on keywords from user input.
- **Conversation History:** The chat maintains a history of the conversation, displaying both the user's messages and Eliza's responses.
- **No Repetition:** Responses are randomized, ensuring no response is repeated until all responses are given at least once.
- **Reset Functionality:** Users can reset the chat at any point, which clears the conversation history and takes them back to the initial greeting.
- **No Client-side JavaScript**: The application is purely server-side driven without any client-side JavaScript.
- **No CSS**: There's no styling applied; it's a simple and straightforward application.
- **JSON Driven**: Eliza's knowledge is based on a JSON file which can be swapped out for testing.
- **Session Management**: The application uses HTTP sessions to manage user state.

## How to Run

1. Clone the repo
    > `git clone [repository-url]`
2. Switch to directory where you cloned    
    > `cd [repository-directory]`

3. Run and build with Gradle:
    > `gradle bootrun`
4. Open a browser and go to: 
    >`http://localhost:8080/`.
5. Enter your name to start chatting with Eliza.

## How to Use

1. **Starting the Chat**: Enter your name to initiate the conversation. Eliza will greet you with a randomized greeting.
2. **Chatting**: Type your message into the input box and press "Send" to chat with Eliza. Eliza's responses are based on keywords in your message.
3. **Resetting the Chat**: If you wish to restart the conversation, click the "Reset" button.

## Implementation Details

- **Keyword Matching**: Eliza's responses are based on keywords found in the user's input. The keywords and corresponding responses are defined in a JSON file.
- **Randomized Responses**: Responses are randomized to ensure variety in the conversation. The application ensures that the same response isn't repeated until all responses have been used at least once.
- **Default Responses**: If no keywords are matched, Eliza provides a default response.

