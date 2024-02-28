#include "Repository.h"
#include <fstream>
#include <sstream>

vector<string> separateAttributes(string line, char separator)
{
	vector<string> sepAtts;
	stringstream lineTS(line);
	string attr;
	while (getline(lineTS, attr, separator))
	{
		sepAtts.push_back(attr);
	}
	return sepAtts;
}

void Repository::readFromFile()
{
	ifstream participantsFile("Participants.txt");
	ifstream questionsFile("Questions.txt");
	string line;
	vector<string> sepAtts;
	//READ PARTICIPANTS
	Participant p;
	while(getline(participantsFile, line))
	{
		sepAtts = separateAttributes(line, '|');
		p.setName(sepAtts[0]);
		p.setScore(stoi(sepAtts[1]));
		this->participants.push_back(p);
	}
	//READ QUESTIONS
	Question q;
	while (getline(questionsFile, line))
	{
		sepAtts = separateAttributes(line, '|');
		q.setId(stoi(sepAtts[0]));
		q.setText(sepAtts[1]);
		q.setAnswer(sepAtts[2]);
		q.setScore(stoi(sepAtts[3]));
		this->questions.push_back(q);
	}
	questionsFile.close();
	participantsFile.close();
}

void Repository::writeToFile()
{
	ofstream writeQuestions("Questions.txt");
	for (Question q : this->getQuestions())
	{
		writeQuestions << q.getId() << "|" << q.getText() << "|" << q.getAnswer() << "|" << q.getScore() << "\n";
	}
	writeQuestions.close();
}
