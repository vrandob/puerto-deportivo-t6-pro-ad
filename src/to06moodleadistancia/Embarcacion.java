package to06moodleadistancia;

/*
 * @author vrand
 */
public abstract class Embarcacion implements Navegable { //Al ser abstracta no pernite instancias de ella pero si que otras hereden

  //Atributos estáticos variables de clase
  public static int numBarcosCreados;
  public static int numBarcosNavegando;
  public static int tiempoTotalFlota;

  //Atributos estáticos inmutables de clase (nombres y valores ya proporcionados explicitamente pq ya se hará uso de ellos)
  public static final String PATRON_POR_DEFECTO = "Sin patrón";
  public static final String RUMBO_POR_DEFECTO = "Sin rumbo";
  public static final int MIN_TRIPULANTES = 0;

  //Atributos constantes de clase
  private final String nombreBarco;   //Se define al instanciarse, es inmutable y sólo accesible desde la clase Embarcación
  private final int MAX_TRIPULANTES;

  //Atributos mutables de objeto
  protected boolean navegando;
  protected String patron;
  protected String rumbo;
  protected int tiempoTotalNavegacionBarco;
  protected int velocidad;

  public Embarcacion(String nombre, int tripulantes) throws NullPointerException, IllegalArgumentException {
    if (nombre == null) {
      throw new NullPointerException("El nombre de la embarcación no puede ser nulo.");
    } else if (nombre.equals("")) {
      throw new IllegalArgumentException("El nombre de la embarcación no puede estar vacío.");
    } else if (tripulantes < Embarcacion.MIN_TRIPULANTES) {
      throw new IllegalArgumentException(String.format("El número de tripulantes debe ser, como mínimo,", Embarcacion.MIN_TRIPULANTES));
    }
    this.nombreBarco = nombre;
    this.MAX_TRIPULANTES = tripulantes;
    Embarcacion.numBarcosCreados++;
    this.navegando = false;
    this.velocidad = 0;
    this.patron = Embarcacion.PATRON_POR_DEFECTO;
    this.rumbo = Embarcacion.RUMBO_POR_DEFECTO;
    this.tiempoTotalNavegacionBarco = 0;

  }

}
