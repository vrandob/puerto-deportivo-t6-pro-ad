package to06moodleadistancia;

/**
 * @author vrand Clase que representa una embarcación de tipo lancha que se
 * encarga de gestionar las diferentes operaciones de navegación de las lanchas
 * del puerto.
 */
public final class Lancha extends Embarcacion { //No debe ser heredable pero sí instanciable

  private final int numMotores;

  private int litrosCombustible;

  // Atributos estáticos
  public static int lanchasCreadas;

  //Constantes públicas
  public static final int MIN_MOTORES = 1;
  public static final int MAX_MOTORES = 2;
  public static final int MIN_COMBUSTIBLE = 8;
  public static final int MAX_COMBUSTIBLE = 50;
  public static final double FACTOR_COMBUSTIBLE = 0.026;
  public static final int MIN_VELOCIDAD_LANCHA = 1;
  public static final int MAX_VELOCIDAD_LANCHA = 50;

  //Constructores
  public Lancha(String nombre, int motores, int combustible, int tripulantes) throws IllegalArgumentException {
    super(nombre, tripulantes); // Llama al constructor de la superclase Embarcacion
    //Validaciones
    if (motores < Lancha.MIN_MOTORES || motores > Lancha.MAX_MOTORES) {
      throw new IllegalArgumentException(String.format("El número de motores debe estar entre %d y %d",
        Lancha.MIN_MOTORES, Lancha.MAX_MOTORES));
    } else if (combustible < Lancha.MIN_COMBUSTIBLE || combustible > Lancha.MAX_COMBUSTIBLE) {
      throw new IllegalArgumentException(String.format("El nivel de combustible debe estar entre %d y %d",
        Lancha.MIN_COMBUSTIBLE, Lancha.MAX_COMBUSTIBLE));
    }
    // Asignación de atributos
    this.numMotores = motores;
    this.litrosCombustible = combustible;
    // Actualización de atributos estáticos
    Lancha.lanchasCreadas++;
    Lancha.numBarcosCreados++;

  }

  // Desde constructor con menos parámetros deberá invocarse a los constructores con más parámetros 
  // utilizando los valores por omisión para completar la inicialización.
  public Lancha() {
    this(String.format("Lancha %d", Lancha.lanchasCreadas + 1),
      Lancha.MIN_MOTORES,
      Lancha.MAX_COMBUSTIBLE,
      Embarcacion.MIN_TRIPULANTES);

    // Actualización de atributos estáticos... again??
    //Lancha.lanchasCreadas++;
    //Lancha.numBarcosCreados++;
  }

  //Getters
  public int getNumMotores() {
    return this.numMotores;
  }

  public int getCantidadCombustible() {
    return this.litrosCombustible;
  }

  public static int getNumLanchas() {
    return Lancha.lanchasCreadas;
  }

  @Override
  public void setRumbo(String rumbo) throws NullPointerException {
    super.setRumbo(rumbo);        //Añadimos las excepciones propias de la clase padre y además:
    if (rumbo == null) {
      throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
    } else if (!rumbo.equalsIgnoreCase("norte")
      || !rumbo.equalsIgnoreCase("sur")
      || !rumbo.equalsIgnoreCase("este")
      || !rumbo.equalsIgnoreCase("oeste")) {
      throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
    }
  }

  //Sobreescritura métodos de interface Navegable, aprovechar los que ya se implementan en la clase padre Embarcación
  @Override
  public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) {
    super.iniciarNavegacion(velocidad, rumbo, patron, tripulantes); //Reaprovecho código
    //Ahora las validaciones específicas de la subclase
    if (this.litrosCombustible < Lancha.MIN_COMBUSTIBLE || this.litrosCombustible > Lancha.MAX_COMBUSTIBLE) {
      throw new IllegalStateException(String.format("La lancha %s no tiene un nivel de combustible válido para iniciar la navegación (solo %d litros).",
        this.getNombreBarco(),
        this.litrosCombustible));
    } else if (velocidad < Lancha.MIN_VELOCIDAD_LANCHA || this.velocidad > Lancha.MAX_VELOCIDAD_LANCHA) {
      throw new IllegalArgumentException(String.format("La velocidad de navegación de %d nudos asignada a %s es incorrecta.",
        velocidad, this.getNombreBarco()));
    }
    this.navegando = true;
    this.velocidad = velocidad;
    Lancha.numBarcosNavegando++;
  }

  @Override
  public void pararNavegacion(int minutosNavegando) {
    super.pararNavegación(minutosNavegando);
    this.litrosCombustible = (int) (velocidad * minutosNavegando * Lancha.FACTOR_COMBUSTIBLE);
    if (litrosCombustible < 0) {
      throw new IllegalStateException("La cantidad de combustible no puede ser menor que cero.");
    }
    this.navegando=false;
    this.velocidad=0;
    this.tiempoTotalNavegacionBarco+=minutosNavegando;
  }

  @Override
  protected void señalizar() {
    System.out.println(String.format("AVISO de señalización de la lancha %s con bocinas y luces intermitentes.",
      this.getNombreBarco()));  //Ligadura dinámica
  }

  @Override
  public String toString() {                
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString());  //Reaprovecha toString de Embarcación
    sb.append(String.format(", Numero de motores: %d, Nivel de combustible: %d", 
      this.numMotores, this.litrosCombustible));
      
    return sb.toString();
  }
  

} //class
