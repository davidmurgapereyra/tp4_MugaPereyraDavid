package ar.edu.unju.fi.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.fi.model.Materia;

public class ListadoMaterias {
    
    public static List<Materia> materias = new ArrayList<Materia>();

    // Método para listar materias
    public static List<Materia> listarMaterias() {
        // Buscar solo las que tengan estado en true
        List<Materia> materiasActivas = new ArrayList<>();
        for (Materia m : materias) {
            if (m.getEstado()) {
                materiasActivas.add(m);
            }
        }
        return materiasActivas;
    }

    // Método para buscar una materia por ID
    public static Materia buscarMateriaPorCodigo(String codigo) {
        for (Materia m : materias) {
            if (m.getCodigo().equals(codigo)) {
                return m;
            }
        }
        return null;
    }

    // Método para agregar una materia
    public static void agregarMateria(Materia m) {
        m.setEstado(true);
        materias.add(m);
    }

    // Método para modificar una materia
    public static void modificarMateria(Materia materiaModificada) {
        materiaModificada.setEstado(true);
        for (int i = 0; i < materias.size(); i++) {
            Materia materia = materias.get(i);
            if (materia.getCodigo().equals(materiaModificada.getCodigo())) {
                materias.set(i, materiaModificada);
                break;
            }
        }
    }

    // Método para eliminar una materia
    public static void eliminarMateria(String codigo) {
        for (int i = 0; i < materias.size(); i++) {
            Materia materia = materias.get(i);
            if (materia.getCodigo().equals(codigo)) {
                materia.setEstado(false);
                break;
            }
        }
    }
}
