
    import java.awt.*;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.*;
    
    public class TiendaGUI extends JFrame {
        private Tienda tienda;
    
        private JTextArea outputArea;
        private JButton btnMostrarProductos;
        private JButton btnRegistrarProducto;
        private JButton btnMostrarClientes;
        private JButton btnRegistrarCliente;
    
        public TiendaGUI() {
            tienda = new Tienda();
    
            setTitle("Sistema de Gestión de Tienda");
            setSize(600, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            Color colorFondo = new Color(178, 216, 250);
            getContentPane().setBackground(colorFondo);
            JPanel btnPanel = new JPanel();
            btnMostrarProductos = new JButton("Mostrar Productos");
            btnRegistrarProducto = new JButton("Registrar Producto");
            btnMostrarClientes = new JButton("Mostrar Clientes");
            btnRegistrarCliente = new JButton("Registrar Cliente");
    
            btnMostrarProductos.addActionListener(e -> mostrarProductos());
            btnRegistrarProducto.addActionListener(e -> registrarProducto());
            btnMostrarClientes.addActionListener(e -> mostrarClientes());
            btnRegistrarCliente.addActionListener(e -> registrarCliente());
    
            btnPanel.add(btnMostrarProductos);
            btnPanel.add(btnRegistrarProducto);
            btnPanel.add(btnMostrarClientes);
            btnPanel.add(btnRegistrarCliente);
    
            outputArea = new JTextArea(20, 50);
            outputArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(outputArea);
    
            add(btnPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);
    
            setVisible(true);
        }
    
        private void mostrarProductos() {
            outputArea.setText("");
            outputArea.append("PRODUCTOS:\n");
            for (Producto producto : tienda.getProductos()) {
                outputArea.append(producto.mostrarInfo() + "\n");
            }
        }
    
        private void registrarProducto() {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto:"));
            String categoria = JOptionPane.showInputDialog("Ingrese la categoría del producto:");
    
            Categoria categoriaObj = new Categoria(categoria);
            Producto producto = new Producto(nombre, precio, categoriaObj);
            tienda.registrarProducto(producto);
    
            mostrarProductos();
        }
    
        private void mostrarClientes() {
            outputArea.setText("");
            outputArea.append("CLIENTES:\n");
            for (Cliente cliente : tienda.getClientes()) {
                outputArea.append(cliente.mostrarInfo() + "\n");
            }
        }
    
        private void registrarCliente() {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente:");
            int idCliente = tienda.getClientes().size() + 1;
    
            Cliente cliente = new Cliente(nombre, apellido, idCliente);
            tienda.registrarCliente(cliente);
    
            mostrarClientes();
        }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new TiendaGUI());
        }
    }
    
    class Tienda {
        private List<Producto> productos;
        private List<Cliente> clientes;
        public Tienda() {
            this.productos = new ArrayList<>();
            this.clientes = new ArrayList<>();
            new ArrayList<>();
            new ArrayList<>();
        }
    
        public void registrarProducto(Producto producto) {
            productos.add(producto);
        }
    
        public void registrarCliente(Cliente cliente) {
            clientes.add(cliente);
        }
    
        public List<Producto> getProductos() {
            return productos;
        }
    
        public List<Cliente> getClientes() {
            return clientes;
        }
    }
    
    class Producto {
        private String nombre;
        private double precio;
        private Categoria categoria;
    
        public Producto(String nombre, double precio, Categoria categoria) {
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
        }
    
        public String mostrarInfo() {
            return "Nombre: " + nombre + ", Precio: $" + precio + ", Categoría: " + categoria.getNombre();
        }
    }
    
    class Cliente {
        private String nombre;
        private String apellido;
        private int idCliente;
    
        public Cliente(String nombre, String apellido, int idCliente) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.idCliente = idCliente;
        }
    
        public String mostrarInfo() {
            return "ID: " + idCliente + ", Nombre: " + nombre + " " + apellido;
        }
    }
    
    class Categoria {
        private String nombre;
    
        public Categoria(String nombre) {
            this.nombre = nombre;
        }
    
        public String getNombre() {
            return nombre;
        }
    }
    