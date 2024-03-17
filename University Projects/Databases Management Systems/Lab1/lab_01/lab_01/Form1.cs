using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace lab_01
{
    public partial class Form1 : Form
    {

        const string connectionString = "Data Source=DESKTOP-FCVPJ3V\\SQLEXPRESS;Initial Catalog=FishingApp;User=sa;Password=admin";
        SqlConnection connection = new SqlConnection(connectionString);
        public Form1()
        {
            InitializeComponent();
        }


        private void Form1_Load(object sender, EventArgs e)
        {
            this.fillSpecies();
            this.fillFish();
        }


        private void fillSpecies()
        {
            try
            {
                string sqlCommand = "select * from Species";
                SqlCommand getSpecies = new SqlCommand(sqlCommand, connection);

                SqlDataAdapter sqlDataAdapter = new SqlDataAdapter(getSpecies);
                DataSet dataSet = new DataSet();

                sqlDataAdapter.Fill(dataSet, "Species");
                speciesGrid.DataSource = dataSet.Tables["Species"];
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }
        }

        private void fillFish()
        {
            try
            {
                string sqlCommand = "select * from Fish";
                SqlCommand getFish = new SqlCommand(sqlCommand, connection);

                SqlDataAdapter sqlDataAdapter = new SqlDataAdapter(getFish);
                DataSet dataSet = new DataSet();

                sqlDataAdapter.Fill(dataSet, "Fish");
                fishGrid.DataSource = dataSet.Tables["Fish"];
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }

        }

        private void speciesGrid_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            var speciesID = speciesGrid.Rows[e.RowIndex].Cells[0].Value;

            string sqlCommand = "SELECT * FROM FISH WHERE SpeciesName=@SpeciesID";

            SqlCommand getFishBySpecies = new SqlCommand(sqlCommand, connection);
            getFishBySpecies.Parameters.AddWithValue("@SpeciesID", speciesID);

            DataSet dataSet = new DataSet();
            SqlDataAdapter dataAdapter = new SqlDataAdapter(getFishBySpecies);

            dataAdapter.Fill(dataSet, "Fish");
            fishBySpeciesGrid.DataSource = dataSet.Tables["Fish"];

        }
    }
}
