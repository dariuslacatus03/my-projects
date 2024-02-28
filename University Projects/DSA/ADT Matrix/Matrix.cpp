#include "Matrix.h"
#include <exception>
using namespace std;



Matrix::Matrix(int nrLines, int nrCols) {
	this->numberOfLines = nrLines;
	this->numberOfColumns = nrCols;
	this->capacity = 10;
	this->numberOfElements = 0;
	this->linesArray = new TElem[capacity];
	//<=> this->lines = malloc(sizeof(TElem)*capacity);
	this->columnsArray = new TElem[capacity];
	this->elementsArray = new TElem[capacity];
}
//Complexity: Theta(1)


void Matrix::resize() {
	TElem* newLinesArray = new TElem[2 * this->capacity];
	TElem* newColumnsArray = new TElem[2 * this->capacity];
	TElem* newElementsArray = new TElem[2 * this->capacity];
	int i;
	for (i = 0; i < this->numberOfElements; i++)
	{
		newLinesArray[i] = this->linesArray[i];
		newColumnsArray[i] = this->columnsArray[i];
		newElementsArray[i] = this->elementsArray[i];
	}
	this->capacity = 2 * this->capacity;
	delete[] this->linesArray;
	delete[] this->columnsArray;
	delete[] this->elementsArray;
	this->linesArray = newLinesArray;
	this->columnsArray = newColumnsArray;
	this->elementsArray = newElementsArray;
}
//Complexity: Theta(numberOfElements)


int Matrix::nrLines() const {
	return this->numberOfLines;
}
//Complexity: Theta(1)


int Matrix::nrColumns() const {
	return this->numberOfColumns;
}
//Complexity: Theta(1)


TElem Matrix::element(int i, int j) const {
	if (i < 0 || i >= this->numberOfLines || j < 0 || j >= this->numberOfColumns)
	{
		throw exception();
	}
	int isElement = 0;
	int index;
	for (index = 0; index < this->numberOfElements; index++)
	{
		if (this->linesArray[index] == i && this->columnsArray[index] == j)
		{
			return this->elementsArray[index];
			isElement = 1;
		}
	}
	if (isElement == 0)
	{
		return NULL_TELEM;
	}
}
//Complexity: Best Case: Theta(1), Worst Case: Theta(numberOfElements). Total Complexity: O(numberOfElements)


TElem Matrix::modify(int i, int j, TElem e) {
	if (i < 0 || i >= this->numberOfLines || j < 0 || j >= this->numberOfColumns)
	{
		throw exception();
	}
	int isElement = 0;
	int index, savedIndex;
	TElem previousElement = NULL_TELEM;
	for (index = 0; index < this->numberOfElements; index++)
	{
		if (this->linesArray[index] == i && this->columnsArray[index] == j)
		{
			isElement = 1;
			savedIndex = index;
			previousElement = this->elementsArray[index];
		}
	}
	if (isElement == 1)
	{
		this->elementsArray[savedIndex] = e;
		return previousElement;
	}
	else if (isElement == 0)
	{
		if (this->numberOfElements == this->capacity)
		{
			resize();
		}
		this->linesArray[this->numberOfElements] = i;
		this->columnsArray[this->numberOfElements] = j;
		this->elementsArray[this->numberOfElements] = e;
		this->numberOfElements = this->numberOfElements + 1;
		return NULL_TELEM;
	}
}
//Complexity: Best Case: Theta(1), Worst Case: Theta(numberOfElements). Total Complexity: O(numberOfElements)


Matrix::~Matrix() {
	delete[] this->elementsArray;
	delete[] this->linesArray;
	delete[] this->columnsArray;
}
//Complexity: Theta(1)


void Matrix::setElemnsOnCol(int col, TElem elem){
	if (col < 0 || col >= this->numberOfColumns)
	{
		throw exception();
	}
	int i;
	for (i = 0; i < numberOfLines; i++)
	{
		this->modify(i, col, elem);
	}
}
//Complexity: Theta(numberOfLines)

