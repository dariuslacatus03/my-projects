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

        private void fillFishBySpeciesGrid(string speciesName)
        {
            try
            {
                string sqlCommand = "select * from Fish where SpeciesName=@SpeciesName";
                SqlCommand getFishBySpecies = new SqlCommand(sqlCommand, connection);
                getFishBySpecies.Parameters.AddWithValue("@SpeciesName", speciesName);

                DataSet dataSet = new DataSet();
                SqlDataAdapter sqlDataAdapter = new SqlDataAdapter(getFishBySpecies);

                sqlDataAdapter.Fill(dataSet, "Fish");
                fishBySpeciesGrid.DataSource = dataSet.Tables["Fish"];
            }
            catch (System.Exception ex)
            {
                System.Windows.Forms.MessageBox.Show(ex.Message);
            }
        }

        private void speciesGrid_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            string speciesName = Convert.ToString(speciesGrid.Rows[e.RowIndex].Cells[0].Value);

            fillFishBySpeciesGrid(speciesName);

        }

        private void fishBySpeciesGrid_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                string fishID = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[0].Value);
                string size = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[1].Value);
                string speciesName = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[2].Value);
                string userID = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[3].Value);
                string river = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[4].Value);
                string location = Convert.ToString(fishBySpeciesGrid.Rows[e.RowIndex].Cells[5].Value);

                fishIdTextBox.Text = fishID;
                sizeTextBox.Text = size;
                speciesNameTextBox.Text = speciesName;
                userIdTextBox.Text = userID;
                riverTextBox.Text = river;
                locationTextBox.Text = location;
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

            
        }

        private void fishIdTextBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsDigit(e.KeyChar) && !char.IsControl(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void userIdTextBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsDigit(e.KeyChar) && !char.IsControl(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void sizeTextBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsDigit(e.KeyChar) && !char.IsControl(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            
            try
            {
                int fishID = Convert.ToInt32(fishIdTextBox.Text);
                double size = Convert.ToDouble(sizeTextBox.Text);
                string speciesName = speciesNameTextBox.Text;
                int userID = Convert.ToInt32(userIdTextBox.Text);
                string river = riverTextBox.Text;
                string locationName = locationTextBox.Text;


                string sqlCommand = "INSERT INTO Fish (FishID, Size, SpeciesName, UserID, River, LocationName) " +
                                    "VALUES (@FishID, @Size, @SpeciesName, @UserID, @River, @LocationName)";
                SqlCommand addFishCommand = new SqlCommand(sqlCommand, connection);
                addFishCommand.Parameters.AddWithValue("@FishID", fishID);
                addFishCommand.Parameters.AddWithValue("@Size", size);
                addFishCommand.Parameters.AddWithValue("@SpeciesName", speciesName);
                addFishCommand.Parameters.AddWithValue("@UserID", userID);
                addFishCommand.Parameters.AddWithValue("@River", river);
                addFishCommand.Parameters.AddWithValue("@LocationName", locationName);

                DataSet dataSet = new DataSet();
                SqlDataAdapter dataAdapter = new SqlDataAdapter(addFishCommand);

                dataAdapter.Fill(dataSet, "Fish");
                fishGrid.DataSource = dataSet.Tables["Fish"];

                fillFish();
                fillFishBySpeciesGrid(speciesName);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error adding fish record: " + ex.Message);
            }

        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            
            try
            {
                if (fishBySpeciesGrid.CurrentCell == null)
                {
                    MessageBox.Show("Please select a fish record to remove.");
                    return; // Exit the method if no row is selected
                }

                int rowIndex = fishBySpeciesGrid.CurrentCell.RowIndex;
                int fishID = Convert.ToInt32(fishBySpeciesGrid.Rows[rowIndex].Cells["FishID"].Value);
                string speciesName = Convert.ToString(fishBySpeciesGrid.Rows[rowIndex].Cells["SpeciesName"].Value);
                // Delete the fish record from the Fish table
                string sqlCommand = "DELETE FROM Fish WHERE FishID = @FishID";
                SqlCommand removeFishCommand = new SqlCommand(sqlCommand, connection);
                removeFishCommand.Parameters.AddWithValue("@FishID", fishID);

                DataSet dataSet = new DataSet();
                SqlDataAdapter dataAdapter = new SqlDataAdapter(removeFishCommand);

                dataAdapter.Fill(dataSet, "Fish");
                fishGrid.DataSource = dataSet.Tables["Fish"];
                fishBySpeciesGrid.DataSource = dataSet.Tables["Fish"];

                // Refresh the fish data grid after removing the fish record
                fillFish();
                fillFishBySpeciesGrid(speciesName);
                
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error removing fish record: " + ex.Message);
            }

        }

        private void updateButton_Click(object sender, EventArgs e)
        {

            try
            {
                int fishID = Convert.ToInt32(fishIdTextBox.Text);
                double size = Convert.ToDouble(sizeTextBox.Text);
                string speciesName = speciesNameTextBox.Text;
                int userID = Convert.ToInt32(userIdTextBox.Text);
                string river = riverTextBox.Text;
                string locationName = locationTextBox.Text;

                // Update the fish record in the Fish table
                string sqlCommand = "UPDATE Fish SET FishID = @FishID, Size = @Size, SpeciesName = @SpeciesName," +
                                    "UserID = @UserID, River = @River, LocationName = @LocationName WHERE FishID = @FishID";
                SqlCommand updateFishCommand = new SqlCommand(sqlCommand, connection);
                updateFishCommand.Parameters.AddWithValue("@FishID", fishID);
                updateFishCommand.Parameters.AddWithValue("@Size", size);
                updateFishCommand.Parameters.AddWithValue("@SpeciesName", speciesName);
                updateFishCommand.Parameters.AddWithValue("@UserID", userID);
                updateFishCommand.Parameters.AddWithValue("@River", river);
                updateFishCommand.Parameters.AddWithValue("@LocationName", locationName);

                DataSet dataSet = new DataSet();
                SqlDataAdapter dataAdapter = new SqlDataAdapter(updateFishCommand);

                dataAdapter.Fill(dataSet, "Fish");
                fishGrid.DataSource = dataSet.Tables["Fish"];
                fishBySpeciesGrid.DataSource = dataSet.Tables["Fish"];

                // Refresh the fish data grid after updating the fish record
                fillFish();
                fillFishBySpeciesGrid(speciesName);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error updating fish record: " + ex.Message);
            }
        }

        
    }
}
