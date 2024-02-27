#define _CRTDBG_MAP_ALLOC
#include <crtdbg.h>
#include "UI.h"
#include <stdio.h>

int main()
{
	testCreateProduct();
	testGettersAndSettersProducts();
	testCreateRepository();
	testResizeRepositoryToGivenCapacity();
	testGettersAndSettersRepository();
	testAddProductToEndRepository();
	testRemoveProductFromPositionRepository();
	testCreateService();
	testCreateUI();
	testProductValidations();
	testAddProductToEndService();
	testRemoveProductFromPositionService();
	testUpdateProductFromPositionToGivenProduct();
	testSortRepositoryByQuantity();
	testFindProductInRepository();
	testIsLeapYear();
	testMonthDaysCounter();
	testfindDaysPassedSinceBaseDate();
	testFindDifferenceBetweenTwoDates();

	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	UI* ui = createUI(service);

	startMenu(ui);

	destroyUI(ui);

	_CrtDumpMemoryLeaks();
}

