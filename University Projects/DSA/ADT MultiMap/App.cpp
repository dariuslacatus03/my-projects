#include <iostream>
#include "MultiMap.h"
#include "ExtendedTest.h"
#include "ShortTest.h"
#include "MultiMapIterator.h"
#include <crtdbg.h>
#define _CRTDBG_MAP_ALLOC
using namespace std;


int main() {


	testAll();
	testAllExtended();
	cout << "All done.\n";
	_CrtDumpMemoryLeaks();
	system("pause");
}
