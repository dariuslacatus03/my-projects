#pragma once
#include<vector>
#include<utility>
//DO NOT INCLUDE MultiMapIterator
#include <iostream>
using namespace std;


//DO NOT CHANGE THIS PART
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111
#define NULL_TELEM pair<int,int>(-111111, -111111)

typedef bool (*Condition)(TKey);

class MultiMapIterator;

class MultiMap
{
	friend class MultiMapIterator;

private:
	//TODO - Representation
	struct Node
	{
		TElem element;
		Node* next;
	};

	Node** hashTable; // Pointer to an array of linked lists
	int capacity; // Total number of buckets (size of the array)
	int numberOfElements; // Total number of elements in the MultiMap

	int hashFunction(const TKey& key, int capacity) const;

public:
	//constructor
	MultiMap();

	//adds a key value pair to the multimap
	void add(TKey c, TValue v);

	//removes a key value pair from the multimap
	//returns true if the pair was removed (if it was in the multimap) and false otherwise
	bool remove(TKey c, TValue v);

	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty
	vector<TValue> search(TKey c) const;

	//returns the number of pairs from the multimap
	int size() const;

	//checks whether the multimap is empty
	bool isEmpty() const;

	//keeps in the MultiMap only those pairs whose key respects the given condition
	void filter(Condition cond);

	//returns an iterator for the multimap
	MultiMapIterator iterator() const;

	//descturctor
	~MultiMap();


};

