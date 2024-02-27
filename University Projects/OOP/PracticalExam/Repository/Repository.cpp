#include "Repository.h"
#include <fstream>
#include <sstream>
vector<string> separateAttributes(string line, char separator)
{
	vector<string> atts;
	stringstream lineSS(line);
	string word;
	while (getline(lineSS, word, separator))
	{
		atts.push_back(word);
	}
	return atts;
}

void Repository::readFromFile()
{
	ifstream depFile("Departments.txt"), volFile("Volunteers.txt");
	string line;
	vector<string> atts;
	//READ DEPARTMENTS
	Department department;
	while (getline(depFile, line))
	{
		atts = separateAttributes(line, '|');
		department.setName(atts[0]);
		department.setDescription(atts[1]);
		this->departments.push_back(department);
	}
	//READ VOLUNTEERS
	Volunteer volunteer;
	vector<string> interests;
	Department dep;
	while (getline(volFile, line))
	{
		atts = separateAttributes(line, '|');
		volunteer.setName(atts[0]);
		volunteer.setEmail(atts[1]);
		interests = separateAttributes(atts[2], ',');
		volunteer.setInterests(interests);
		dep.setName(atts[3]);
		dep.setDescription(atts[4]);
		volunteer.setDepartment(dep);
		this->volunteers.push_back(volunteer);
	}
	depFile.close();
	volFile.close();
}

void Repository::writeToFile()
{
	ofstream write("Volunteers.txt");
	for (Volunteer v : this->volunteers)
	{
		write << v.getName() << "|" << v.getEmail() << "|";
		for (string i : v.getInterests())
		{
			write << i << ",";
		}
		write << "|" << v.getDepartment().getName() << "|" << v.getDepartment().getDescription()<<"\n";
	}
}
