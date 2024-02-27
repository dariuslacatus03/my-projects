#include "Repository.h"

Repository* createRepository(int capacity)
{
	Repository* repository = (Repository*)malloc(sizeof(Repository));
	if (repository != NULL)
	{
		repository->length = 0;
		repository->capacity = capacity;
		repository->products = (Product*)malloc(sizeof(Product) * repository->capacity);
	}
	return repository;
}

void destroyRepository(Repository* repository)
{
	int i;
	for (i = 0; i < getLengthOfRepository(repository); i++)
	{
		free(getProductOfRepositoryFromPosition(repository, i)->name);
		free(getProductOfRepositoryFromPosition(repository, i)->category);

	}
	free(repository->products);
	free(repository);
}

void destroyElementsOfRepository(Repository* repository)
{
	int i;
	for (i = 0; i < getLengthOfRepository(repository); i++)
	{
		free(getProductOfRepositoryFromPosition(repository, i)->name);
		free(getProductOfRepositoryFromPosition(repository, i)->category);

	}
	free(repository->products);
}

void testCreateRepository()
{
	Repository* repository = createRepository(10);
	assert(repository->length == 0);
	assert(repository->capacity == 10);
	assert(repository->products != NULL);
	destroyRepository(repository);
	repository = createRepository(20);
	assert(repository->length == 0);
	assert(repository->capacity == 20);
	assert(repository->products != NULL);
	destroyRepository(repository);
}

int getLengthOfRepository(Repository* repository)
{
	return repository->length;
}

int getCapacityOfRepository(Repository* repository)
{
	return repository->capacity;
}

Product* getProductOfRepositoryFromPosition(Repository* repository, int position)
{
	return &repository->products[position];
}

void setLengthOfRepository(Repository* repository, int length)
{
	repository->length = length;
}

void setCapacityOfRepository(Repository* repository, int capacity)
{
	repository->capacity = capacity;
}

void setProductInRepositoryOnPosition(Repository* repository, Product* product, int position)
{
	repository->products[position].name = (char*)malloc(sizeof(char) * (strlen(getNameOfProduct(product)) + 1));
	repository->products[position].category = (char*)malloc(sizeof(char) * (strlen(getCategoryOfProduct(product)) + 1));
	if (repository->products[position].name != NULL)
		strcpy(repository->products[position].name, getNameOfProduct(product));
	if (repository->products[position].category != NULL)
		strcpy(repository->products[position].category, getCategoryOfProduct(product));
	repository->products[position].quantity = getQuantityOfProduct(product);
	repository->products[position].expiringDate = getExpiringDateOfProduct(product);
}

void testGettersAndSettersRepository()
{
	Repository* repository = createRepository(10);
	Product* product = createProduct("Milk", "diary", 5, createDate(6, 6, 2003));
	setProductInRepositoryOnPosition(repository, product, 0);
	destroyProduct(product);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->name, "Milk") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->category, "diary") == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->quantity == 5);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.day == 6);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.month == 6);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.year == 2003);
	setLengthOfRepository(repository, getLengthOfRepository(repository) + 1);
	assert(getLengthOfRepository(repository) == 1);
	setCapacityOfRepository(repository, 5);
	assert(getCapacityOfRepository(repository) == 5);
	destroyRepository(repository);
}

void resizeRepositoryToGivenCapacity(Repository* repository, int capacity)
{
	Product* resizedSpaceForProducts = (Product*)malloc(sizeof(Product) * capacity);
	if (resizedSpaceForProducts != NULL)
	{
		for (int i = 0; i < getLengthOfRepository(repository); i++)
		{
			resizedSpaceForProducts[i] = repository->products[i];
		}
	}
	free(repository->products);
	repository->products = resizedSpaceForProducts;
	setCapacityOfRepository(repository, capacity);
}

void testResizeRepositoryToGivenCapacity()
{
	Repository* repository = createRepository(10);
	resizeRepositoryToGivenCapacity(repository, 15);
	assert(repository->capacity == 15);
	assert(repository->products != NULL);
	destroyRepository(repository);
}

void addProductToEndRepository(Repository* repository, Product* productToAdd)
{
	if (getCapacityOfRepository(repository) == getLengthOfRepository(repository))
	{
		resizeRepositoryToGivenCapacity(repository, getCapacityOfRepository(repository) * 2);
	}

	setProductInRepositoryOnPosition(repository, productToAdd, getLengthOfRepository(repository));

	setLengthOfRepository(repository, getLengthOfRepository(repository) + 1);
}

void testAddProductToEndRepository()
{
	Repository* repository = createRepository(10);
	Product* product = createProduct("Milk", "diary", 0, createDate(0, 0, 0));
	addProductToEndRepository(repository, product);
	destroyProduct(product);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->name, "Milk") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->category, "diary") == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->quantity == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.day == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.month == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.year == 0);
	product = createProduct("Milk", "diary", 0, createDate(0, 0, 0));
	addProductToEndRepository(repository, product);
	destroyProduct(product);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 1)->name, "Milk") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 1)->category, "diary") == 0);
	assert(getProductOfRepositoryFromPosition(repository, 1)->quantity == 0);
	assert(getProductOfRepositoryFromPosition(repository, 1)->expiringDate.day == 0);
	assert(getProductOfRepositoryFromPosition(repository, 1)->expiringDate.month == 0);
	assert(getProductOfRepositoryFromPosition(repository, 1)->expiringDate.year == 0);
	destroyRepository(repository);
}

