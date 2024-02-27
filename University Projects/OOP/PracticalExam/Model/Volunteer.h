#pragma once
#include <string>
#include <vector>
#include "Department.h"

class Volunteer
{
private:
	string name;
	string email;
	vector<string> interests;
	Department department;
public:
	Volunteer()
	{
		this->name = ""; this->email = "";
	}

	string getName() { return this->name; }
	string getEmail() { return this->email; }
	vector<string> getInterests() { return this->interests; }
	Department getDepartment() { return this->department; }

	void setName(string name) { this->name = name; }
	void setEmail(string email) { this->email = email; }
	void setInterests(vector<string> interests) { this->interests = interests; }
	void setDepartment(Department dep) { this->department = dep; }

	string toString();
};

