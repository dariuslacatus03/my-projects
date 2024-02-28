#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;


MultiMap::MultiMap() {
	
	this->capacity = 100;
	this->numberOfElements = 0;

	this->hashTable = new Node*[this->capacity];

	for (int i = 0; i < capacity; i++)
		this->hashTable[i] = nullptr;
}
//Theta(capacity)

int MultiMap::hashFunction(const TKey& key, int capacity) const
{
	int code = static_cast<int>(key);
	int position = abs(code % capacity);
	return position;
}
//Theta(1)


void MultiMap::add(TKey c, TValue v) {

	if ((double)this->size() / (double)this->capacity > 0.7)
	{
		Node** newHashTable = new Node*[this->capacity * 2];
		for (int i = 0; i < this->capacity * 2; i++)
			newHashTable[i] = nullptr;

		MultiMapIterator it = this->iterator();
		while (it.valid())
		{
			int newPosition = this->hashFunction(it.getCurrent().first, this->capacity * 2); // ERROR RESIZE ADD LA GET CURRENT
			
			Node* nodeCopy = new Node;
			nodeCopy->element.first = it.getCurrent().first;
			nodeCopy->element.second = it.getCurrent().second;
			nodeCopy->next = nullptr;

			if (newHashTable[newPosition] == nullptr)
			{
				newHashTable[newPosition] = nodeCopy;
			}
			else
			{
				Node* lastNodeOnBucketCopy = newHashTable[newPosition];
				while (lastNodeOnBucketCopy->next != nullptr)
					lastNodeOnBucketCopy = lastNodeOnBucketCopy->next;
				lastNodeOnBucketCopy->next = nodeCopy;
			}

			it.next();
		}

		//FREE SPACE
		Node* currentNode = nullptr;
		int currentBucket = 0;
		while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
		{
			currentBucket++;
		}
		currentNode = this->hashTable[currentBucket];
		Node* anteriorNode = nullptr;

		while (currentNode != nullptr)
		{
			anteriorNode = currentNode;
			if (currentNode->next != nullptr)
				currentNode = currentNode->next;
			else
			{
				if (currentBucket == this->capacity - 1)
					currentNode = nullptr;
				else
				{
					currentBucket++;
					while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
					{
						currentBucket++;
					}
					currentNode = this->hashTable[currentBucket];
				}
			}
			delete anteriorNode;
		}
		delete[] this->hashTable;

		this->hashTable = newHashTable;
		this->capacity = this->capacity * 2;
	}
	
	int position = this->hashFunction(c, this->capacity);
	
	Node* nodeToAdd = new Node;
	nodeToAdd->element.first = c;
	nodeToAdd->element.second = v;
	nodeToAdd->next = nullptr;

	if (this->hashTable[position] == nullptr)
	{
		this->hashTable[position] = nodeToAdd;
	}
	else
	{
		Node* lastNodeOnBucket = this->hashTable[position];
		while (lastNodeOnBucket->next != nullptr)
			lastNodeOnBucket = lastNodeOnBucket->next;
		lastNodeOnBucket->next = nodeToAdd;
	}
	this->numberOfElements++;
}
//Best case: Theta(1), Worst Case: Theta(capacity+capacity*2), Total Case: O(capacity+capacity*2)

bool MultiMap::remove(TKey c, TValue v) {
	
	vector<TValue> valuesOfKey = this->search(c);
	if (valuesOfKey.size() == 0)
	{
		return false;
	}
	else
	{
		int ok = 0;
		for (TValue tvalue : valuesOfKey)
		{
			if (tvalue == v)
			{
				ok = 1;
			}
		}
		if (ok == 0)
			return false;
	}

	Node* currentNode = nullptr;
	int bucket = this->hashFunction(c, this->capacity);
	currentNode = this->hashTable[bucket];
	Node* anteriorNode = nullptr;

	while (currentNode->element.second != v)
	{
		anteriorNode = currentNode;
		currentNode = currentNode->next;
	}
	if (currentNode->next == nullptr && anteriorNode == nullptr)
	{
		delete currentNode;
		this->hashTable[bucket] = nullptr;
	}
	else if (currentNode->next != nullptr && anteriorNode == nullptr)
	{
		this->hashTable[bucket] = currentNode->next;
		delete currentNode;
	}
	else if (currentNode->next != nullptr && anteriorNode != nullptr)
	{
		anteriorNode->next = currentNode->next;
		delete currentNode;
	}
	else if (currentNode->next == nullptr && anteriorNode != nullptr)
	{
		anteriorNode->next = nullptr;
		delete currentNode;
	}
	this->numberOfElements--;
	return true;
}
//Best case: Theta(1), Worst Case: Theta(capacity+capacity), Total Case: O(capacity+capacity)


vector<TValue> MultiMap::search(TKey c) const {
	
	vector<TValue> valuesOfKey;
	int hashCode = this->hashFunction(c, this->capacity);
	Node* node = this->hashTable[hashCode];
	if (node == nullptr)
		return valuesOfKey;
	while (node->next != nullptr)
	{
		if (node->element.first == c)
			valuesOfKey.push_back(node->element.second);
		node = node->next;
	}
	if (node->element.first == c)
		valuesOfKey.push_back(node->element.second);
	return valuesOfKey;
}
//Best case: Theta(1), Worst Case: Theta(capacity), Total Case: O(capacity)


int MultiMap::size() const {
	
	return this->numberOfElements;
}
//Theta(1)

bool MultiMap::isEmpty() const {
	
	return this->numberOfElements == 0;
}
//Theta(1)

MultiMapIterator MultiMap::iterator() const {
	return MultiMapIterator(*this);
}


void MultiMap::filter(Condition cond)
{
	Node* currentNode = nullptr;
	int currentBucket = 0;
	while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
	{
		currentBucket++;
	}
	currentNode = this->hashTable[currentBucket];
	Node* anteriorNode = nullptr;

	while (currentNode != nullptr)
	{
		anteriorNode = currentNode;
		if (currentNode->next != nullptr)
			currentNode = currentNode->next;
		else
		{
			if (currentBucket == this->capacity - 1)
				currentNode = nullptr;
			else
			{
				currentBucket++;
				while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
				{
					currentBucket++;
				}
				currentNode = this->hashTable[currentBucket];
			}
		}
		if (cond(anteriorNode->element.first) == false)
			this->remove(anteriorNode->element.first, anteriorNode->element.second);
	}
}
//Theta(n)


MultiMap::~MultiMap() {
	
	Node* currentNode = nullptr;
	int currentBucket = 0;
	while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
	{
		currentBucket++;
	}
	currentNode = this->hashTable[currentBucket];
	Node* anteriorNode = nullptr;
	
	while (currentNode != nullptr)
	{
		anteriorNode = currentNode;
		if (currentNode->next != nullptr)
			currentNode = currentNode->next;
		else
		{
			if (currentBucket == this->capacity - 1)
				currentNode = nullptr;
			else
			{
				currentBucket++;
				while (this->hashTable[currentBucket] == nullptr && currentBucket < this->capacity - 1)
				{
					currentBucket++;
				}
				currentNode = this->hashTable[currentBucket];
			}
		}
		delete anteriorNode;
	}

	delete[] this->hashTable;
}
//Best case: Theta(1), Worst Case: Theta(capacity), Total Case: O(capacity)

