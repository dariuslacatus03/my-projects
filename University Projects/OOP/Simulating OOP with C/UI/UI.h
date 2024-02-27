#pragma once
#include "Service.h"

#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include<time.h>

typedef struct
{
	Service* service;
}UI;

void flushStdinBuffer();

UI* createUI(Service* service);

void testCreateService();

Service* getServiceFromUI(UI* ui);

void destroyUI(UI* uiToDestroy);

void printMenu();

void display_product(Product* product);

void displayProductsUI(UI* ui);

void startMenu(UI* ui);