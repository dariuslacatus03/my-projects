#pragma once
#include <string>

class Writer
{
private:
	std::string name;
	std::string expertise;


public:
	Writer() { this->name = " "; this->expertise = " "; }

	std::string getName() { return this->name; }
	std::string getExpertise() { return this->expertise; }

	void setName(std::string name) { this->name = name; }
	void setExpertise(std::string expertise) { this->expertise = expertise; }

};

