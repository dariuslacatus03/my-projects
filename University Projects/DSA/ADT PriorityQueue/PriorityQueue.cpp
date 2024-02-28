#include "PriorityQueue.h"
#include <exception>
using namespace std;


PriorityQueue::PriorityQueue(Relation r) {
	this->head = new Node;
	this->tail = new Node;
	this->size = 0;
	this->relation = r;
}
//Complexity: Theta(1)

void PriorityQueue::push(TElem e, TPriority p) {
	Node *currentNode = this->head;
	Element elementToPush(e, p);
	if (this->head->getElement() == NULL_TELEM)
	{
		this->head->setElement(elementToPush);
		this->tail = this->head;
	}
	else
	{
		Node* newNode = new Node;
		newNode->setElement(elementToPush);
		while (this->relation(currentNode->getElement().second, newNode->getElement().second))
		{
			currentNode = currentNode->next();
			if (currentNode == nullptr)
				break;
		}	
		if (currentNode == nullptr)
		{

			this->tail->setNext(newNode);
			newNode->setPrevious(this->tail);
			newNode->setNext(nullptr);
			this->tail = newNode;
		}
		else if (currentNode->previous() == nullptr)
		{
			this->head->setPrevious(newNode);
			newNode->setNext(this->head);
			newNode->setPrevious(nullptr);
			this->head = newNode;
		}
		else
		{
			newNode->setNext(currentNode);
			newNode->setPrevious(currentNode->previous());
			currentNode->previous()->setNext(newNode);
			currentNode->setPrevious(newNode);
		}
	}
	this->size++;
}
//Complexity: BC: Theta(1), WC:Theta(n), Total: O(n)


//throws exception if the queue is empty
Element PriorityQueue::top() const {
	if (this->size == 0)
		throw std::exception();
	return this->head->getElement();
}
//Complexity: Theta(1)

Element PriorityQueue::pop() {
	Element returnElement = this->head->getElement();
	Node* nodeToPop;
	if (this->size == 0)
		throw std::exception();
	if (this->size == 1)
	{
		this->head->setElement(NULL_TELEM);
		this->head->setNext(nullptr);
		this->head->setPrevious(nullptr);
		nodeToPop = this->head;
		this->size--;
		return returnElement;
	}
	this->head->next()->setPrevious(nullptr);
	nodeToPop = this->head;
	this->head = this->head->next();
	delete nodeToPop;
	this->size--;
	return returnElement;
}
//Complexity: Theta(1)

bool PriorityQueue::isEmpty() const {
	return this->size == 0;
}
//Complexity: Theta(1)

TPriority PriorityQueue::priorityOf(TElem element)
{
	Node *currentNode = this->head;
	while (currentNode->getElement().first != element)
	{
		currentNode = currentNode->next();
	}
	if (currentNode == nullptr)
		return -1;
	return currentNode->getElement().second;
}
//Complexity: BC: Theta(1), WC: Theta(n), Total: O(n)

PriorityQueue::~PriorityQueue() {
	Node* nodeToFree;
	Node* currentNode = this->head;
	while (currentNode != nullptr)
	{
		nodeToFree = currentNode;
		currentNode = currentNode->next();
		delete nodeToFree;
		this->size--;
	}
};
//Comlexity: Theta(n)
