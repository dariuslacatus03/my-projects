#pragma once
#include <vector>
#include <utility>

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TElem;
typedef int TPriority;
typedef std::pair<TElem, TPriority> Element;
typedef bool (*Relation)(TPriority p1, TPriority p2);
#define NULL_TELEM pair<TElem, TPriority> (-11111,-11111)


class Node
{
private:
	Node* previousNode;
	Element element;
	Node* nextNode;
public:
	Node()
	{
		this->element = NULL_TELEM;
		this->previousNode = nullptr;
		this->nextNode = nullptr;
	}
	//Complexity: Theta(1)

	Node(Element& element)
	{
		this->element = element;
		this->previousNode = nullptr;
		this->nextNode = nullptr;
	}
	//Complexity: Theta(1)

	Element& getElement()
	{
		return this->element;
	}
	//Complexity: Theta(1)

	Node* next()
	{
		return this->nextNode;
	}
	//Complexity: Theta(1)

	Node* previous()
	{
		return this->previousNode;
	}
	//Complexity: Theta(1)

	void setElement(Element& elementToSet)
	{
		this->element = elementToSet;
	}
	//Complexity: Theta(1)

	void setNext(Node* pointerToNextNode)
	{
		this->nextNode = pointerToNextNode;
	}
	//Complexity: Theta(1)

	void setPrevious(Node* pointerToPreviousNode)
	{
		this->previousNode = pointerToPreviousNode;
	}
	//Complexity: Theta(1)
};


class PriorityQueue {
private:
	//TODO - Representation
	Node* head;
	Node* tail;
	int size;
	Relation relation;
public:
	//implicit constructor
	PriorityQueue(Relation r);

	//pushes an element with priority to the queue
	void push(TElem e, TPriority p);

	//returns the element with the highest priority with respect to the order relation
	//throws exception if the queue is empty
	Element top()  const;

	//removes and returns the element with the highest priority
	//throws exception if the queue is empty
	Element pop();

	//checks if the queue is empty
	bool isEmpty() const;

	//returns the priority of elem. If elem appears multiple times the priority of one of them should be returned.
	//if elem is not in the PriorityQueue, return -1.
	TPriority priorityOf(TElem element);

	//destructor
	~PriorityQueue();

};

