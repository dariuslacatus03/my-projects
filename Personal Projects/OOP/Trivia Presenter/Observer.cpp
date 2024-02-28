#include "Observer.h"

void Observable::addObserver(Observer* o)
{
	this->observers.push_back(o);
}
