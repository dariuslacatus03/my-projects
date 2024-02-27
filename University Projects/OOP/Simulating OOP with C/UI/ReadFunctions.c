#include "ReadFunctions.h"

char* readProductName(char* name)
{
	char product_name[64];
	printf("Give product name: ");
	scanf("%s", product_name);
	flushStdinBuffer();

	sprintf(name, "%s", product_name);
	return name;
}

char* readProductCategory(char* category)
{
	char product_category[64];
	printf("Give product category(dairy, sweets, meat or fruit): ");
	scanf("%s", product_category);
	flushStdinBuffer();

	while (is_valid_category(product_category) == 0)
	{
		printf("Make sure you choose a valid category(dairy, sweets, meat or fruit): ");
		scanf("%s", product_category);
		flushStdinBuffer();
	}

	sprintf(category, "%s", product_category);
	return category;
}

int readProductQuantity()
{
	printf("Give product quantity: ");
	int product_quantity = 0;
	scanf("%d", &product_quantity);
	flushStdinBuffer();
	while (is_valid_quantity(product_quantity) == 0)
	{
		printf("Make sure you introduce a valid quantity: ");
		scanf("%d", &product_quantity);
	}
	return product_quantity;
}

Date readProductExpiringDate()
{
	Date productExpiringDate;
	printf("Expiring Date:\nYear:");
	scanf("%d", &productExpiringDate.year);
	flushStdinBuffer();
	printf("Month: ");
	scanf("%d", &productExpiringDate.month);
	flushStdinBuffer();
	printf("Day: ");
	scanf("%d", &productExpiringDate.day);
	flushStdinBuffer();

	while (is_valid_date(productExpiringDate) == 0)
	{
		printf("Make sure you choose a valid expiring date.\n");

		printf("Expiring Date:\nYear:");
		scanf("%d", &productExpiringDate.year);
		flushStdinBuffer();
		printf("Month: ");
		scanf("%d", &productExpiringDate.month);
		flushStdinBuffer();
		printf("Day: ");
		scanf("%d", &productExpiringDate.day);
		flushStdinBuffer();
	}

	return productExpiringDate;
}

int readIndexUI(UI* ui)
{
	if (getLengthOfRepository(getRepositoryFromService(getServiceFromUI(ui))) > 0)
	{
		printf("Give the index of the product: ");
		int id = 0;
		scanf("%d", &id);
		flushStdinBuffer();
		while (id < 0 || id > getLengthOfRepository(getRepositoryFromService(getServiceFromUI(ui))) - 1)
		{
			printf("Make sure you give a valid ID: ");
			scanf("%d", &id);
			flushStdinBuffer();
		}
		return id;
	}
	else
		return -1;
}

char* readSearchCategory(char* category)
{
	int i = 0;
	while (category[i] = getchar())
	{
		if (category[i] == '\n')
		{
			category[i] = '\0';
			break;
		}
		i++;
	}
	if (strcmp(category, "") == 0)
	{
		return "";
	}
	else
	{
		while (is_valid_category(category) == 0)
		{
			printf("Wrong category.\n\nGive category: ");
			strcpy(category, "");
			i = 0;
			while (category[i] = getchar())
			{
				if (category[i] == '\n')
				{
					category[i] = '\0';
					break;
				}
				i++;
			}
			if (strcmp(category, "") == 0)
			{
				return "";
			}
		}
		return category;
	}
}

char* readSearchString(char* searchString)
{
	int i = 0;
	while (searchString[i] = getchar())
	{
		if (searchString[i] == '\n')
		{
			searchString[i] = '\0';
			break;
		}
		i++;
	}
	return searchString;
}