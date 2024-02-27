#include "Service.h"

Service* createService(Repository* repository)
{
	Service* service = malloc(sizeof(Service));
	if (service != NULL)
	{
		service->repository = repository;
	}
	return service;
}

void destroyService(Service* serviceToDestroy)
{
	destroyRepository(serviceToDestroy->repository);
	free(serviceToDestroy);
}

void testCreateService()
{
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	assert(service->repository->capacity == 10);
	assert(service->repository->length == 0);
	destroyService(service);
}

Repository* getRepositoryFromService(Service* service)
{
	return service->repository;
}

int is_valid_category(char* product_category)
{
	if (strcmp(product_category, "dairy") == 0)
		return 1;
	else if (strcmp(product_category, "sweets") == 0)
		return 1;
	else if (strcmp(product_category, "meat") == 0)
		return 1;
	else if (strcmp(product_category, "fruit") == 0)
		return 1;
	return 0;
}

int is_valid_quantity(int quantity)
{
	if (quantity < 1)
		return 0;
	return 1;
}

int is_valid_date(Date product_expiring_date)
{
	if (product_expiring_date.day <= 0 || product_expiring_date.day >= 32)
		return 0;
	if (product_expiring_date.month <= 0 || product_expiring_date.month >= 13)
		return 0;
	if (product_expiring_date.year <= 2000 || product_expiring_date.year >= 9999)
		return 0;
	switch (product_expiring_date.month)
	{
	case 2:
		if (product_expiring_date.year % 4 == 0 && product_expiring_date.day >= 30)
			return 0;
		if (product_expiring_date.year % 4 != 0 && product_expiring_date.day >= 29)
			return 0;
		break;
	case 4:
		if (product_expiring_date.day >= 31)
			return 0;
	case 6:
		if (product_expiring_date.day >= 31)
			return 0;
	case 9:
		if (product_expiring_date.day >= 31)
			return 0;
	case 11:
		if (product_expiring_date.day >= 31)
			return 0;
	}
	return 1;
}

void testProductValidations()
{
	Product* product = createProduct("Milk", "dairy", 5, createDate(29, 2, 2020));
	assert(is_valid_category(getCategoryOfProduct(product)) == 1);
	assert(is_valid_quantity(getQuantityOfProduct(product)) == 1);
	assert(is_valid_date(getExpiringDateOfProduct(product)) == 1);
	destroyProduct(product);
	product = createProduct("Milk", "sweets", -1, createDate(29, 2, 2021));
	assert(is_valid_category(getCategoryOfProduct(product)) == 1);
	assert(is_valid_quantity(getQuantityOfProduct(product)) == 0);
	assert(is_valid_date(getExpiringDateOfProduct(product)) == 0);
	destroyProduct(product);
	product = createProduct("Milk", "dary", 100, createDate(31, 3, 2020));
	assert(is_valid_category(getCategoryOfProduct(product)) == 0);
	assert(is_valid_quantity(getQuantityOfProduct(product)) == 1);
	assert(is_valid_date(getExpiringDateOfProduct(product)) == 1);
	destroyProduct(product);
	product = createProduct("Milk", "swets", 6, createDate(15, 13, 2020));
	assert(is_valid_category(getCategoryOfProduct(product)) == 0);
	assert(is_valid_quantity(getQuantityOfProduct(product)) == 1);
	assert(is_valid_date(getExpiringDateOfProduct(product)) == 0);
	destroyProduct(product);
	product = createProduct("Milk", "meat", -100, createDate(29, 2, 100));
	assert(is_valid_category(getCategoryOfProduct(product)) == 1);
	assert(is_valid_quantity(getQuantityOfProduct(product)) == 0);
	assert(is_valid_date(getExpiringDateOfProduct(product)) == 0);
	destroyProduct(product);
}

