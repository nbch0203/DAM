using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using Cine_app.Models;
using Cine_app.Services;
using Cine_app.Servicios;

namespace Cine_app.Views
{
    public partial class CarteleraWindow : Window
    {
        // IDE0044: Hacer el archivo de solo lectura
        private readonly ServicioBaseDeDatos _dbService;
        // IDE0044: Hacer el archivo de solo lectura
        private readonly List<Pelicula> _peliculas = new();
        // CS8618: Inicializar campos no anulables
        private readonly TextBlock txtUsuario;
        private readonly Button btnCerrarSesion;
        private readonly Button btnIniciarSesion;
        // Referencias a controles de la UI
        private readonly StackPanel panelPeliculas;
        private readonly ScrollViewer scrollPeliculas;
        private readonly Grid pnlLoading;
        private readonly Grid pnlSinPeliculas;

        public CarteleraWindow()
        {
            InitializeComponent();
            _dbService = new ServicioBaseDeDatos();

            // Inicializar referencias a controles de la UI
            txtUsuario = (TextBlock)FindName("txtUsuario")!;
            btnCerrarSesion = (Button)FindName("btnCerrarSesion")!;
            btnIniciarSesion = (Button)FindName("btnIniciarSesion")!;
            panelPeliculas = (StackPanel)FindName("panelPeliculas")!;
            scrollPeliculas = (ScrollViewer)FindName("scrollPeliculas")!;
            pnlLoading = (Grid)FindName("pnlLoading")!;
            pnlSinPeliculas = (Grid)FindName("pnlSinPeliculas")!;

            // Configurar UI según estado de sesión
            ActualizarEstadoSesion();

            // Suscribirse a eventos de sesión
            ServicioSesion.Instance.SesionIniciada += (s, e) => ActualizarEstadoSesion();
            ServicioSesion.Instance.SesionCerrada += (s, e) => ActualizarEstadoSesion();

            // Cargar películas
            Loaded += async (s, e) => await CargarPeliculasAsync();
        }

        private void ActualizarEstadoSesion()
        {
            if (ServicioSesion.Instance.EstaAutenticado)
            {
                var usuario = ServicioSesion.Instance.UsuarioActual;
                txtUsuario.Text = $"👤 {usuario?.NombreCompleto}";
                btnCerrarSesion.Visibility = Visibility.Visible;
                btnIniciarSesion.Visibility = Visibility.Collapsed;
            }
            else
            {
                txtUsuario.Text = "Invitado";
                btnCerrarSesion.Visibility = Visibility.Collapsed;
                btnIniciarSesion.Visibility = Visibility.Visible;
            }
        }

        private async Task CargarPeliculasAsync()
        {
            try
            {
                pnlLoading.Visibility = Visibility.Visible;
                scrollPeliculas.Visibility = Visibility.Collapsed;
                pnlSinPeliculas.Visibility = Visibility.Collapsed;
                panelPeliculas.Children.Clear();

                var peliculas = await _dbService.ObtenerPeliculasActivasAsync();
                _peliculas.Clear();
                _peliculas.AddRange(peliculas);

                // CA1860: Usar Count en vez de Any
                if (_peliculas.Count > 0)
                {
                    foreach (var pelicula in _peliculas)
                    {
                        panelPeliculas.Children.Add(CrearTarjetaPelicula(pelicula));
                    }
                    scrollPeliculas.Visibility = Visibility.Visible;
                }
                else
                {
                    pnlSinPeliculas.Visibility = Visibility.Visible;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al cargar películas: {ex.Message}",
                              "Error",
                              MessageBoxButton.OK,
                              MessageBoxImage.Error);
            }
            finally
            {
                pnlLoading.Visibility = Visibility.Collapsed;
            }
        }

        private Border CrearTarjetaPelicula(Pelicula pelicula)
        {
            var card = new Border
            {
                Style = (Style)FindResource("PeliculaCardStyle"),
                Width = 250,
                Height = 420,
                Tag = pelicula
            };

            var grid = new Grid();
            grid.RowDefinitions.Add(new RowDefinition { Height = new GridLength(300) });
            grid.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });

            var imagen = new Image
            {
                Stretch = Stretch.UniformToFill,
                Height = 300
            };

