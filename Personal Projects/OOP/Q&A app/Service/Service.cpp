#pragma once
#include "Service.h"

#include <algorithm>
int Service::getNumberOfAnswers(Question question)
{
	int count = 0;
	for (Answer answer : this->getAllAnswers())
	{
		if (answer.getQuestionId() == question.getId())
			count++;
	}
	return count;
}
void Service::sortQuestions()
{
	std::vector<Question>& sortedQuestions = this->repository.getAllQuestions();

	sort(sortedQuestions.begin(), sortedQuestions.end(), [this](Question q1, Question q2) {
		return getNumberOfAnswers(q1) > getNumberOfAnswers(q2);
		});
}
