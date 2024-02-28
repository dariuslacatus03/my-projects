#pragma once
#include <string>

class Question
{
private:
	int id;
	std::string questionText;
	std::string userWhoAsked;

public:
	Question(int id, std::string text, std::string userWhoAsked);

	int getId() { return this->id; }
	std::string getText() { return this->questionText; }
	std::string getUserWhoAsked() { return this->userWhoAsked; }

	void setId(int id) { this->id = id; }
	void setQuestionText(std::string questionText) { this->questionText = questionText; }
	void setUserWhoAsked(std::string user) { this->userWhoAsked = user; }

	std::string toString();
};

