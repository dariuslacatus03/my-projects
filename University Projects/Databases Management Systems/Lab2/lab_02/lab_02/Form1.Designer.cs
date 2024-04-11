using lab_02;

namespace lab_02
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
            this.parentTableGrid = new System.Windows.Forms.DataGridView();
            this.speciesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSetBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fishingAppDataSet = new lab_02.FishingAppDataSet();
            this.championshipBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.currentVersionBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillSpeciesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillSpeciesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.fishBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.fillFishesToolStrip = new System.Windows.Forms.ToolStrip();
            this.fillFishesToolStripButton = new System.Windows.Forms.ToolStripButton();
            this.championshipTableAdapter = new lab_02.FishingAppDataSetTableAdapters.ChampionshipTableAdapter();
            this.currentVersionTableAdapter = new lab_02.FishingAppDataSetTableAdapters.CurrentVersionTableAdapter();
            this.speciesTableAdapter = new lab_02.FishingAppDataSetTableAdapters.SpeciesTableAdapter();
            this.fishTableAdapter = new lab_02.FishingAppDataSetTableAdapters.FishTableAdapter();
            this.childTableGrid = new System.Windows.Forms.DataGridView();
            this.addButton = new System.Windows.Forms.Button();
            this.allFishTableAdapter1 = new lab_02.FishingAppDataSetTableAdapters.AllFishTableAdapter();
            ((System.ComponentModel.ISupportInitialize)(this.parentTableGrid)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTableGrid)).BeginInit();
            this.SuspendLayout();
            // 
            // parentTableGrid
            // 
            this.parentTableGrid.AutoGenerateColumns = false;
            this.parentTableGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.parentTableGrid.DataSource = this.speciesBindingSource;
            this.parentTableGrid.Location = new System.Drawing.Point(15, 80);
            this.parentTableGrid.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.parentTableGrid.Name = "parentTableGrid";
            this.parentTableGrid.RowHeadersWidth = 51;
            this.parentTableGrid.RowTemplate.Height = 24;
            this.parentTableGrid.Size = new System.Drawing.Size(524, 486);
            this.parentTableGrid.TabIndex = 2;
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
            this.fillSpeciesToolStrip.Location = new System.Drawing.Point(0, 0);
            this.fillSpeciesToolStrip.Name = "fillSpeciesToolStrip";
            this.fillSpeciesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillSpeciesToolStrip.TabIndex = 11;
            // 
            // fillSpeciesToolStripButton
            // 
            this.fillSpeciesToolStripButton.Name = "fillSpeciesToolStripButton";
            this.fillSpeciesToolStripButton.Size = new System.Drawing.Size(23, 23);
            // 
            // fillFishesToolStrip
            // 
            this.fillFishesToolStrip.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.fillFishesToolStrip.Location = new System.Drawing.Point(0, 25);
            this.fillFishesToolStrip.Name = "fillFishesToolStrip";
            this.fillFishesToolStrip.Size = new System.Drawing.Size(1452, 25);
            this.fillFishesToolStrip.TabIndex = 10;
            // 
            // fillFishesToolStripButton
            // 
            this.fillFishesToolStripButton.Name = "fillFishesToolStripButton";
            this.fillFishesToolStripButton.Size = new System.Drawing.Size(23, 23);
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
            // childTableGrid
            // 
            this.childTableGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.childTableGrid.Location = new System.Drawing.Point(737, 80);
            this.childTableGrid.Name = "childTableGrid";
            this.childTableGrid.RowHeadersWidth = 51;
            this.childTableGrid.RowTemplate.Height = 24;
            this.childTableGrid.Size = new System.Drawing.Size(536, 486);
            this.childTableGrid.TabIndex = 6;
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(581, 186);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(110, 23);
            this.addButton.TabIndex = 7;
            this.addButton.Text = "Update DB";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
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
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.childTableGrid);
            this.Controls.Add(this.fillFishesToolStrip);
            this.Controls.Add(this.fillSpeciesToolStrip);
            this.Controls.Add(this.parentTableGrid);
            this.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.parentTableGrid)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.speciesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSetBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishingAppDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.championshipBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.currentVersionBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fishBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.childTableGrid)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.DataGridView parentTableGrid;
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
        private System.Windows.Forms.DataGridView childTableGrid;
        private System.Windows.Forms.Button addButton;
        private FishingAppDataSetTableAdapters.AllFishTableAdapter allFishTableAdapter1;
    }
}

