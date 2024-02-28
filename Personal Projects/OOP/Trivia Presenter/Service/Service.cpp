#include "Service.h"

void Service::updateScore(string userName, int scoreToAdd)
{
	for (Participant& p : this->getAllParticipants())
	{
		if (p.getName() == userName)
		{
			p.setScore(p.getScore() + scoreToAdd);
		}
	}
}
