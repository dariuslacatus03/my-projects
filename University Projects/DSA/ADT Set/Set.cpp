#include "Set.h"
#include "SetITerator.h"
#pragma warning(disable:6385)

Set::Set() {
	
	this->elements = new TElem[capacity];
	this->next = new int[capacity];
	for (int i = 0; i < this->capacity - 1; i++)
	{
		this->next[i] = i + 1;
	}
	this->next[this->capacity - 1] = -1;
	this->head = -1;
	this->length = 0;
	this->firstFree = 0;
}


int Set::allocateP() {

	if (this->firstFree == -1)
	{
		//reallocate, copy, re-initialize free space
		this->capacity = this->capacity * 2;
		TElem* newSpaceForElements = new TElem[this->capacity];
		int* newSpaceForNext = new int[this->capacity];

		for (int i = 0; i < this->length; i++)
		{
			newSpaceForElements[i] = this->elements[i];
			newSpaceForNext[i] = this->next[i];
		}
		for (int i = length; i < this->capacity - 1; i++)
		{
			newSpaceForNext[i] = i + 1;
		}
		newSpaceForNext[this->capacity - 1] = -1;
		this->firstFree = this->length;

		delete[] this->elements;
		delete[] this->next;

		this->elements = newSpaceForElements;
		this->next = newSpaceForNext;
	}
	int newPosition = this->firstFree;
	this->firstFree = this->next[this->firstFree];

	return newPosition;
}
//Best Case: Theta(1), Worst Case: Theta(capacity*2), Total Case: O(capacity*2)

void Set::freeP(int position) {

	next[position] = firstFree;
	firstFree = position;
}
//Theta(1)

bool Set::add(TElem elem) {

	if (this->search(elem) == true)
		return false;

	int newPosition = allocateP();
	this->elements[newPosition] = elem;
	this->next[newPosition] = this->head;
	this->head = newPosition;
	this->length++;

	return true;
}
//Best Case: Theta(1), Worst Case: Theta(n+capacity*2), Total Case: O(n+capacity*2)


bool Set::remove(TElem elem) {
	
	int current = head;
	int previous = -1;

	while (current != -1 && this->elements[current] != elem) {
		previous = current;
		current = this->next[current];
	}

	if (current != -1)
	{
		if (current == this->head)
			this->head = this->next[this->head];
		else
			this->next[previous] = this->next[current];

		freeP(current);
		this->length--;

		return true;
	}
	else
		return false;
}
//Best case: Theta(1), Worst Case: Theta(n), Total Case: O(n)


bool Set::search(TElem elem) const {
	
	int current = this->head;
	while (current != -1 && this->elements[current] != elem)
	{
		current = this->next[current];
	}
	if (current != -1)
		return true;

	return false;
}
//Best case: Theta(1), Worst Case: Theta(n), Total Case: O(n)


int Set::size() const {
	
	return this->length;
}
//Theta(1)


bool Set::isEmpty() const {
	
	if (this->length == 0)
		return true;
	return false;
}
//Theta(1)


int Set::getRange() const
{
	if (this->isEmpty() == true)
		return -1;
	int current = this->head, max = this->elements[this->head], min = this->elements[this->head];
	while (current != -1)
	{
		if (this->elements[current] > max)
			max = this->elements[current];
		if (this->elements[current] < min)
			min = this->elements[current];
		current = this->next[current];
	}
	return max - min;
}
//Theta(n)


Set::~Set() {
	
	delete[] this->elements;
	delete[] this->next;
}
//Theta(1)


SetIterator Set::iterator() const {
	return SetIterator(*this);
}
//Theta(1)


