#pragma once
#include <string>

using namespace std;


class Participant
{
private:
	string name;
	int score;
public:
	Participant()
	{
		this->name = ""; this->score = 0;
	}

	string getName() { return this->name; }
	int getScore() { return this->score; }

	void setName(string name) { this->name = name; }
	void setScore(int score) { this->score = score; }

};

