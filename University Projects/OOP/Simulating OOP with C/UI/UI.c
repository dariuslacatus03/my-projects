#include "UI.h"
#include "ReadFunctions.h"

void flushStdinBuffer()
{
	int c;
	while ((c = getchar()) != '\n' && c != EOF);
}

UI* createUI(Service* service)
{
	UI* ui = malloc(sizeof(UI));
	if (ui != NULL)
	{
		ui->service = service;
	}
	return ui;
}

void destroyUI(UI* ui)
{
	destroyService(ui->service);
	free(ui);
}

void testCreateUI()
{
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	UI* ui = createUI(service);
	assert(ui->service->repository->capacity == 10);
	assert(ui->service->repository->length == 0);
	destroyUI(ui);
}

Service* getServiceFromUI(UI* ui)
{
	return ui->service;
}

void printMenu()
{
	printf("Main Menu. Available commands:\n");
	printf("0 - Exit the program\n");
	printf("1 - Add a product\n");
	printf("2 - Delete a product\n");
	printf("3 - Update a product\n");
	printf("4 - Search products\n");
	printf("5 - Show products of a given category expiring in X days\n");
	printf("6 - Undo\n");
	printf("7 - Redo\n");
	printf("8 - Show products\n\n");
	printf("Your command: ");
}

void display_product(Product* product)
{
	printf("Name: %s\n", getNameOfProduct(product));
	printf("Category: %s\n", getCategoryOfProduct(product));
	printf("Quantity: %d\n", getQuantityOfProduct(product));
	char dateString[12];
	printf("Expiring Date: %s\n\n", dateOfProductToString(getExpiringDateOfProduct(product), dateString));
}

void displayProductsUI(UI* ui)
{
	int i;
	for (i = 0; i < getLengthOfRepository(getRepositoryFromService(getServiceFromUI(ui))); i++)
	{
		printf("Product %d:\n", i);
		display_product(getProductOfRepositoryFromPosition(getRepositoryFromService(getServiceFromUI(ui)), i));
	}
}

void displaySearchedProductsOrderedByQuantity(UI* ui, char* search_string)
{
	if (getRepositoryFromService(getServiceFromUI(ui)) == 0)
	{
		printf("No products in the fridge.\n\n");
	}
	else
	{
		Repository* sorted_repository = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
		sortRepositoryByQuantityService(sorted_repository);
		if (strcmp(search_string, "") == 0)
		{
			int i;
			for (i = 0; i < getLengthOfRepository(sorted_repository); i++)
			{
				printf("Product %d:\n", i);
				display_product(getProductOfRepositoryFromPosition(sorted_repository, i));
			}
		}
		else
		{
			int i;
			for (i = 0; i < getLengthOfRepository(sorted_repository); i++)
				if (strstr(getNameOfProduct(getProductOfRepositoryFromPosition(sorted_repository, i)), search_string) != NULL)
				{
					printf("Product %d:\n", i);
					display_product(getProductOfRepositoryFromPosition(sorted_repository, i));
				}
		}
		destroyRepository(sorted_repository);
	}
}

int readExpireRangeInDays()
{
	int expireRange;
	printf("Give the search range for expiring date: ");
	scanf("%d", &expireRange);
	while (expireRange < 1)
	{
		printf("Make sure the number is positive: ");
		scanf("%d", &expireRange);
	}
	return expireRange;
}

void displaySearchedCategoryInRange(UI* ui, char* category, int range)
{
	Repository* repository = getRepositoryFromService(getServiceFromUI(ui));
	int i;
	Date currentDate = getCurrentDate();
	for (i = 0; i < getLengthOfRepository(repository); i++)
	{
		if (strcmp(category, getCategoryOfProduct(getProductOfRepositoryFromPosition(repository, i))) == 0)
		{
			if (findDifferenceBetweenTwoDates(currentDate, getExpiringDateOfProduct(getProductOfRepositoryFromPosition(repository, i))) <= range)
			{
				display_product(getProductOfRepositoryFromPosition(repository, i));
			}
		}
	}
}

