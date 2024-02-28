# Q&A Forum Application with Qt

## Description

Welcome to the Q&A Forum Application, a C++ application built with the Qt framework, fostering collaborative and interactive question and answer sessions.

### Key Features

1. **Individual User Windows:**
   - Upon starting the application, N individual windows are generated, one for each user, providing a personalized space for interaction.

2. **Global Q&A Feed:**
   - Users can view a comprehensive list of questions from the "questions.txt" file, fostering a shared knowledge space.

3. **Answering Questions:**
   - Users can click on a question to view all existing answers and provide their responses.

4. **Upvoting and Downvoting:**
   - Users have the ability to upvote or downvote answers, allowing for community-driven recognition of valuable contributions.

5. **Search Widget:**
   - The application features a search widget that enables users to search for specific questions, enhancing the efficiency of information retrieval. The widget will also display the top 3 answers for that question.

6. **Observer Pattern Implementation:**
   - Leveraging the observer pattern, any new answer posted triggers real-time updates in all user windows.

7. **Signal-Slot Mechanism Implementation:**
   - This time, for practicing purposes, for the new questions added, a signal-slot mechanism was implemented.
   - The signal-slot mechanism and the observer pattern ensures a synchronized and updated Q&A environment for all participants.

### Data Input

- The application reads user information from the "users.txt" file, questions from the "questions.txt" file, and answers from the "answers.txt" file.
- This modular approach facilitates easy customization of user profiles and Q&A content.

### Getting Started

1. Ensure you have the necessary dependencies, including Qt.
2. Compile and run the application.
3. Explore your personalized user window to engage with questions and answers.
4. Utilize the search widget to efficiently find specific questions.
5. Contribute to discussions by providing answers, upvoting valuable responses, or posting new questions.

