#pragma once
#include <time.h>

typedef struct date {
	int day;
	int month;
	int year;
} Date;

Date createDate(int day, int month, int year);

int validateDate(Date product_expiring_date);

Date getCurrentDate();