#include "Volunteer.h"

string Volunteer::toString()
{
	string volString;
	volString = this->name + " | " + this->email + " | ";
	for (string i : this->interests)
	{
		volString = volString + i + ",";
	}
	volString = volString + " | " + this->department.toString();
	return volString;
}