void startMenu(UI* ui)
{
	add10EntitiesToRepository(getRepositoryFromService(getServiceFromUI(ui)));

	Repository** repositoryStatesForUndo;
	repositoryStatesForUndo = (Repository**)malloc(sizeof(Repository*) * 50);
	int repositoryStatesForUndoLength = 0;

	Repository** repositoryStatesForRedo;
	repositoryStatesForRedo = (Repository**)malloc(sizeof(Repository*) * 50);
	int repositoryStatesForRedoLength = 0;

	if (repositoryStatesForUndo != NULL)
	{
		repositoryStatesForUndo[repositoryStatesForUndoLength] = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
		repositoryStatesForUndoLength = repositoryStatesForUndoLength + 1;
	}

	while (1)
	{
		printMenu();
		int option;
		scanf("%d", &option);
		flushStdinBuffer();
		if (option == 0)
		{
			int i;
			for (i = 0; i < repositoryStatesForUndoLength; i++)
			{
				if (repositoryStatesForUndo[i] != NULL)
				{
					destroyRepository(repositoryStatesForUndo[i]);
				}
			}
			free(repositoryStatesForUndo);

			for (i = 0; i < repositoryStatesForRedoLength; i++)
			{
				if (repositoryStatesForRedo[i] != NULL)
				{
					destroyRepository(repositoryStatesForRedo[i]);
				}
			}
			free(repositoryStatesForRedo);

			printf("The program will be closed.\n\n");
			break;
		}
		switch (option)
		{
		case 1:
		{
			char productName[64];
			strcpy(productName, readProductName(productName));

			char productCategory[64];
			strcpy(productCategory, readProductCategory(productCategory));

			int productQuantity = readProductQuantity();

			Date productExpiringDate = readProductExpiringDate();

			Product* product = createProduct(productName, productCategory, productQuantity, productExpiringDate);
			addProductToEndService(getServiceFromUI(ui), product);
			destroyProduct(product);

			if (repositoryStatesForUndo != NULL)
			{
				repositoryStatesForUndo[repositoryStatesForUndoLength] = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
				repositoryStatesForUndoLength = repositoryStatesForUndoLength + 1;
			}
			break;
		}
		case 2:
		{
			displayProductsUI(ui);

			int index = readIndexUI(ui);
			if (index == -1)
			{
				printf("There are no products in the fridge.\n\n");
				break;
			}
			else
			{
				removeProductFromPositionService(getServiceFromUI(ui), index);
				if (repositoryStatesForUndo != NULL)
				{
					repositoryStatesForUndo[repositoryStatesForUndoLength] = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
					repositoryStatesForUndoLength = repositoryStatesForUndoLength + 1;
				}
			}

			break;
		}
		case 3:
		{
			displayProductsUI(ui);

			int index = readIndexUI(ui);
			if (index == -1)
			{
				printf("There are no products in the fridge.\n\n");
				break;
			}
			else
			{
				char productName[64];
				strcpy(productName, readProductName(productName));

				char productCategory[64];
				strcpy(productCategory, readProductCategory(productCategory));

				int productQuantity = readProductQuantity();

				Date productExpiringDate = readProductExpiringDate();

				Product* product = createProduct(productName, productCategory, productQuantity, productExpiringDate);

				updateProductFromPositionToGivenProductService(getServiceFromUI(ui), product, index);

				destroyProduct(product);
			}

			if (repositoryStatesForUndo != NULL)
			{
				repositoryStatesForUndo[repositoryStatesForUndoLength] = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
				repositoryStatesForUndoLength = repositoryStatesForUndoLength + 1;
			}
			break;
		}
		case 4:
		{
			printf("Search for products: ");
			char search_string[64];
			strcpy(search_string, readSearchString(search_string));

			displaySearchedProductsOrderedByQuantity(ui, search_string);
			break;
		}
		case 5:
		{
			printf("Give category: ");
			char category[64];
			strcpy(category, readSearchCategory(category));

			int expireRangeInDays = readExpireRangeInDays();

			displaySearchedCategoryInRange(ui, category, expireRangeInDays);

			break;
		}
		case 6:
		{
			if (repositoryStatesForUndoLength > 1)
			{
				destroyRepository(getRepositoryFromService(getServiceFromUI(ui)));
				ui->service->repository = deepCopyOfRepository(repositoryStatesForUndo[repositoryStatesForUndoLength - 1 - 1]);
				repositoryStatesForUndoLength = repositoryStatesForUndoLength - 1;
				if (repositoryStatesForRedo != NULL)
				{
					repositoryStatesForRedo[repositoryStatesForRedoLength] = deepCopyOfRepository(repositoryStatesForUndo[repositoryStatesForUndoLength]);
					repositoryStatesForRedoLength = repositoryStatesForRedoLength + 1;
				}
				destroyRepository(repositoryStatesForUndo[repositoryStatesForUndoLength]);
			}
			else
			{
				printf("There are no undos left\n\n");
			}
			break;
		}
		case 7:
		{
			if (repositoryStatesForRedoLength > 0)
			{
				destroyRepository(getRepositoryFromService(getServiceFromUI(ui)));
				ui->service->repository = deepCopyOfRepository(repositoryStatesForRedo[repositoryStatesForRedoLength - 1]);
				repositoryStatesForRedoLength = repositoryStatesForRedoLength - 1;
				if (repositoryStatesForUndo != NULL)
				{
					repositoryStatesForUndo[repositoryStatesForUndoLength] = deepCopyOfRepository(getRepositoryFromService(getServiceFromUI(ui)));
					repositoryStatesForUndoLength = repositoryStatesForUndoLength + 1;
				}
				destroyRepository(repositoryStatesForRedo[repositoryStatesForRedoLength]);
			}
			else
			{
				printf("There are no redos left\n\n");
			}
			break;
		}
		case 8:
		{
			displayProductsUI(ui);
			break;
		}
		default:
		{
			printf("Wrong input.\n\n");
			break;
		}
		}
	}
}