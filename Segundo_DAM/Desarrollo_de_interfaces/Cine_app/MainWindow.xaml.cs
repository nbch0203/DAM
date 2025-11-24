using System.Data;
using System.Windows;
using System.Windows.Controls;
using MySql.Data.MySqlClient;

namespace Cine_app
{
    public partial class MainWindow : Window
    {
        private readonly string connectionString;

        public MainWindow()
        {
            DotNetEnv.Env.Load();

            connectionString = Environment.GetEnvironmentVariable("DATABASE") ?? string.Empty;

            InitializeComponent();
            TestDatabaseConnection();
            CargarPeliculas();
        }

        private void TestDatabaseConnection()
        {
            if (string.IsNullOrEmpty(connectionString))
            {
                MessageBox.Show("Error: No se pudo cargar la cadena de conexión desde .env");
                return;
            }

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    MessageBox.Show("Conexión exitosa a la base de datos");
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"Error de conexión: {ex.Message}");
                }
            }
        }

        public void onclick(object sender, RoutedEventArgs e)
        {
            CargarPeliculas();
        }

        private void CargarPeliculas()
        {
            string sql = "SELECT * FROM Peliculas";
            try
            {
                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    using (MySqlCommand command = new MySqlCommand(sql, conn))
                    {
                        DataTable dt = new DataTable();
                        MySqlDataAdapter adapter = new MySqlDataAdapter(command);
                        adapter.Fill(dt);

                        dgPeliculas.ItemsSource = dt.DefaultView;
                    }
                }
                MessageBox.Show("Películas cargadas exitosamente.");
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al consultar la base de datos: {ex.Message}");
            }
        }

        private void DataGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (dgPeliculas.SelectedItem != null)
            {
                DataRowView row = (DataRowView)dgPeliculas.SelectedItem;
                string titulo = row["Titulo"].ToString();
                MessageBox.Show($"Seleccionaste: {titulo}");
            }
        }
    }
}