#include "Answer.h"

Answer::Answer(int ansId, int qstId, std::string userWhoAnswered, std::string answer, int votes)
{
	this->answerId = ansId;
	this->questionId = qstId;
	this->userWhoAswered = userWhoAnswered;
	this->answerText = answer;
	this->votes = votes;
}
