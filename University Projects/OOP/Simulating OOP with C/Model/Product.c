#include "Product.h"

Product* createProduct(char* name, char* category, int quantity, Date expiringDate)
{
	Product* product;
	product = (Product*)malloc(sizeof(Product));

	if (product != NULL)
	{
		product->name = (char*)malloc(sizeof(char) * (strlen(name) + 1));
		if (product->name != NULL)
		{
			strcpy(product->name, name);
		}
		product->category = (char*)malloc(sizeof(char) * (strlen(category) + 1));
		if (product->category != NULL)
		{
			strcpy(product->category, category);
		}
		product->quantity = quantity;
		product->expiringDate = expiringDate;
	}

	return product;
}

void destroyProduct(Product* productToDestroy)
{
	free(productToDestroy->name);
	free(productToDestroy->category);
	free(productToDestroy);
}

void testCreateProduct()
{
	Product* product = createProduct("Milk", "dairy", 5, createDate(5, 2, 2022));
	assert(strcmp(product->name, "Milk") == 0);
	assert(strcmp(product->category, "dairy") == 0);
	assert(product->quantity == 5);
	assert(product->expiringDate.day == 5);
	assert(product->expiringDate.month == 2);
	assert(product->expiringDate.year == 2022);
	destroyProduct(product);

	product = createProduct("Honey", "sweets", 20, createDate(12, 12, 2020));
	assert(strcmp(product->name, "Honey") == 0);
	assert(strcmp(product->category, "sweets") == 0);
	assert(product->quantity == 20);
	assert(product->expiringDate.day == 12);
	assert(product->expiringDate.month == 12);
	assert(product->expiringDate.year == 2020);
	destroyProduct(product);
}

char* getNameOfProduct(Product* product)
{
	return product->name;
}

char* getCategoryOfProduct(Product* product)
{
	return product->category;
}

int getQuantityOfProduct(Product* product)
{
	return product->quantity;
}

Date getExpiringDateOfProduct(Product* product)
{
	return product->expiringDate;
}

void setNameOfProduct(Product* productToBeSet, char* name)
{
	free(productToBeSet->name);
	productToBeSet->name = (char*)malloc(sizeof(char) * (strlen(name) + 1));
	if (productToBeSet->name != NULL)
	{
		strcpy(productToBeSet->name, name);
	}
}

void setCategoryOfProduct(Product* productToBeSet, char* category)
{
	free(productToBeSet->category);
	productToBeSet->category = (char*)malloc(sizeof(char) * (strlen(category) + 1));
	if (productToBeSet->category != NULL)
	{
		strcpy(productToBeSet->category, category);
	}
}

void setQuantityOfProduct(Product* productToBeSet, int quantity)
{
	productToBeSet->quantity = quantity;
}

void setExpiringDateOfProduct(Product* productToBeSet, Date date)
{
	productToBeSet->expiringDate.day = date.day;
	productToBeSet->expiringDate.month = date.month;
	productToBeSet->expiringDate.year = date.year;
}

void testGettersAndSettersProducts()
{
	Product* product = createProduct("", "", 0, createDate(0, 0, 0));
	setNameOfProduct(product, "Milk");
	assert(strcmp(getNameOfProduct(product), "Milk") == 0);
	setCategoryOfProduct(product, "dairy");
	assert(strcmp(getCategoryOfProduct(product), "dairy") == 0);
	setQuantityOfProduct(product, 20);
	assert(getQuantityOfProduct(product) == 20);
	setExpiringDateOfProduct(product, createDate(12, 11, 2000));
	assert(getExpiringDateOfProduct(product).day == 12);
	assert(getExpiringDateOfProduct(product).month == 11);
	assert(getExpiringDateOfProduct(product).year == 2000);
	destroyProduct(product);

	product = createProduct("", "", 0, createDate(0, 0, 0));
	setNameOfProduct(product, "asdasd");
	assert(strcmp(getNameOfProduct(product), "asdasd") == 0);
	setCategoryOfProduct(product, "sweets");
	assert(strcmp(getCategoryOfProduct(product), "sweets") == 0);
	setQuantityOfProduct(product, 100);
	assert(getQuantityOfProduct(product) == 100);
	setExpiringDateOfProduct(product, createDate(5, 6, 2022));
	assert(getExpiringDateOfProduct(product).day == 5);
	assert(getExpiringDateOfProduct(product).month == 6);
	assert(getExpiringDateOfProduct(product).year == 2022);
	destroyProduct(product);
}

char* dateOfProductToString(Date dateOfProduct, char* dateString)
{
	sprintf(dateString, "%d.%d.%d", dateOfProduct.day, dateOfProduct.month, dateOfProduct.year);
	return dateString;
}