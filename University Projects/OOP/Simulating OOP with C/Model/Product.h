#pragma once
#include "Date.h"
#include <stdlib.h>
#include <string.h>
#include <assert.h>

typedef struct product {
	char* name;
	char* category;
	int quantity;
	Date expiringDate;
} Product;

Product* createProduct(char* name, char* category, int quantity, Date expiringDate);

void destroyProduct(Product* productToDestroy);

void testCreateProduct();

char* getNameOfProduct(Product* product);

char* getCategoryOfProduct(Product* product);

int getQuantityOfProduct(Product* product);

Date getExpiringDateOfProduct(Product* product);

void setNameOfProduct(Product* productToBeSet, char* name);

void setCategoryOfProduct(Product* productToBeSet, char* category);

void setQuantityOfProduct(Product* productToBeSet, int quantity);

void setExpiringDateOfProduct(Product* productToBeSet, Date date);

void testGettersAndSettersProducts();

char* dateOfProductToString(Date dateOfProduct, char* dateString);

