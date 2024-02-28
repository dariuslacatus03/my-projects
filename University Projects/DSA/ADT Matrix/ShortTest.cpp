#include <assert.h>
#include "Matrix.h"
#include <iostream>
using namespace std;

void testAll() { 
	Matrix m(4, 4);
	assert(m.nrLines() == 4);
	assert(m.nrColumns() == 4);	
	m.modify(1, 1, 5);
	assert(m.element(1, 1) == 5);
	TElem old = m.modify(1, 1, 6);
	assert(m.element(1, 2) == NULL_TELEM);
	assert(old == 5);
}

void testExtraFeature() {
	Matrix m(5, 5);
	int i;
	for (i = 0; i <= 4; i++)
	{
		m.modify(i, 2, 1);
	}
	m.setElemnsOnCol(2, 2);
	for (i = 0; i <= 4; i++)
	{
		assert(m.element(i, 2) == 2);
	}
	m.setElemnsOnCol(3, 10);
	for (i = 0; i <= 4; i++)
	{
		assert(m.element(i, 3) == 10);
	}
	m.modify(1, 1, 4);
	m.modify(3, 1, 4);
	m.setElemnsOnCol(1, 100);
	for (i = 0; i <= 4; i++)
	{
		assert(m.element(i, 1) == 100);
	}
	try {
		m.setElemnsOnCol(6, 1000);
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
	try {
		m.setElemnsOnCol(-1, 1000);
		assert(false);
	}
	catch (exception&) {
		assert(true);
	}
}