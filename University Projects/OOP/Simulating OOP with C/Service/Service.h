#pragma once
#include "Repository.h"

typedef struct
{
	Repository* repository;
}Service;

Service* createService(Repository* repository);

void destroyService(Service* serviceToDestroy);

void testCreateService();

Repository* getRepositoryFromService(Service* service);

int is_valid_category(char* product_category);

int is_valid_quantity(int quantity);

int is_valid_date(Date product_expiring_date);

void testProductValidations();

void addProductToEndService(Service* service, Product* productToAdd);

void testAddProductToEndService();

void removeProductFromPositionService(Service* service, int positionToRemove);

void testRemoveProductFromPositionService();

void updateProductFromPositionToGivenProductService(Service* service, Product* product, int position);

void testUpdateProductFromPositionToGivenProduct();

Repository* sortRepositoryByQuantityService(Repository* repository);

void testSortRepositoryByQuantity();

int findDaysPassedSinceBaseDate(Date expiringDate);

int monthDaysCounter(int month, int year);

int isLeapYear(int year);

void testIsLeapYear();

void testMonthDaysCounter();

void testfindDaysPassedSinceBaseDate();

int findDifferenceBetweenTwoDates(Date date1, Date date2);

void testFindDifferenceBetweenTwoDates();

