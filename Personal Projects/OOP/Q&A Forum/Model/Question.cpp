#include "Question.h"
#include <stdlib.h>

Question::Question(int id, std::string text, std::string userWhoAsked)
{
	this->id = id;
	this->questionText = text;
	this->userWhoAsked = userWhoAsked;
}

std::string Question::toString()
{
	return std::to_string(this->id) + " | " + this->questionText + " | " + this->userWhoAsked;
}
