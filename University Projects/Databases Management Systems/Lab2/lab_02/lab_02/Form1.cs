using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab_02
{
    public partial class Form1 : Form
    {

        const string connectionString = "Data Source=DESKTOP-FCVPJ3V\\SQLEXPRESS;Initial Catalog=FishingApp;User=sa;Password=admin";
        SqlConnection dbConnection = new SqlConnection(connectionString);

        SqlDataAdapter dataAdapterParentTable;
        SqlDataAdapter dataAdapterChildTable;
        SqlDataAdapter dataAdapterChildOfParentTable;

        DataSet parentAndChildDataSet = new DataSet();
        DataSet childOfParentDataSet = new DataSet();

        SqlCommandBuilder sqlCommandBuilder;

        BindingSource bindingSourceParentTable, bindingSourceChildTable;

        String parentTableName = ConfigurationManager.AppSettings["parentTableName"];
        String childTableName = ConfigurationManager.AppSettings["childTableName"];



        public Form1()
        {
            InitializeComponent();
            // Set AutoGenerateColumns for parentTableGrid
            parentTableGrid.AutoGenerateColumns = true;
            // Set AutoGenerateColumns for childTableGrid
            childTableGrid.AutoGenerateColumns = true;
        }


        private void Form1_Load(object sender, EventArgs e)
        {

            // Construct SQL queries dynamically
            string selectQueryParentTable = $"SELECT * FROM {parentTableName}";
            string selectQueryChildTable = $"SELECT * FROM {childTableName}";

            // Set up DataAdapters
            dataAdapterParentTable = new SqlDataAdapter(selectQueryParentTable, dbConnection);
            dataAdapterChildTable = new SqlDataAdapter(selectQueryChildTable, dbConnection);

            // Fill the DataSet
            dataAdapterParentTable.Fill(parentAndChildDataSet, parentTableName);
            dataAdapterChildTable.Fill(parentAndChildDataSet, childTableName);

            // Set up CommandBuilder to generate SQL commands
            sqlCommandBuilder = new SqlCommandBuilder(dataAdapterParentTable);
            sqlCommandBuilder = new SqlCommandBuilder(dataAdapterChildTable);

            // Set up BindingSources for data binding
            bindingSourceParentTable = new BindingSource();
            bindingSourceChildTable = new BindingSource();

            // Set data source for BindingSources
            bindingSourceParentTable.DataSource = parentAndChildDataSet;
            bindingSourceParentTable.DataMember = parentTableName;
            bindingSourceChildTable.DataSource = parentAndChildDataSet;
            bindingSourceChildTable.DataMember = childTableName;

            // Set data sources for your UI controls (e.g., DataGridViews)
            parentTableGrid.DataSource = bindingSourceParentTable;
            childTableGrid.DataSource = bindingSourceChildTable;

        }

        private void parentTableGrid_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                // Get the name of the primary key column of the parent table from configuration settings
                String parentToChildRelationName = ConfigurationManager.AppSettings["parentToChildRelationName"];

                // Get the name of the foreign key column of the child table from configuration settings
                String childToParentRelationName = ConfigurationManager.AppSettings["childToParentRelationName"];

                // Get the value of the selected parent's primary key column
                string parentKeyValue = parentTableGrid.Rows[e.RowIndex].Cells[parentToChildRelationName].Value.ToString();

                // Construct SQL query to retrieve child elements based on the selected parent
                string selectChildByParentQuery = $"SELECT * FROM {childTableName} WHERE {childToParentRelationName} = '{parentKeyValue}'";

                // Create a new SqlDataAdapter for the childByParentGrid
                dataAdapterChildOfParentTable = new SqlDataAdapter(selectChildByParentQuery, dbConnection);

                // Clear the existing child data from the DataSet if it exists
                if (childOfParentDataSet.Tables.Contains(childTableName))
                {
                    childOfParentDataSet.Tables[childTableName].Clear();
                }

                // Fill the DataSet of childByParentGrid
                dataAdapterChildOfParentTable.Fill(childOfParentDataSet, childTableName);

                // Set data source for the childByParentGrid
                childByParentGrid.DataSource = childOfParentDataSet.Tables[childTableName];

            }
            catch (Exception ex)
            {
                MessageBox.Show("Parent Table Grid: " + ex.ToString());
            }
        }


    }
}
