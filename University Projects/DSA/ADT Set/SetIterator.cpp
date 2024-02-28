#include "SetIterator.h"
#include "Set.h"
#include <exception>


SetIterator::SetIterator(const Set& m) : set(m)
{
	this->current = this->set.head;
}
//Theta(1)

void SetIterator::first() {
	
	this->current = this->set.head;
}
//Theta(1)


void SetIterator::next() {
	
	if (!this->valid())
		throw std::exception("Invalid iterator.\n\n");
	this->current = this->set.next[current];
}
//Theta(1)


TElem SetIterator::getCurrent()
{
	if (!this->valid())
		throw std::exception("Invalid iterator.\n\n");
	return this->set.elements[current];
}
//Theta(1)


bool SetIterator::valid() const {
	
	return this->current != -1;
}
//Theta(1)



