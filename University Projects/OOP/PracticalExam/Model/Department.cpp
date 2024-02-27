#include "Department.h"

bool Department::operator==(Department d1)
{
    return this->getName() == d1.getName() && this->getDescription() == this->getDescription();
}
