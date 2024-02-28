#pragma once
#include <vector>

using namespace std;

class Observer
{
public:
	virtual void update() = 0;
};

class Observable
{
private:
	vector<Observer*> observers;

public:

	void addObserver(Observer* o);
	vector<Observer*>& getObservers() { return this->observers; }

};