void addProductToEndService(Service* service, Product* productToAdd)
{
	int returnValue = findProductInRepository(getRepositoryFromService(service), productToAdd);
	if (returnValue == -1)
	{
		return addProductToEndRepository(getRepositoryFromService(service), productToAdd);
	}
	else
	{
		setQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), returnValue), getQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), returnValue)) + getQuantityOfProduct(productToAdd));
	}
}

void testAddProductToEndService()
{
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	Product* product = createProduct("Milk", "dairy", 5, createDate(0, 0, 0));
	addProductToEndService(service, product);
	assert(strcmp(getNameOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)), "Milk") == 0);
	assert(strcmp(getCategoryOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)), "dairy") == 0);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)) == 5);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).day == 0);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).month == 0);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).year == 0);
	destroyProduct(product);
	product = createProduct("Milk", "dairy", 5, createDate(0, 0, 0));
	addProductToEndService(service, product);
	assert(strcmp(getNameOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)), "Milk") == 0);
	assert(strcmp(getCategoryOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)), "dairy") == 0);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)) == 10);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).day == 0);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).month == 0);
	assert(getExpiringDateOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)).year == 0);
	destroyProduct(product);
	destroyService(service);
}

void removeProductFromPositionService(Service* service, int positionToRemove)
{
	return removeProductFromPositionRepository(getRepositoryFromService(service), positionToRemove);
}

void testRemoveProductFromPositionService()
{
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	Product* product = createProduct("Milk", "diary", 1, createDate(0, 0, 0));
	addProductToEndService(service, product);
	destroyProduct(product);
	product = createProduct("Honey", "sweets", 2, createDate(1, 1, 1));
	addProductToEndService(service, product);
	destroyProduct(product);
	removeProductFromPositionService(service, 0);
	assert(getLengthOfRepository(getRepositoryFromService(service)) == 1);
	assert(strcmp(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)->name, "Honey") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)->category, "sweets") == 0);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)) == 2);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.day == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.month == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.year == 1);
	removeProductFromPositionService(service, 0);
	assert(getLengthOfRepository(repository) == 0);
	destroyService(service);
}

void updateProductFromPositionToGivenProductService(Service* service, Product* product, int position)
{
	free(getProductOfRepositoryFromPosition(getRepositoryFromService(service), position)->name);
	free(getProductOfRepositoryFromPosition(getRepositoryFromService(service), position)->category);

	setProductInRepositoryOnPosition(getRepositoryFromService(service), product, position);
}

void testUpdateProductFromPositionToGivenProduct()
{
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	Product* product = createProduct("Milk", "diary", 1, createDate(0, 0, 0));
	addProductToEndService(service, product);
	destroyProduct(product);
	product = createProduct("Honey", "sweets", 2, createDate(1, 1, 1));
	updateProductFromPositionToGivenProductService(service, product, 0);
	destroyProduct(product);
	assert(strcmp(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)->name, "Honey") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)->category, "sweets") == 0);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(getRepositoryFromService(service), 0)) == 2);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.day == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.month == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.year == 1);
	destroyService(service);
}

Repository* sortRepositoryByQuantityService(Repository* repository)
{
	int i, j;
	Product swap;
	swap.name = malloc(sizeof(char) * 64);
	swap.category = malloc(sizeof(char) * 64);
	for (i = 0; i < getLengthOfRepository(repository) - 1; i++)
		for (j = i + 1; j < getLengthOfRepository(repository); j++)
			if (getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, i)) > getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, j)))
			{
				if (swap.name != 0 && swap.category != 0)
				{
					strcpy(swap.name, getProductOfRepositoryFromPosition(repository, i)->name);
					strcpy(swap.category, getProductOfRepositoryFromPosition(repository, i)->category);
				}
				swap.quantity = getProductOfRepositoryFromPosition(repository, i)->quantity;
				swap.expiringDate = getProductOfRepositoryFromPosition(repository, i)->expiringDate;

				free(getProductOfRepositoryFromPosition(repository, i)->name);
				free(getProductOfRepositoryFromPosition(repository, i)->category);
				setProductInRepositoryOnPosition(repository, getProductOfRepositoryFromPosition(repository, j), i);

				free(getProductOfRepositoryFromPosition(repository, j)->name);
				free(getProductOfRepositoryFromPosition(repository, j)->category);
				setProductInRepositoryOnPosition(repository, &swap, j);
			}
	free(swap.name);
	free(swap.category);
	return repository;
}

