#pragma once
#include "Repository.h"

class Service
{
private:
	Repository repository;

public:

	std::vector<Writer>& getAllWriters() { return this->repository.getWriters(); }
	std::vector<Idea>& getAllIdeas() { return this->repository.getIdeas(); }

	void addIdea(Idea idea) { this->repository.addIdea(idea); }
	void updateStatus(Idea& idea, std::string status) { this->repository.updateStatus(idea, status); }
	void savePlot() { this->repository.writeToFile(); }

	void sortIdeasByAct();
};

