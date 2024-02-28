#include "Service.h"
#include <algorithm>

void Service::sortIdeasByAct()
{
	std::vector<Idea>& sortedIdeas = this->getAllIdeas();

	sort(sortedIdeas.begin(), sortedIdeas.end(), [](Idea i1, Idea i2)
		{
			return i1.getAct() < i2.getAct();
		});
}
