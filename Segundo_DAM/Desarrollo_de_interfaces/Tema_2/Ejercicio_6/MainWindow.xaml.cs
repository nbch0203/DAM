using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Ejercicio_6
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            List<User> users = new List<User>
 
            {
                new User { ID = 1, Name = "Carlos", Email = "carlos@example.com" },
                new User { ID = 2, Name = "Maria", Email = "maria@example.com" },
            };
            dataGridUsers.ItemsSource = users;
        }
        public class User
        {
            public int ID { get; set; }
            public string Name { get; set; }
            public string Email { get; set; }
        }

    }
}