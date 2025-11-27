using System.Windows;
using Cine_app.Services;
using Cine_app.Servicios;

namespace Cine_app.Views
{
    public partial class LoginWindow : Window
    {
        private readonly ServicioBaseDeDatos _dbService;

        public LoginWindow()
        {
            InitializeComponent();
            _dbService = new ServicioBaseDeDatos();
        }

        private async void BtnLogin_Click(object sender, RoutedEventArgs e)
        {
            txtError.Visibility = Visibility.Collapsed;

            if (string.IsNullOrWhiteSpace(txtEmail.Text))
            {
                MostrarError("Por favor, ingresa tu email");
                return;
            }

            if (string.IsNullOrWhiteSpace(txtPassword.Password))
            {
                MostrarError("Por favor, ingresa tu contraseña");
                return;
            }

            btnLogin.IsEnabled = false;
            btnLogin.Content = "Iniciando sesión...";

            try
            {
                var usuario = await _dbService.ValidarUsuarioAsync(txtEmail.Text.Trim(), txtPassword.Password);

                if (usuario != null)
                {
                    ServicioSesion.Instance.IniciarSesion(usuario);

                    var carteleraWindow = new CarteleraWindow();
                    carteleraWindow.Show();
                    this.Close();
                }
                else
                {
                    MostrarError("Email o contraseña incorrectos");
                }
            }
            catch (Exception ex)
            {
                MostrarError($"Error al iniciar sesión: {ex.Message}");
            }
            finally
            {
                btnLogin.IsEnabled = true;
                btnLogin.Content = "Iniciar Sesión";
            }
        }

        private void BtnRegistro_Click(object sender, RoutedEventArgs e)
        {
            var registroWindow = new RegistroWindow();
            registroWindow.ShowDialog();
        }

        private void BtnInvitado_Click(object sender, RoutedEventArgs e)
        {
            var carteleraWindow = new CarteleraWindow();
            carteleraWindow.Show();
            this.Close();
        }

        private void MostrarError(string mensaje)
        {
            txtError.Text = mensaje;
            txtError.Visibility = Visibility.Visible;
        }
    }
}