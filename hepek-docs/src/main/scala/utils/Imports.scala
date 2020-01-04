package utils

import ba.sake.hepek.Resources
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticBundle
import ba.sake.hepek.html.component.BasicComponents
import ba.sake.hepek.prismjs.PrismCodeHighlightComponents
import scalatags.Text.all._

object Imports extends BootstrapStaticBundle {

  object resources extends Resources {
    override def siteRootPath = "docs"
  }

  object chl extends PrismCodeHighlightComponents

  // FontAwesome 5 brand
  def faBrand(name: String) = i(cls := s"fab fa-$name")

  // class field/method description
  case class ClassProperty(
      name: String,
      tpe: String,
      desc: String = "",
      defaultValue: Option[String] = None
  )

  import Classes._

  def renderClassProps(props: List[ClassProperty]) =
    div(tableResponsive)(
      table(tableClass, tableHoverable)(
        tr(th("Name"), th("Mandatory"), th("Type"), th("Default value"), th("Description")),
        props.map {
          case ClassProperty(name, tpe, desc, defaultValue) =>
            tr(
              td(name),
              td(if (defaultValue.isDefined) "No" else "Yes"),
              td(tpe),
              td(defaultValue),
              td(desc)
            )
        }
      )
    )
}