void removeProductFromPositionRepository(Repository* repository, int positionToRemove)
{
	int i;
	if (positionToRemove == getLengthOfRepository(repository) - 1)
	{
		free(getProductOfRepositoryFromPosition(repository, positionToRemove)->name);
		free(getProductOfRepositoryFromPosition(repository, positionToRemove)->category);
	}
	for (i = positionToRemove; i < getLengthOfRepository(repository) - 1; i++)
	{
		free(getProductOfRepositoryFromPosition(repository, i)->name);
		free(getProductOfRepositoryFromPosition(repository, i)->category);
		setProductInRepositoryOnPosition(repository, getProductOfRepositoryFromPosition(repository, i + 1), i);
	}
	if (positionToRemove < getLengthOfRepository(repository) - 1)
	{
		free(getProductOfRepositoryFromPosition(repository, getLengthOfRepository(repository) - 1)->name);
		free(getProductOfRepositoryFromPosition(repository, getLengthOfRepository(repository) - 1)->category);
	}
	setLengthOfRepository(repository, getLengthOfRepository(repository) - 1);
}

void testRemoveProductFromPositionRepository()
{
	Repository* repository = createRepository(10);
	Product* product = createProduct("Milk", "diary", 1, createDate(0, 0, 0));
	addProductToEndRepository(repository, product);
	destroyProduct(product);
	product = createProduct("Honey", "sweets", 2, createDate(1, 1, 1));
	addProductToEndRepository(repository, product);
	destroyProduct(product);
	removeProductFromPositionRepository(repository, 0);
	assert(getLengthOfRepository(repository) == 1);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->name, "Honey") == 0);
	assert(strcmp(getProductOfRepositoryFromPosition(repository, 0)->category, "sweets") == 0);
	assert(getProductOfRepositoryFromPosition(repository, 0)->quantity == 2);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.day == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.month == 1);
	assert(getProductOfRepositoryFromPosition(repository, 0)->expiringDate.year == 1);
	removeProductFromPositionRepository(repository, 0);
	assert(getLengthOfRepository(repository) == 0);
	destroyRepository(repository);
}

void add10EntitiesToRepository(Repository* repository)
{
	char name[64], category[64];
	int quantity;
	Date expiringDate;
	Product* product;
	strcpy(name, "Milk");
	strcpy(category, "dairy");
	quantity = 5;
	expiringDate = createDate(10, 12, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Honey");
	strcpy(category, "sweets");
	quantity = 3;
	expiringDate = createDate(30, 12, 2999);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Apple");
	strcpy(category, "fruit");
	quantity = 2;
	expiringDate = createDate(1, 4, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Banana");
	strcpy(category, "fruit");
	quantity = 1;
	expiringDate = createDate(2, 4, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Chicken");
	strcpy(category, "meat");
	quantity = 2;
	expiringDate = createDate(15, 4, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Chocolate");
	strcpy(category, "sweets");
	quantity = 1;
	expiringDate = createDate(6, 6, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Cheese");
	strcpy(category, "dairy");
	quantity = 3;
	expiringDate = createDate(10, 4, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Avocado");
	strcpy(category, "fruit");
	quantity = 4;
	expiringDate = createDate(10, 5, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Cake");
	strcpy(category, "sweets");
	quantity = 1;
	expiringDate = createDate(31, 3, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}

	strcpy(name, "Watermelon");
	strcpy(category, "fruit");
	quantity = 1;
	expiringDate = createDate(6, 5, 2023);
	if (is_valid_category(category) && is_valid_quantity(quantity) && is_valid_date(expiringDate))
	{
		product = createProduct(name, category, quantity, expiringDate);
		addProductToEndRepository(repository, product);
		destroyProduct(product);
	}
}

Repository* deepCopyOfRepository(Repository* repositoryToCopy)
{
	Repository* repository = createRepository(getCapacityOfRepository(repositoryToCopy));
	int i;
	for (i = 0; i < getLengthOfRepository(repositoryToCopy); i++)
	{
		addProductToEndRepository(repository, getProductOfRepositoryFromPosition(repositoryToCopy, i));
	}
	return repository;
}

int findProductInRepository(Repository* repository, Product* ProductToFind)
{
	for (int i = 0; i < getLengthOfRepository(repository); i++)
	{
		if (strcmp(getNameOfProduct(getProductOfRepositoryFromPosition(repository, i)), getNameOfProduct(ProductToFind)) == 0)
		{
			return i;
		}
	}
	return -1;
}

void testFindProductInRepository()
{
	Repository* repository = createRepository(10);
	add10EntitiesToRepository(repository);
	Product* product = createProduct("Milk", "diary", 5, createDate(0, 0, 0));
	assert(findProductInRepository(repository, product) == 0);
	destroyProduct(product);
	product = createProduct("NotInRepo", "diary", 10, createDate(10, 10, 2020));
	assert(findProductInRepository(repository, product) == -1);
	destroyProduct(product);
	destroyRepository(repository);
}