void testSortRepositoryByQuantity()
{
	Repository* repository = createRepository(10);
	add10EntitiesToRepository(repository);
	sortRepositoryByQuantityService(repository);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 0)) == 1);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 1)) == 1);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 2)) == 1);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 3)) == 1);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 4)) == 2);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 5)) == 2);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 6)) == 3);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 7)) == 3);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 8)) == 4);
	assert(getQuantityOfProduct(getProductOfRepositoryFromPosition(repository, 9)) == 5);
	destroyRepository(repository);
}

int findDaysPassedSinceBaseDate(Date expiringDate)
{
	Date baseDate = createDate(1, 1, 2000);
	int days = 0;
	days += (expiringDate.year - baseDate.year) * 365;
	days += (expiringDate.year - baseDate.year) / 4;
	days -= (expiringDate.year - baseDate.year) / 100;
	days += (expiringDate.year - baseDate.year) / 400;
	days += monthDaysCounter(expiringDate.month - 1, expiringDate.year);
	days += expiringDate.day;
	return days;
}

int monthDaysCounter(int month, int year)
{
	int monthlengths[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	int daysCounter = 0;
	for (int i = 1; i <= month; i++)
		daysCounter += monthlengths[i];
	if (isLeapYear(year) && month >= 2)
		daysCounter++;
	return daysCounter;
}

int isLeapYear(int year)
{
	return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
}

void testIsLeapYear()
{
	assert(isLeapYear(2000) == 1);
	assert(isLeapYear(1900) == 0);
	assert(isLeapYear(1904) == 1);
	assert(isLeapYear(2020) == 1);
}

void testMonthDaysCounter()
{
	assert(monthDaysCounter(2, 2020) == 60);
	assert(monthDaysCounter(2, 2021) == 59);
	assert(monthDaysCounter(3, 2021) == 90);
	assert(monthDaysCounter(4, 2020) == 121);
}

void testfindDaysPassedSinceBaseDate()
{
	Date date = createDate(15, 4, 2020);
	assert(is_valid_date(date) == 1);
	assert(findDaysPassedSinceBaseDate(date) == 7411);
}

int findDifferenceBetweenTwoDates(Date date1, Date date2)
{
	return findDaysPassedSinceBaseDate(date2) - findDaysPassedSinceBaseDate(date1);
}

void testFindDifferenceBetweenTwoDates()
{
	Date date1 = createDate(15, 4, 2020);
	Date date2 = createDate(30, 4, 2020);
	assert(is_valid_date(date1) == 1);
	assert(is_valid_date(date2) == 1);
	assert(findDifferenceBetweenTwoDates(date1, date2) == 15);
	date1 = createDate(15, 4, 2021);
	date2 = createDate(15, 4, 2022);
	assert(is_valid_date(date1) == 1);
	assert(is_valid_date(date2) == 1);
	assert(findDifferenceBetweenTwoDates(date1, date2) == 365);
	date1 = createDate(15, 4, 2021);
	date2 = createDate(15, 5, 2022);
	assert(is_valid_date(date1) == 1);
	assert(is_valid_date(date2) == 1);
	assert(findDifferenceBetweenTwoDates(date1, date2) == 395);
	date1 = createDate(15, 4, 2021);
	date2 = createDate(30, 5, 2022);
	assert(is_valid_date(date1) == 1);
	assert(is_valid_date(date2) == 1);
	assert(findDifferenceBetweenTwoDates(date1, date2) == 410);
}