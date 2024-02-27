#pragma once
#include <string>

class Answer
{
private:
	int answerId;
	int questionId;
	std::string userWhoAswered;
	std::string answerText;
	int votes;

public:
	Answer(int ansId, int qstId, std::string userWhoAnswered, std::string answer, int votes);

	int getAnswerId() { return this->answerId; }
	int getQuestionId() { return this->questionId; }
	std::string getUserWhoAsked() { return this->userWhoAswered; }
	std::string getAnswerText() { return this->answerText; }
	int getVotes() { return this->votes; }

	void setAnswerId(int id) { this->answerId = id; }
	void setQuestionId(int id) { this->questionId = id; }
	void setUserWhoAsked(std::string user) { this->userWhoAswered = user; }
	void setAnswerText(std::string text) { this->answerText = text; }
	void setVotes(int votes) { this->votes = votes; }

	std::string toString() { return std::to_string(answerId) + " | " + userWhoAswered + " | " + answerText + " | " + std::to_string(votes); }
};

