#pragma once
#include <string>

using namespace std;


class Department
{
private:
	string name;
	string description;
public:
	Department() { this->name = ""; this->description = ""; }

	string getName() { return this->name; }
	string getDescription() { return this->description; }
	void setName(string name) { this->name = name; }
	void setDescription(string desc) { this->description = desc; }

	bool operator==(Department d1);

	string toString() { return this->name + " | " + this->description; }
};

