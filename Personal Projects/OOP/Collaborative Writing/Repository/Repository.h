#pragma once
#include <vector>
#include "Idea.h"
#include "Writer.h"


class Repository
{
private:
	std::vector<Writer> writers;
	std::vector<Idea> ideas;

public:
	Repository() { this->readFromFile(); }

	std::vector<Writer>& getWriters() { return this->writers; }
	std::vector<Idea>& getIdeas() { return this->ideas; }
	
	void addIdea(Idea idea) { this->ideas.push_back(idea); }
	void updateStatus(Idea& idea, std::string status) { idea.setStatus(status); }

	void readFromFile();
	void writeToFile();
};

