#pragma once
#include "User.h"
#include "Answer.h"
#include "Question.h"
#include <vector>

class Repository
{
private:
	std::vector<User> users;
	std::vector<Question> questions;
	std::vector<Answer> answers;

public:
	Repository() { this->readFromFile(); };

	std::vector<User>& getAllUsers() { return this->users; }
	std::vector<Question>& getAllQuestions() { return this->questions; }
	std::vector<Answer>& getAllAnswers() { return this->answers; }

	void addQuestion(Question question) { this->questions.push_back(question); }
	void addAnswer(Answer answer) { this->answers.push_back(answer); }
	void readFromFile();
};

