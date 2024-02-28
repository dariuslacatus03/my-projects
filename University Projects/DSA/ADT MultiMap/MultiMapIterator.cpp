#include "MultiMapIterator.h"
#include "MultiMap.h"


MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) {

	this->currentBucket = 0;
	while (this->col.hashTable[currentBucket] == nullptr && this->currentBucket < this->col.capacity - 1)
	{
		this->currentBucket++;
	}
	this->currentNode = this->col.hashTable[currentBucket];
}
//Best case: Theta(1), Worst Case: Theta(capacity), Total Case: O(capacity)


TElem MultiMapIterator::getCurrent() const{
	
	if (!this->valid())
		throw std::exception("Invalid position for the iterator.\n");
	return this->currentNode->element;
}
//Theta(1)

bool MultiMapIterator::valid() const {
	
	return this->currentNode != nullptr;
}
//Theta(1)

void MultiMapIterator::next() {
	
	if (!this->valid())
		throw std::exception("Invalid position for the iterator.\n");
	if (this->currentNode->next != nullptr)
		this->currentNode = this->currentNode->next;
	else
	{
		if (this->currentBucket == this->col.capacity - 1)
			this->currentNode = nullptr;
		else
		{
			this->currentBucket++;
			while (this->col.hashTable[currentBucket] == nullptr && this->currentBucket < this->col.capacity - 1)
			{
				this->currentBucket++;
			}
			this->currentNode = this->col.hashTable[currentBucket];
		}
	}
}
//Best case: Theta(1), Worst Case: Theta(capacity), Total Case: O(capacity)

void MultiMapIterator::first() {
	
	this->currentBucket = 0;
	while (this->col.hashTable[currentBucket] == nullptr && this->currentBucket < this->col.capacity)
	{
		this->currentBucket++;
	}
	this->currentNode = this->col.hashTable[currentBucket];
}
//Best case: Theta(1), Worst Case: Theta(capacity), Total Case: O(capacity)
