#pragma once
#include "Repository.h"
#include "Observer.h"

class Service : public Observable
{
private:
	Repository repository;

public:
	
	vector<Department>& getAllDepartments() { return this->repository.getDepartments(); }
	vector<Volunteer>& getAllVolunteers() { return this->repository.getVolunteers(); }

	void updateVolFile() { this->repository.writeToFile(); }
};

