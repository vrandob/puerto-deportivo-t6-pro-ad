package to06moodleadistancia;

/*
 * @author vrand
 */
public abstract class Embarcacion implements Navegable { //Al ser abstracta no pernite instancias de ella pero si que otras hereden

  //Atributos estáticos variables de clase
  public static int numBarcosCreados;
  public static int numBarcosNavegando;
  public static float tiempoTotalFlota;

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

  //Getters
  public String getNombreBarco() {
    return this.nombreBarco;
  }

  public int getMaxTripulantes() {
    return this.MAX_TRIPULANTES;
  }

  public boolean isNavegando() {
    return this.navegando;
  }

  public int getVelocidad() {
    return this.velocidad;
  }

  public String getRumbo() {
    return this.rumbo;
  }

  public String getPatron() {
    return this.patron;
  }

  public int getTripulacion() {
    return this.MAX_TRIPULANTES;
  }

  public int getTiempoTotalNavegacionBarco() {
    return this.tiempoTotalNavegacionBarco;
  }

  //Métodos que devuelven información genérica o global sobre la clase (estáticos)
  public int getNumBarcos() {
    return Embarcacion.numBarcosCreados;
  }

  public int getNumBarcosNavegando() {
    return Embarcacion.numBarcosNavegando;
  }

  public float getTiempoTotalNavegacion() {
    return Embarcacion.tiempoTotalFlota;
  }
  
  //Métodos para modificar atributos de la clase
  public void setRumbo(String rumbo) throws IllegalStateException {
    if(!this.navegando) {
      throw new IllegalStateException(String.format("La embarcación %s no está navegando, no se puede cambiar el rumbo.", 
        this.nombreBarco));
    } else if(rumbo.equalsIgnoreCase(this.rumbo)) {
        throw new IllegalStateException(String.format("La embarcación %s ya está navegando con ese rumbo //(%s//), debes indicar un rumbo distinto para poder modificarlo.", 
        this.nombreBarco, this.rumbo));
    }
  }
}
