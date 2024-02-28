#pragma once
#include "Repository.h"
#include "Observer.h"


class Service : public Observable
{
private:
	Repository repository;

public:
	std::vector<User>& getAllUsers() { return this->repository.getAllUsers(); }
	std::vector<Question>& getAllQuestions() { return this->repository.getAllQuestions(); }
	std::vector<Answer>& getAllAnswers() { return this->repository.getAllAnswers(); }

	int getNumberOfAnswers(Question question);
	void sortQuestions();
	void addQuestion(Question question) { this->repository.addQuestion(question); }
	void addAnswer(Answer answer) { this->repository.addAnswer(answer); }
};

