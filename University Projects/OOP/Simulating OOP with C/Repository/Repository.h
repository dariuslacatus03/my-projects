#pragma once
#include "Product.h"

typedef struct
{
	Product* products;
	int length;
	int capacity;
} Repository;

Repository* createRepository(int capacity);

void destroyRepository(Repository* repository);

void testCreateRepository();

int getLengthOfRepository(Repository* repository);

int getCapacityOfRepository(Repository* repository);

Product* getProductOfRepositoryFromPosition(Repository* repository, int position);

void setLengthOfRepository(Repository* repository, int length);

void setCapacityOfRepository(Repository* repository, int capacity);

void setProductInRepositoryOnPosition(Repository* repository, Product* product, int position);

void testGettersAndSettersRepository();

void resizeRepositoryToGivenCapacity(Repository* repository, int capacity);

void testResizeRepositoryToGivenCapacity();

void addProductToEndRepository(Repository* repository, Product* productToAdd);

void testAddProductToEndRepository();

void removeProductFromPositionRepository(Repository* repository, int positionToRemove);

void testRemoveProductFromPositionRepository();

void add10EntitiesToRepository(Repository* repository);

Repository* deepCopyOfRepository(Repository* repositoryToCopy);

int findProductInRepository(Repository* repository, Product* productToFind);