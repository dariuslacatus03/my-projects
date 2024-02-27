#pragma once

#include "Volunteer.h"

class Repository
{
private:
	vector<Department> departments;
	vector<Volunteer> volunteers;
public:
	Repository() { this->readFromFile(); }

	vector<Department>& getDepartments() { return this->departments; }
	vector<Volunteer>& getVolunteers() { return this->volunteers; }

	void readFromFile();
	void writeToFile();
};

