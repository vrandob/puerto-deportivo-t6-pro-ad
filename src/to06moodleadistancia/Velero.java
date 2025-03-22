package to06moodleadistancia;

/*
 * @author vrand
 */
public final class Velero extends Embarcacion implements Regateable {

  // ------------------------------------------------------------------------
  // Atributos de la clase
  // ------------------------------------------------------------------------
  public static final int MAX_MASTILES = 4;
  public static final int MAX_VELOCIDAD_VELERO = 30;
  public static final int MIN_MASTILES = 1;
  public static final int MIN_VELOCIDAD_VELERO = 2;

  private static int numVeleros;
  private int numMastiles;

  // ------------------------------------------------------------------------
  // Constructores de la clase
  // ------------------------------------------------------------------------
  public Velero(String nombre, int mastiles, int tripulantes) throws IllegalArgumentException {
    super(nombre, tripulantes);
    if (mastiles < Velero.MIN_MASTILES || mastiles > Velero.MAX_MASTILES) {
      throw new IllegalArgumentException(String.format("El número de maástiles debe estar entre %d y %d",
        Velero.MIN_MASTILES,
        Velero.MAX_MASTILES));
    }
    this.numMastiles = mastiles;
    Velero.numVeleros++;
    this.navegando = false;
  }

  public Velero() {
    this(String.format("Velero %d", Velero.numVeleros + 1),
      Velero.MIN_MASTILES,
      Embarcacion.MIN_TRIPULANTES);
    Velero.numVeleros++;
    Velero.numBarcosCreados++;
  }
  // ------------------------------------------------------------------------
  // Getters (consultan el estado del objeto)
  // ------------------------------------------------------------------------

  public int getNumMastiles() {
    return this.numMastiles;
  }

  // ------------------------------------------------------------------------
  // Setters (modifican el estado del objeto)
  // ------------------------------------------------------------------------
  public void setNumMastiles(int numMastiles) {
    this.numMastiles = numMastiles;
  }

  // ------------------------------------------------------------------------
  // Métodos estáticos (acceden a los atributos estáticos de la clase)
  // ------------------------------------------------------------------------
  public static int getNumVeleros() {
    return Velero.numVeleros;
  }

  // ------------------------------------------------------------------------
  // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
  // ------------------------------------------------------------------------
  //Sobreescritura métodos de interface Navegable, aprovechar los que ya se implementan en la clase padre Embarcación
  @Override
  public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) {
    super.iniciarNavegacion(velocidad, rumbo, patron, tripulantes); //Reaprovecho código
    //Ahora las validaciones específicas de la subclase
    if (velocidad < Velero.MIN_VELOCIDAD_VELERO || velocidad > Velero.MAX_VELOCIDAD_VELERO) {
      throw new IllegalArgumentException(String.format("La velocidad de navegación de %d nudos es incorrecta.",
        velocidad));
    }
    this.navegando = true;
    this.velocidad = velocidad;
    Velero.numBarcosNavegando++;
  }

  //En el enunciado de esta clase no dice nada, pero se debe implementar el método pararNavegacion( de la interface Navegable
  @Override
  public void pararNavegacion(int minutosNavegando) {
    super.pararNavegación(minutosNavegando);
    this.navegando = false;
    this.velocidad = 0;
    this.tiempoTotalNavegacionBarco += minutosNavegando;
  }

  @Override
  public String iniciarRegata(Velero otroBarco) throws NullPointerException, IllegalStateException {
    if (otroBarco == null) {
      throw new NullPointerException("El barco con el que se intenta regatear no existe");
    }
    if (!this.navegando) {
      throw new IllegalStateException(String.format("No se puede iniciar la regata, el barco %s no está navegando",
        this.getNombreBarco()));
    }
    if (!otroBarco.navegando) {
      throw new IllegalStateException(String.format("No se puede iniciar la regata, el barco %s no está navegando",
        otroBarco.getNombreBarco()));
    }
    if (this.rumbo.equals(otroBarco.rumbo)) {      //de tipo String --> usar equals
      throw new IllegalStateException(String.format("No se puede iniciar la regata, los barcos %s y %s deben navegar con el mismo rumbo.",
        this.getNombreBarco(), otroBarco.getNombreBarco()));
    }
    if (this.getNumMastiles() != otroBarco.getNumMastiles()) {
      throw new IllegalStateException(String.format("No se puede iniciar la regata, los barcos %s y %s no tienen el mismo numero de mástiles.",
        this.getNombreBarco(), otroBarco.getNombreBarco()));
    }
    //Si se valida todo se inicia la regata:
    String mensaje;
    if (this.getVelocidad() > otroBarco.getVelocidad()) {
      mensaje = String.format("El barco %s ha llegado antes a la línea de llegada", this.getNombreBarco());
    } else if (this.getVelocidad() < otroBarco.getVelocidad()) {
      mensaje = String.format("El barco %s ha llegado antes a la línea de llegada", otroBarco.getNombreBarco());
    } else {
      mensaje = String.format("Los barcos %s y %s han llegado a la vez a la línea de llegada", this.getNombreBarco(), otroBarco.getNombreBarco());
    }
    return mensaje;
  }

  // ------------------------------------------------------------------------
  // Método Abstracto sobreecrito
  // ------------------------------------------------------------------------
  protected void señalizar() {
    System.out.println(String.format("AVISO del velero %s con banderas de señalización marítima.",
      this.getNombreBarco()));  //Ligadura dinámica
  }

  // ------------------------------------------------------------------------
  // Método toString (imprime el estado del objeto)
  // ------------------------------------------------------------------------
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());
    sb.append(String.format(", Número de mástiles: %d", this.getNumMastiles()));
    return sb.toString();
  }

} //class
