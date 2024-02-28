#include "IdeasTableModel.h"
#include <qbrush.h>

int IdeasTableModel::rowCount(const QModelIndex& parent) const
{
	return this->service.getAllIdeas().size();
}

int IdeasTableModel::columnCount(const QModelIndex& parent) const
{
	return 4;
}

QVariant IdeasTableModel::data(const QModelIndex& index, int role) const
{
	if (role == Qt::DisplayRole)
	{
		switch (index.column())
		{
		case 0:
		{
			return QString::fromStdString(this->service.getAllIdeas().at(index.row()).getDescription());
		}
		case 1:
		{
			return QString::fromStdString(this->service.getAllIdeas().at(index.row()).getStatus());
		}
		case 2:
		{
			return QString::fromStdString(this->service.getAllIdeas().at(index.row()).getCreator());
		}
		case 3:
		{
			return QString::fromStdString(std::to_string(this->service.getAllIdeas().at(index.row()).getAct()));
		}
		default:
			break;
		}
	}

	if (role == Qt::BackgroundRole)
	{
		if (this->service.getAllIdeas().at(index.row()).getStatus() == "proposed")
		{
			return QBrush(Qt::yellow);
		}
		else
		{
			return QBrush(Qt::green);
		}
	}

	return QVariant();
}

QVariant IdeasTableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section)
			{
			case 0:
			{
				return QString("Description");
			}
			case 1:
			{
				return QString("Status");
			}
			case 2:
			{
				return QString("Creator");
			}
			case 3:
			{
				return QString("Act");
			}
			default:
				break;
			}
		}
	}
	
	return QVariant();
}
