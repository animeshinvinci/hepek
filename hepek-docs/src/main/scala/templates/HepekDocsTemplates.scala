package templates

import scalatags.Text.all._
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.prismjs
import ba.sake.hepek.theme.bootstrap3.{HepekBootstrap3BlogPage, TocType}
import utils._
import Imports._
import resources._

trait HepekDocsAbstractPage
    extends HepekBootstrap3BlogPage
    with HepekDocsStaticPage
    with prismjs.PrismDependencies {
  val hlLangs = List("core", "clike", "scala", "java", "markup")

  override def prismSettings =
    super.prismSettings
      .withTheme(prismjs.Themes.Okaidia)
      .withLanguages(prismjs.PrismConsts.languages filter hlLangs.contains)

  override def tocSettings = super.tocSettings.copy(
    tocType = Some(TocType.Scrollspy(45))
  )

  override def pageHeader = None
}

trait HepekDocsStaticPage extends StaticPage with AnchorjsDependencies {

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(images.ico("favicon").ref)
      .withFaviconInverted(images.ico("favicon-small").ref)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(docs.Index)
      .withMainPages(docs.hepek.components.Index, docs.hepek.Index, docs.hepek.play.Index)

  override def navbar = Some(Navbar)

  // CSS
  override def styleURLs = super.styleURLs ++ List(
    styles.css("main").ref,
    "https://use.fontawesome.com/releases/v5.0.12/css/all.css"
  )

  override def bootstrapDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies()
      .withDeps(Dependency("yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def scriptURLs = super.scriptURLs :+ scripts.js("main").ref

  override def pageContent = {
    import Classes._
    frag(
      super.pageContent,
      footer(txtAlignCenter, bgInfo, cls := "navbar-fixed-bottom")(
        hyperlink("https://github.com/sake92/hepek", btnClass)(
          faBrand("github")
        ),
        hyperlink("https://gitter.im/sake92/hepek", btnClass)(
          faBrand("gitter")
        )
      )
    )
  }
}
