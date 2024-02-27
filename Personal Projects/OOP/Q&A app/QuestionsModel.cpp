#include "QuestionsModel.h"

int QuestionsModel::rowCount(const QModelIndex& parent) const
{
    return this->service.getAllQuestions().size();
}

QVariant QuestionsModel::data(const QModelIndex& index, int role) const
{
    if (role == Qt::DisplayRole)
    {
        return QString::fromStdString(this->service.getAllQuestions()[index.row()].toString());
    }

    return QVariant();
}


