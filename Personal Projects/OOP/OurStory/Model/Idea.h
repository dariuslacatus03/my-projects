#pragma once
#include <string>

class Idea
{
private:
	std::string description;
	std::string status;
	std::string creator;
	int act;

public:
	Idea() { this->description = " "; this->status = " "; this->creator = " "; this->act = 0; }
	
	std::string getDescription() { return this->description; }
	std::string getStatus() { return this->status; }
	std::string getCreator() { return this->creator; }
	int getAct() { return this->act; }

	void setDescription(std::string description) { this->description = description; }
	void setStatus(std::string status) { this->status = status; }
	void setCreator(std::string creator) { this->creator = creator; }
	void setAct(int act) { this->act = act; }

	std::string toString() { return description + " | " + status + " | " + creator + " | " + std::to_string(act); }
};

