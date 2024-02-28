#include "Tests.h"
#include <assert.h>

void Tests::testAdd()
{
	Repository repository;
	Idea idea;
	repository.addIdea(idea);
	assert(repository.getIdeas().size() == 11);
	assert(repository.getIdeas().at(10).getAct() == 0);
	repository.addIdea(idea);
	assert(repository.getIdeas().size() == 12);
	assert(repository.getIdeas().at(11).getAct() == 0);
	repository.addIdea(idea);
	assert(repository.getIdeas().size() == 13);
	assert(repository.getIdeas().at(12).getAct() == 0);
}

void Tests::testUpdate()
{
	Repository repository;
	Idea idea;
	repository.addIdea(idea);
	repository.updateStatus(repository.getIdeas()[10], "accepted");
	assert(repository.getIdeas()[10].getStatus() == "accepted");
	Idea idea2;
	repository.addIdea(idea2);
	repository.updateStatus(repository.getIdeas()[11], "accepted");
	assert(repository.getIdeas()[11].getStatus() == "accepted");
}
