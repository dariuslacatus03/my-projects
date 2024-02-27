#include "Date.h"


Date createDate(int day, int month, int year)
{
	Date date;
	date.day = day;
	date.month = month;
	date.year = year;
	return date;
}

int validateDate(Date product_expiring_date)
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

Date getCurrentDate()
{
	time_t t = time(NULL);

	struct tm tm = *localtime(&t);

	Date currentDate;
	currentDate.day = tm.tm_mday;
	currentDate.month = tm.tm_mon + 1;
	currentDate.year = tm.tm_year + 1900;

	return currentDate;
}