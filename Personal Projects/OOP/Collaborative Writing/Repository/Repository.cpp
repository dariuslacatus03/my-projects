#include "Repository.h"
#include <fstream>
#include <sstream>

std::vector<std::string> separateAtts(std::string line, char separator)
{
	std::vector<std::string> sepAtts;
	std::stringstream sepLine(line);
	std::string sepString;
	while (getline(sepLine, sepString, separator))
	{
		sepAtts.push_back(sepString);
	}
	return sepAtts;
}

void Repository::readFromFile()
{
	std::ifstream writersFile("Writers.txt"), ideasFile("Ideas.txt");
	std::string line;
	std::vector<std::string> sepAtts;
	
	//Read writers;
	Writer writer;
	while (getline(writersFile, line))
	{
		sepAtts = separateAtts(line, '|');
		writer.setName(sepAtts[0]);
		writer.setExpertise(sepAtts[1]);
		this->writers.push_back(writer);
	}
	//Read ideas;
	Idea idea;
	while (getline(ideasFile, line))
	{
		sepAtts = separateAtts(line, '|');
		idea.setDescription(sepAtts[0]);
		idea.setStatus(sepAtts[1]);
		idea.setCreator(sepAtts[2]);
		idea.setAct(stoi(sepAtts[3]));
		this->ideas.push_back(idea);
	}
	writersFile.close();
	ideasFile.close();
}

void Repository::writeToFile()
{
	std::ofstream plotFile("Plot.txt");
	plotFile << "Act 1:\n";
	for (Idea idea : this->ideas)
	{
		if (idea.getStatus() == "accepted" && idea.getAct() == 1)
		{
			plotFile << idea.getDescription() << "(" << idea.getCreator() << ")\n";
		}
	}
	plotFile << "Act 2:\n";
	for (Idea idea : this->ideas)
	{
		if (idea.getStatus() == "accepted" && idea.getAct() == 2)
		{
			plotFile << idea.getDescription() << "(" << idea.getCreator() << ")\n";
		}
	}
	plotFile << "Act 3:\n";
	for (Idea idea : this->ideas)
	{
		if (idea.getStatus() == "accepted" && idea.getAct() == 3)
		{
			plotFile << idea.getDescription() << "(" << idea.getCreator() << ")\n";
		}
	}
	plotFile.close();
}
