#pragma once
#include <vector>

class Observer
{
public:
	virtual void updateAnswers() = 0;
};

class Observable
{
private:
	std::vector<Observer*> observers;

public:
	void addObserver(Observer* observer) { this->observers.push_back(observer); }
	std::vector<Observer*> getAllObservers() { return this->observers; }
};
