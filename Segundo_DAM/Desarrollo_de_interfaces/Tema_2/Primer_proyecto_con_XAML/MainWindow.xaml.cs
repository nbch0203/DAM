using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
namespace Primer_proyecto_con_XAML
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary> 
    /// 
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            botonmostrar.Click += Botonmostrar_Click;
        }

        private void Botonmostrar_Click(object sender, RoutedEventArgs e)
        {
            TextoAmostrar.Content = "¡LO VAN A PETAR EN LAS PRÁCTICAS!";
        }
    }
}