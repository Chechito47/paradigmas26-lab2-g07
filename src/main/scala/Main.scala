// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================

object Main {
  def main(args: Array[String]): Unit = {

    // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------

    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    println(s"\nDiccionario cargado: ${dictionary.size} entidades.")

    // ------------------------------------------------------------------
    // Paso 2: Descargar posts
    // ------------------------------------------------------------------
    val subscriptions: List[String] = FileIO.readSubscriptions()

    val allPosts: List[(String, List[String])] = subscriptions.map { url =>
      val json: String = FileIO.downloadFeed(url)
      val titles: List[String] = FileIO.extractPostTitles(json)
      (url, titles)
    }

    // ------------------------------------------------------------------
    // Paso 3: Detectar entidades y mostrar resultados por post
    // ------------------------------------------------------------------

    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------

    allPosts.map { case (url, titles) => 
      println(s"\nDescargando posts de: $url\n")
      val allEntities: List[NamedEntity] = titles.flatMap { title =>
        val entitiesDetected: List[NamedEntity] = Analyzer.detectEntities(title, dictionary)
        println(Formatters.formatNERResult(title, entitiesDetected))
        entitiesDetected
      }
      val countsEntities: Map[String, Int] = Analyzer.countByType(allEntities)
      println(Formatters.formatEntityStats(countsEntities))
    }
  }
}
