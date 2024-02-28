#pragma once
#include "Repository.h"
#include "Observer.h"

class Service : public Observable
{
private:
	Repository repository;

public:
	
	vector<Participant>& getAllParticipants() { return this->repository.getParticipants(); }
	vector<Question>& getAllQuestions() { return this->repository.getQuestions(); }

	void addQuestion(Question q) { this->repository.getQuestions().push_back(q); }
	void updateScore(string userName, int scoreToAdd);


	void updateFile() { this->repository.writeToFile(); }
};

