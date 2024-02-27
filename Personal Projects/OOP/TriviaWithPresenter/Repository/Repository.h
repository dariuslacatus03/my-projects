#pragma once
#include "Question.h"
#include "Participant.h"
#include <vector>

class Repository
{
private:
	vector<Question> questions;
	vector<Participant> participants;

public:
	Repository() { this->readFromFile(); }

	vector<Question>& getQuestions(){ return this->questions; }
	vector<Participant>& getParticipants() { return this->participants; }

	void readFromFile();
	void writeToFile();
};

