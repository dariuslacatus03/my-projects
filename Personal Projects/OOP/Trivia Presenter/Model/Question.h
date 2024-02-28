#pragma once
#include <string>

using namespace std;

class Question
{
private:
	int id;
	string text;
	string answer;
	int score;

public:
	Question()
	{
		this->id = 0; this->text = ""; this->answer = ""; this->score = 0;
	}

	int getId() { return this->id; }
	string getText() { return this->text; }
	string getAnswer() { return this->answer; }
	int getScore() { return this->score; }

	void setId(int id) { this->id = id; }
	void setText(string txt) { this->text = txt; }
	void setAnswer(string ans) { this->answer = ans; }
	void setScore(int score) { this->score = score; }

	string toStringAdmin()
	{
		return to_string(id) + " | " + text + " | " + answer + " | " + to_string(score);
	}
	string toStringUser()
	{
		return to_string(id) + " | " + text + " | " + to_string(score);
	}
};

