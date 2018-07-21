package ba.sake.hepek.theme.bootstrap3

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section

object HepekBootstrap3SectionUtils extends HepekBootstrap3SectionUtils

trait HepekBootstrap3SectionUtils {

  /** Renders the sections. */
  def renderSections(secs: List[Section], depth: Int): List[Frag] =
    secs.flatMap { s =>
      val hTag = tag("h" + (depth + 1)) // depth = h2, h3...
      val thisSection =
        tag("section")(hTag(id := s.id)(s.name), s.content)
      thisSection :: renderSections(s.children, depth + 1)
    }

  /** Renders the TOC (Table of Contents). */
  def renderTOC(secs: List[Section], depth: Int): List[Frag] = {
    val lis = secs.flatMap { s =>
      val aLink = a(href := s"#${s.id}")(s.name)
      li(aLink) :: renderTOC(s.children, depth + 1)
    }
    List(ul(lis))
  }
}
