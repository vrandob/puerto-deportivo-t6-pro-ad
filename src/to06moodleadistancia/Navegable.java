package to06moodleadistancia;

/**
 *
 * @author vrand
 */
public interface Navegable {
  
  void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes);
  
  void pararNavegacion(int minutos);
}
