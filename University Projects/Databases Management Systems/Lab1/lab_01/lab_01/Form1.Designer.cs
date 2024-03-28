namespace lab_01
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.speciesGrid = new System.Windows.Forms.DataGridView();
            this.speciesNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.mediumSizeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.recordDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.speciesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSet = new lab_01.FishingAppDataSet();
            this.championshipBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currentVersionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillSpeciesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillSpeciesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.fishGrid = new System.Windows.Forms.DataGridView();
            this.fishIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.sizeDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.speciesNameDataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.userIDDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.riverDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.locationNameDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.fishBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillFishesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillFishesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.championshipTableAdapter = new lab_01.FishingAppDataSetTableAdapters.ChampionshipTableAdapter();
            this.currentVersionTableAdapter = new lab_01.FishingAppDataSetTableAdapters.CurrentVersionTableAdapter();
            this.speciesTableAdapter = new lab_01.FishingAppDataSetTableAdapters.SpeciesTableAdapter();
            this.fishTableAdapter = new lab_01.FishingAppDataSetTableAdapters.FishTableAdapter();
            this.fishBySpeciesGrid = new System.Windows.Forms.DataGridView();
            this.addButton = new System.Windows.Forms.Button();
            this.updateButton = new System.Windows.Forms.Button();
            this.removeButton = new System.Windows.Forms.Button();
            this.fishIdTextBox = new System.Windows.Forms.TextBox();
            this.fishIdLabel = new System.Windows.Forms.Label();
            this.userIdLabel = new System.Windows.Forms.Label();
            this.userIdTextBox = new System.Windows.Forms.TextBox();
            this.locationLabel = new System.Windows.Forms.Label();
            this.locationTextBox = new System.Windows.Forms.TextBox();
            this.riverLabel = new System.Windows.Forms.Label();
            this.riverTextBox = new System.Windows.Forms.TextBox();
            this.sizeLabel = new System.Windows.Forms.Label();
            this.sizeTextBox = new System.Windows.Forms.TextBox();
            this.speciesIdLabel = new System.Windows.Forms.Label();
            this.speciesNameTextBox = new System.Windows.Forms.TextBox();
            this.allFishTableAdapter1 = new lab_01.FishingAppDataSetTableAdapters.AllFishTableAdapter();
            ((System.ComponentModel.ISupportInitialize)(this.speciesGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).BeginInit();
            this.fillSpeciesToolStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).BeginInit();
            this.fillFishesToolStrip.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishBySpeciesGrid)).BeginInit();
            this.SuspendLayout();
            // 
            // speciesGrid
            // 
            this.speciesGrid.AutoGenerateColumns = false;
            this.speciesGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.speciesGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.speciesNameDataGridViewTextBoxColumn,
            this.mediumSizeDataGridViewTextBoxColumn,
            this.recordDataGridViewTextBoxColumn});
            this.speciesGrid.DataSource = this.speciesBindingSource;
            this.speciesGrid.Location = new System.Drawing.Point(15, 80);
            this.speciesGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.speciesGrid.Name = "speciesGrid";
            this.speciesGrid.RowHeadersWidth = 51;
            this.speciesGrid.RowTemplate.Height = 24;
            this.speciesGrid.Size = new System.Drawing.Size(581, 199);
            this.speciesGrid.TabIndex = 2;
            this.speciesGrid.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.speciesGrid_CellClick);
            // 
            // speciesNameDataGridViewTextBoxColumn
            // 
            this.speciesNameDataGridViewTextBoxColumn.DataPropertyName = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn.HeaderText = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.speciesNameDataGridViewTextBoxColumn.Name = "speciesNameDataGridViewTextBoxColumn";
            this.speciesNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // mediumSizeDataGridViewTextBoxColumn
            // 
            this.mediumSizeDataGridViewTextBoxColumn.DataPropertyName = "MediumSize";
            this.mediumSizeDataGridViewTextBoxColumn.HeaderText = "MediumSize";
            this.mediumSizeDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.mediumSizeDataGridViewTextBoxColumn.Name = "mediumSizeDataGridViewTextBoxColumn";
            this.mediumSizeDataGridViewTextBoxColumn.Width = 125;
            // 
            // recordDataGridViewTextBoxColumn
            // 
            this.recordDataGridViewTextBoxColumn.DataPropertyName = "Record";
            this.recordDataGridViewTextBoxColumn.HeaderText = "Record";
            this.recordDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.recordDataGridViewTextBoxColumn.Name = "recordDataGridViewTextBoxColumn";
            this.recordDataGridViewTextBoxColumn.Width = 125;
            // 
            // speciesBindingSource
            // 
            this.speciesBindingSource.DataMember = "Species";
            this.speciesBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // fishingAppDataSetBindingSource
            // 
            this.fishingAppDataSetBindingSource.DataSource = this.fishingAppDataSet;
            this.fishingAppDataSetBindingSource.Position = 0;
            // 
            // fishingAppDataSet
            // 
            this.fishingAppDataSet.DataSetName = "FishingAppDataSet";
            this.fishingAppDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // fillSpeciesToolStrip
            // 
            this.fillSpeciesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillSpeciesToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fillSpeciesToolStripButton});
            this.fillSpeciesToolStrip.Location = new System.Drawing.Point(0, 0);
            this.fillSpeciesToolStrip.Name = "fillSpeciesToolStrip";
            this.fillSpeciesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillSpeciesToolStrip.TabIndex = 3;
            this.fillSpeciesToolStrip.Text = "fillSpeciesToolStrip";
            // 
            // fillSpeciesToolStripButton
            // 
            this.fillSpeciesToolStripButton.Name = "fillSpeciesToolStripButton";
            this.fillSpeciesToolStripButton.Size = new System.Drawing.Size(29, 22);
            // 
            // fishGrid
            // 
            this.fishGrid.AutoGenerateColumns = false;
            this.fishGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.fishGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.fishIDDataGridViewTextBoxColumn,
            this.sizeDataGridViewTextBoxColumn,
            this.speciesNameDataGridViewTextBoxColumn1,
            this.userIDDataGridViewTextBoxColumn,
            this.riverDataGridViewTextBoxColumn,
            this.locationNameDataGridViewTextBoxColumn});
            this.fishGrid.DataSource = this.fishBindingSource;
            this.fishGrid.Location = new System.Drawing.Point(15, 392);
            this.fishGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.fishGrid.Name = "fishGrid";
            this.fishGrid.RowHeadersWidth = 51;
            this.fishGrid.RowTemplate.Height = 24;
            this.fishGrid.Size = new System.Drawing.Size(1085, 192);
            this.fishGrid.TabIndex = 4;
            // 
            // fishIDDataGridViewTextBoxColumn
            // 
            this.fishIDDataGridViewTextBoxColumn.DataPropertyName = "FishID";
            this.fishIDDataGridViewTextBoxColumn.HeaderText = "FishID";
            this.fishIDDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.fishIDDataGridViewTextBoxColumn.Name = "fishIDDataGridViewTextBoxColumn";
            this.fishIDDataGridViewTextBoxColumn.Width = 125;
            // 
            // sizeDataGridViewTextBoxColumn
            // 
            this.sizeDataGridViewTextBoxColumn.DataPropertyName = "Size";
            this.sizeDataGridViewTextBoxColumn.HeaderText = "Size";
            this.sizeDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.sizeDataGridViewTextBoxColumn.Name = "sizeDataGridViewTextBoxColumn";
            this.sizeDataGridViewTextBoxColumn.Width = 125;
            // 
            // speciesNameDataGridViewTextBoxColumn1
            // 
            this.speciesNameDataGridViewTextBoxColumn1.DataPropertyName = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn1.HeaderText = "SpeciesName";
            this.speciesNameDataGridViewTextBoxColumn1.MinimumWidth = 6;
            this.speciesNameDataGridViewTextBoxColumn1.Name = "speciesNameDataGridViewTextBoxColumn1";
            this.speciesNameDataGridViewTextBoxColumn1.Width = 125;
            // 
            // userIDDataGridViewTextBoxColumn
            // 
            this.userIDDataGridViewTextBoxColumn.DataPropertyName = "UserID";
            this.userIDDataGridViewTextBoxColumn.HeaderText = "UserID";
            this.userIDDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.userIDDataGridViewTextBoxColumn.Name = "userIDDataGridViewTextBoxColumn";
            this.userIDDataGridViewTextBoxColumn.Width = 125;
            // 
            // riverDataGridViewTextBoxColumn
            // 
            this.riverDataGridViewTextBoxColumn.DataPropertyName = "River";
            this.riverDataGridViewTextBoxColumn.HeaderText = "River";
            this.riverDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.riverDataGridViewTextBoxColumn.Name = "riverDataGridViewTextBoxColumn";
            this.riverDataGridViewTextBoxColumn.Width = 125;
            // 
            // locationNameDataGridViewTextBoxColumn
            // 
            this.locationNameDataGridViewTextBoxColumn.DataPropertyName = "LocationName";
            this.locationNameDataGridViewTextBoxColumn.HeaderText = "LocationName";
            this.locationNameDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.locationNameDataGridViewTextBoxColumn.Name = "locationNameDataGridViewTextBoxColumn";
            this.locationNameDataGridViewTextBoxColumn.Width = 125;
            // 
            // fishBindingSource
            // 
            this.fishBindingSource.DataMember = "Fish";
            this.fishBindingSource.DataSource = this.fishingAppDataSetBindingSource;
            // 
            // fillFishesToolStrip
            // 
            this.fillFishesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillFishesToolStrip.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.fillFishesToolStripButton});
            this.fillFishesToolStrip.Location = new System.Drawing.Point(0, 25);
            this.fillFishesToolStrip.Name = "fillFishesToolStrip";
            this.fillFishesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillFishesToolStrip.TabIndex = 5;
            this.fillFishesToolStrip.Text = "fillFishesToolStrip";
            // 
            // fillFishesToolStripButton
            // 
            this.fillFishesToolStripButton.Name = "fillFishesToolStripButton";
            this.fillFishesToolStripButton.Size = new System.Drawing.Size(29, 22);
            // 
            // championshipTableAdapter
            // 
            this.championshipTableAdapter.ClearBeforeFill = true;
            // 
            // currentVersionTableAdapter
            // 
            this.currentVersionTableAdapter.ClearBeforeFill = true;
            // 
            // speciesTableAdapter
            // 
            this.speciesTableAdapter.ClearBeforeFill = true;
            // 
            // fishTableAdapter
            // 
            this.fishTableAdapter.ClearBeforeFill = true;
            // 
            // fishBySpeciesGrid
            // 
            this.fishBySpeciesGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.fishBySpeciesGrid.Location = new System.Drawing.Point(690, 80);
            this.fishBySpeciesGrid.Name = "fishBySpeciesGrid";
            this.fishBySpeciesGrid.RowHeadersWidth = 51;
            this.fishBySpeciesGrid.RowTemplate.Height = 24;
            this.fishBySpeciesGrid.Size = new System.Drawing.Size(583, 199);
            this.fishBySpeciesGrid.TabIndex = 6;
            this.fishBySpeciesGrid.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.fishBySpeciesGrid_CellClick);
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(15, 313);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(75, 23);
            this.addButton.TabIndex = 7;
            this.addButton.Text = "Add Fish";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // updateButton
            // 
            this.updateButton.Location = new System.Drawing.Point(96, 313);
            this.updateButton.Name = "updateButton";
            this.updateButton.Size = new System.Drawing.Size(92, 23);
            this.updateButton.TabIndex = 8;
            this.updateButton.Text = "Update Fish";
            this.updateButton.UseVisualStyleBackColor = true;
            this.updateButton.Click += new System.EventHandler(this.updateButton_Click);
            // 
            // removeButton
            // 
            this.removeButton.Location = new System.Drawing.Point(194, 313);
            this.removeButton.Name = "removeButton";
            this.removeButton.Size = new System.Drawing.Size(99, 23);
            this.removeButton.TabIndex = 9;
            this.removeButton.Text = "Remove Fish";
            this.removeButton.UseVisualStyleBackColor = true;
            this.removeButton.Click += new System.EventHandler(this.removeButton_Click);
            // 
            // fishIdTextBox
            // 
            this.fishIdTextBox.Location = new System.Drawing.Point(465, 295);
            this.fishIdTextBox.Name = "fishIdTextBox";
            this.fishIdTextBox.Size = new System.Drawing.Size(78, 22);
            this.fishIdTextBox.TabIndex = 10;
            this.fishIdTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.fishIdTextBox_KeyPress);
            // 
            // fishIdLabel
            // 
            this.fishIdLabel.AutoSize = true;
            this.fishIdLabel.Location = new System.Drawing.Point(408, 298);
            this.fishIdLabel.Name = "fishIdLabel";
            this.fishIdLabel.Size = new System.Drawing.Size(51, 16);
            this.fishIdLabel.TabIndex = 11;
            this.fishIdLabel.Text = "Fish ID:";
            // 
            // userIdLabel
            // 
            this.userIdLabel.AutoSize = true;
            this.userIdLabel.Location = new System.Drawing.Point(603, 297);
            this.userIdLabel.Name = "userIdLabel";
            this.userIdLabel.Size = new System.Drawing.Size(55, 16);
            this.userIdLabel.TabIndex = 13;
            this.userIdLabel.Text = "User ID:";
            // 
            // userIdTextBox
            // 
            this.userIdTextBox.Location = new System.Drawing.Point(664, 294);
            this.userIdTextBox.Name = "userIdTextBox";
            this.userIdTextBox.Size = new System.Drawing.Size(78, 22);
            this.userIdTextBox.TabIndex = 12;
            this.userIdTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.userIdTextBox_KeyPress);
            // 
            // locationLabel
            // 
            this.locationLabel.AutoSize = true;
            this.locationLabel.Location = new System.Drawing.Point(800, 331);
            this.locationLabel.Name = "locationLabel";
            this.locationLabel.Size = new System.Drawing.Size(61, 16);
            this.locationLabel.TabIndex = 15;
            this.locationLabel.Text = "Location:";
            // 
            // locationTextBox
            // 
            this.locationTextBox.Location = new System.Drawing.Point(867, 328);
            this.locationTextBox.Name = "locationTextBox";
            this.locationTextBox.Size = new System.Drawing.Size(78, 22);
            this.locationTextBox.TabIndex = 14;
            // 
            // riverLabel
            // 
            this.riverLabel.AutoSize = true;
            this.riverLabel.Location = new System.Drawing.Point(819, 298);
            this.riverLabel.Name = "riverLabel";
            this.riverLabel.Size = new System.Drawing.Size(42, 16);
            this.riverLabel.TabIndex = 17;
            this.riverLabel.Text = "River:";
            // 
            // riverTextBox
            // 
            this.riverTextBox.Location = new System.Drawing.Point(867, 295);
            this.riverTextBox.Name = "riverTextBox";
            this.riverTextBox.Size = new System.Drawing.Size(78, 22);
            this.riverTextBox.TabIndex = 16;
            // 
            // sizeLabel
            // 
            this.sizeLabel.AutoSize = true;
            this.sizeLabel.Location = new System.Drawing.Point(622, 331);
            this.sizeLabel.Name = "sizeLabel";
            this.sizeLabel.Size = new System.Drawing.Size(36, 16);
            this.sizeLabel.TabIndex = 19;
            this.sizeLabel.Text = "Size:";
            // 
            // sizeTextBox
            // 
            this.sizeTextBox.Location = new System.Drawing.Point(664, 328);
            this.sizeTextBox.Name = "sizeTextBox";
            this.sizeTextBox.Size = new System.Drawing.Size(78, 22);
            this.sizeTextBox.TabIndex = 18;
            this.sizeTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.sizeTextBox_KeyPress);
            // 
            // speciesIdLabel
            // 
            this.speciesIdLabel.AutoSize = true;
            this.speciesIdLabel.Location = new System.Drawing.Point(359, 331);
            this.speciesIdLabel.Name = "speciesIdLabel";
            this.speciesIdLabel.Size = new System.Drawing.Size(100, 16);
            this.speciesIdLabel.TabIndex = 21;
            this.speciesIdLabel.Text = "Species Name:";
            // 
            // speciesNameTextBox
            // 
            this.speciesNameTextBox.Location = new System.Drawing.Point(465, 328);
            this.speciesNameTextBox.Name = "speciesNameTextBox";
            this.speciesNameTextBox.Size = new System.Drawing.Size(78, 22);
            this.speciesNameTextBox.TabIndex = 20;
            // 
            // allFishTableAdapter1
            // 
            this.allFishTableAdapter1.ClearBeforeFill = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1452, 630);
            this.Controls.Add(this.speciesIdLabel);
            this.Controls.Add(this.speciesNameTextBox);
            this.Controls.Add(this.sizeLabel);
            this.Controls.Add(this.sizeTextBox);
            this.Controls.Add(this.riverLabel);
            this.Controls.Add(this.riverTextBox);
            this.Controls.Add(this.locationLabel);
            this.Controls.Add(this.locationTextBox);
            this.Controls.Add(this.userIdLabel);
            this.Controls.Add(this.userIdTextBox);
            this.Controls.Add(this.fishIdLabel);
            this.Controls.Add(this.fishIdTextBox);
            this.Controls.Add(this.removeButton);
            this.Controls.Add(this.updateButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.fishBySpeciesGrid);
            this.Controls.Add(this.fillFishesToolStrip);
            this.Controls.Add(this.fishGrid);
            this.Controls.Add(this.fillSpeciesToolStrip);
            this.Controls.Add(this.speciesGrid);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.speciesGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).EndInit();
            this.fillSpeciesToolStrip.ResumeLayout(false);
            this.fillSpeciesToolStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).EndInit();
            this.fillFishesToolStrip.ResumeLayout(false);
            this.fillFishesToolStrip.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.fishBySpeciesGrid)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.DataGridView speciesGrid;
        private System.Windows.Forms.BindingSource fishingAppDataSetBindingSource;
        private FishingAppDataSet fishingAppDataSet;
        private System.Windows.Forms.BindingSource championshipBindingSource;
        private FishingAppDataSetTableAdapters.ChampionshipTableAdapter championshipTableAdapter;
        private System.Windows.Forms.BindingSource currentVersionBindingSource;
        private FishingAppDataSetTableAdapters.CurrentVersionTableAdapter currentVersionTableAdapter;
        private System.Windows.Forms.BindingSource speciesBindingSource;
        private FishingAppDataSetTableAdapters.SpeciesTableAdapter speciesTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn speciesNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn mediumSizeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn recordDataGridViewTextBoxColumn;
        private System.Windows.Forms.ToolStrip fillSpeciesToolStrip;
        private System.Windows.Forms.ToolStripButton fillSpeciesToolStripButton;
        private System.Windows.Forms.DataGridView fishGrid;
        private System.Windows.Forms.BindingSource fishBindingSource;
        private FishingAppDataSetTableAdapters.FishTableAdapter fishTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn fishIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn sizeDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn speciesNameDataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn userIDDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn riverDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn locationNameDataGridViewTextBoxColumn;
        private System.Windows.Forms.ToolStrip fillFishesToolStrip;
        private System.Windows.Forms.ToolStripButton fillFishesToolStripButton;
        private System.Windows.Forms.DataGridView fishBySpeciesGrid;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.Button removeButton;
        private System.Windows.Forms.TextBox fishIdTextBox;
        private System.Windows.Forms.Label fishIdLabel;
        private System.Windows.Forms.Label userIdLabel;
        private System.Windows.Forms.TextBox userIdTextBox;
        private System.Windows.Forms.Label locationLabel;
        private System.Windows.Forms.TextBox locationTextBox;
        private System.Windows.Forms.Label riverLabel;
        private System.Windows.Forms.TextBox riverTextBox;
        private System.Windows.Forms.Label sizeLabel;
        private System.Windows.Forms.TextBox sizeTextBox;
        private System.Windows.Forms.Label speciesIdLabel;
        private System.Windows.Forms.TextBox speciesNameTextBox;
        private FishingAppDataSetTableAdapters.AllFishTableAdapter allFishTableAdapter1;
    }
}

