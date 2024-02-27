#include "Repository.h"
#include <fstream>
#include <sstream>

std::vector<std::string> separateAttributes(std::string line, char separator)
{
	std::vector<std::string> separatedAttributes;
	std::stringstream lineToSeparate(line);
	std::string separatedAttribute;
	while (getline(lineToSeparate, separatedAttribute, separator))
	{
		separatedAttributes.push_back(separatedAttribute);
	}
	return separatedAttributes;
}

void Repository::readFromFile()
{
	std::ifstream usersFile("Users.txt"), questionsFile("Questions.txt"), answersFile("Answers.txt");
	std::string line;
	std::vector<std::string> separatedAttributes;
	//Read users
	User userToAdd(" ");
	while (getline(usersFile, line))
	{
		separatedAttributes = separateAttributes(line, '|');
		userToAdd.setName(separatedAttributes[0]);
		this->users.push_back(userToAdd);
	}

	//Read questions
	Question questionToAdd(0, " ", " ");
	while (getline(questionsFile, line))
	{
		separatedAttributes = separateAttributes(line, '|');
		questionToAdd.setId(stoi(separatedAttributes[0]));
		questionToAdd.setQuestionText(separatedAttributes[1]);
		questionToAdd.setUserWhoAsked(separatedAttributes[2]);
		this->questions.push_back(questionToAdd);
	}

	//Read answers
	Answer answerToAdd(0, 0, " ", " ", 0);
	while (getline(answersFile, line))
	{
		separatedAttributes = separateAttributes(line, '|');
		answerToAdd.setAnswerId(stoi(separatedAttributes[0]));
		answerToAdd.setQuestionId(stoi(separatedAttributes[1]));
		answerToAdd.setUserWhoAsked(separatedAttributes[2]);
		answerToAdd.setAnswerText(separatedAttributes[3]);
		answerToAdd.setVotes(stoi(separatedAttributes[4]));
		this->answers.push_back(answerToAdd);
	}
}