            try
            {
                if (!string.IsNullOrEmpty(pelicula.ImagenUrl))
                {
                    imagen.Source = new BitmapImage(new Uri(pelicula.ImagenUrl, UriKind.Absolute));
                }
                else
                {
                    imagen.Source = CrearImagenPorDefecto(pelicula.Titulo);
                }
            }
            catch
            {
                imagen.Source = CrearImagenPorDefecto(pelicula.Titulo);
            }

            Grid.SetRow(imagen, 0);
            grid.Children.Add(imagen);

            var infoPanel = new StackPanel
            {
                Margin = new Thickness(15),
                VerticalAlignment = VerticalAlignment.Center
            };

            var titulo = new TextBlock
            {
                Text = pelicula.Titulo,
                FontSize = 16,
                FontWeight = FontWeights.Bold,
                TextWrapping = TextWrapping.Wrap,
                Foreground = new SolidColorBrush(Color.FromRgb(26, 26, 46)),
                TextAlignment = TextAlignment.Center,
                MaxHeight = 60
            };
            infoPanel.Children.Add(titulo);

            if (!string.IsNullOrEmpty(pelicula.Genero))
            {
                var genero = new TextBlock
                {
                    Text = pelicula.Genero,
                    FontSize = 12,
                    Foreground = new SolidColorBrush(Color.FromRgb(102, 102, 102)),
                    TextAlignment = TextAlignment.Center,
                    Margin = new Thickness(0, 5, 0, 0)
                };
                infoPanel.Children.Add(genero);
            }

            if (pelicula.Duracion.HasValue)
            {
                var duracion = new TextBlock
                {
                    Text = $"⏱ {pelicula.Duracion} min",
                    FontSize = 12,
                    Foreground = new SolidColorBrush(Color.FromRgb(102, 102, 102)),
                    TextAlignment = TextAlignment.Center,
                    Margin = new Thickness(0, 5, 0, 0)
                };
                infoPanel.Children.Add(duracion);
            }

            Grid.SetRow(infoPanel, 1);
            grid.Children.Add(infoPanel);

            card.Child = grid;

            card.MouseLeftButtonDown += (s, e) => SeleccionarPelicula(pelicula);

            return card;
        }

        // CA1859: Cambiar tipo de retorno a RenderTargetBitmap
        private RenderTargetBitmap CrearImagenPorDefecto(string titulo)
        {
            var visual = new DrawingVisual();
            using (var context = visual.RenderOpen())
            {
                context.DrawRectangle(
                    new SolidColorBrush(Color.FromRgb(26, 26, 46)),
                    null,
                    new Rect(0, 0, 250, 300)
                );

                var text = new FormattedText(
                    "🎬\n" + titulo,
                    System.Globalization.CultureInfo.CurrentCulture,
                    FlowDirection.LeftToRight,
                    new Typeface("Segoe UI"),
                    20,
                    Brushes.White,
                    VisualTreeHelper.GetDpi(this).PixelsPerDip
                )
                {
                    MaxTextWidth = 230,
                    TextAlignment = TextAlignment.Center
                };

                context.DrawText(text, new Point(10, 130));
            }

            var bitmap = new RenderTargetBitmap(250, 300, 96, 96, PixelFormats.Pbgra32);
            bitmap.Render(visual);
            return bitmap;
        }

        private void SeleccionarPelicula(Pelicula pelicula)
        {
            // CS0246: LoginWindow y SeleccionSesionWindow no existen, se requiere definición/importación
            MessageBox.Show("Funcionalidad de selección de película no implementada: faltan las ventanas LoginWindow y SeleccionSesionWindow.", "Aviso", MessageBoxButton.OK, MessageBoxImage.Information);
        }

        private void BtnIniciarSesion_Click(object sender, RoutedEventArgs e)
        {
            MessageBox.Show("Funcionalidad de inicio de sesión no implementada: falta la ventana LoginWindow.", "Aviso", MessageBoxButton.OK, MessageBoxImage.Information);
        }

        private void BtnCerrarSesion_Click(object sender, RoutedEventArgs e)
        {
            var result = MessageBox.Show(
                "¿Estás seguro de que deseas cerrar sesión?",
                "Cerrar Sesión",
                MessageBoxButton.YesNo,
                MessageBoxImage.Question
            );

            if (result == MessageBoxResult.Yes)
            {
                ServicioSesion.Instance.CerrarSesion();
                MessageBox.Show("Sesión cerrada correctamente", "Información", MessageBoxButton.OK, MessageBoxImage.Information);
            }
        }
    }
}