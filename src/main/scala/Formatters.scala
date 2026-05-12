// =====================================================================
// Ejercicios 4 y 5: Formateo de resultados
// =====================================================================

/**
 * Responsable de convertir los resultados del análisis a texto para mostrar.
 */
object Formatters {

  /**
   * Formatea el análisis NER de un post individual.
   *
   * @param postTitle título del post analizado
   * @param entities  entidades detectadas en ese post
   * @return bloque de texto con el título y las entidades encontradas
   *
   * TODO (Ejercicio 4): Implementar este método.
   *
   *   Usar el método describe de cada entidad para generar la salida.
   *   No es necesario hacer match sobre el tipo concreto de cada entidad:
   *   describe ya funciona correctamente para cualquier subtipo (polimorfismo).
   *
   *   Ejemplo de salida esperada:
   *
   *     Post: "Scala 3 released at EPFL by Martin Odersky"
   *     Entidades detectadas:
   *       [ProgrammingLanguage] Scala
   *       [University] EPFL
   *       [Person] Martin Odersky
   *
   *   Si no se detectaron entidades, mostrar un mensaje indicándolo.
   */
  def formatNERResult(postTitle: String, entities: List[NamedEntity]): String = {
    val title = s"Post: \"$postTitle\""

    if (entities.isEmpty) {
      s"$title\n(sin entidades detectadas)\n"
    } else {
      val listEntities = entities.map(e => s"  ${e.describe}").mkString("\n")
      s"$title\nEntidades detectadas:\n$listEntities\n"
    }
  }

  /**
   * Formatea un resumen de estadísticas de entidades por tipo.
   *
   * @param counts mapa de entityType → cantidad
   * @return texto con las estadísticas ordenadas por cantidad (de mayor a menor)
   */
  def formatEntityStats(counts: Map[String, Int]): String = {
    val lines = counts.toList
      .sortBy { case (_, total) => -total }
      .map { case (eType, total) => s"$eType: $total" }
      .mkString("\n")
    s"=== Estadísticas de entidades ===\n$lines\n"
  }
}
