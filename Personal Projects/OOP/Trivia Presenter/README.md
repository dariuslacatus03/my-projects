# Trivia Presenter Application with Qt

## Description

Welcome to the Trivia Presenter Application, a C++ application built with the Qt framework for having fun with little trivia sessions.

### Key Features

1. **Presenter Widget:**
   - Upon running the application, a Presenter widget is displayed, serving as the central control hub for the trivia session.

2. **Participant Windows:**
   - N individual participant windows are generated, one for each participant, allowing them to answer trivia questions in real-time.

3. **Dynamic Scoring:**
   - Participants can answer questions within their respective windows, with correct answers resulting in earned points and the question turning green.
   - Incorrect answers will mark the question in red, providing instant feedback.

4. **Question Addition:**
   - The presenter can add new questions dynamically during the trivia session.
   - Leveraging the observer pattern, newly added questions seamlessly appear in every participant's window, ensuring synchronized engagement.

### Data Input

- The application reads participant information from the "participants.txt" file and trivia questions with scores from the "questions.txt" file.
- This modular approach allows flexibility in managing participants and customizing trivia content.

### Getting Started

1. Ensure you have the necessary dependencies, including Qt.
2. Compile and run the application.
3. Explore the Presenter widget to manage the trivia session.
4. Participants use their individual windows to answer questions, receiving immediate feedback.
