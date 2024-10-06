package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainX {

    @Autowired
    CategoriaRepository repository;

    // Método para registrar una nueva categoría con un nombre proporcionado por el usuario
    public void registro() {
        Scanner cs = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la nueva categoría:");
        String nombreCategoria = cs.nextLine();  // Solicitar el nombre de la nueva categoría
        Categoria ca = new Categoria();
        ca.setNombre(nombreCategoria);  // Asignar el nombre proporcionado por el usuario
        Categoria dd = repository.save(ca);
        System.out.println("Categoría registrada exitosamente:");
        System.out.println("ID: " + dd.getIdCategoria() + " | Nombre: " + dd.getNombre());
    }

    // Método para listar todas las categorías
    public void listar() {
        List<Categoria> datos = repository.findAll();
        System.out.println("ID\tNombre");
        for (Categoria ca : datos) {
            System.out.println(ca.getIdCategoria() + "\t" + ca.getNombre());
        }
    }

    // Método para eliminar una categoría por ID
    public void eliminar() {
        Scanner cs = new Scanner(System.in);
        System.out.println("Ingrese el ID de la categoría a eliminar:");
        long id = Long.parseLong(cs.next());
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isPresent()) {
            repository.deleteById(id);
            System.out.println("Categoría eliminada exitosamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    // Método para actualizar una categoría
    public void actualizar() {
        Scanner cs = new Scanner(System.in);
        System.out.println("Ingrese el ID de la categoría a actualizar:");
        long id = Long.parseLong(cs.next());
        Optional<Categoria> categoria = repository.findById(id);
        if (categoria.isPresent()) {
            Categoria ca = categoria.get();
            System.out.println("Ingrese el nuevo nombre de la categoría:");
            String nuevoNombre = cs.next();
            ca.setNombre(nuevoNombre);
            repository.save(ca);
            System.out.println("Categoría actualizada exitosamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    // Menú interactivo actualizado
    public void menu() {
        int opc = 0;
        Scanner cs = new Scanner(System.in);
        String mensaje = "Seleccione una opción: \n 1=Crear\n2=Listar\n3=Eliminar\n4=Actualizar\n0=Salir";
        do {
            System.out.println(mensaje);
            opc = Integer.parseInt(cs.next());
            switch (opc) {
                case 1: {
                    registro();
                    break;
                }
                case 2: {
                    listar();
                    break;
                }
                case 3: {
                    eliminar();
                    break;
                }
                case 4: {
                    actualizar();
                    break;
                }
                case 0: {
                    System.out.println("Saliendo...");
                    break;
                }
                default: {
                    System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } while (opc != 0);
    }
}
