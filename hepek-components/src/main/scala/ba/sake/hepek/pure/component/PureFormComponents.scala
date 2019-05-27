package ba.sake.hepek.pure.component

import scalatags.Text.all._
import ba.sake.hepek.html.component.FormComponents
import ba.sake.hepek.pure.component.classes.PureButtonClasses

object PureFormComponents extends PureFormComponents {
  sealed trait Type extends FormComponents.Type

  object Type {
    case object Vertical extends Type {
      override def classes = List("pure-form", "pure-form-stacked")
    }
    case object Inline extends Type {
      override def classes = List("pure-form")
    }
    case object Horizontal extends Type {
      override def classes = List("pure-form", "pure-form-aligned")
    }
  }
}

trait PureFormComponents extends FormComponents {
  import PureFormComponents._
  import PureButtonClasses._

  // TODO display validation !!

  override def formType: FormComponents.Type = Type.Vertical

  override def constructInputNormal(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputHelp: Option[String],
      inputValidationState: Option[ValidationState],
      inputMessages: Seq[String],
      inputAttrs: Seq[AttrPair]
  ) = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-control-group")(
          label(inputId.map(`for` := _), cls := "control-label")(inputLabel),
          input(commonAttrs)
        )
      case _ =>
        frag(
          label(inputId.map(`for` := _))(inputLabel),
          input(commonAttrs)
        )
    }
  }

  override def constructInputButton(
      inputType: String,
      inputId: Option[String],
      inputValue: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs
    val btnField =
      if (inputType == "button") button(btnClass, commonAttrs)(inputValue)
      else input(btnClass, commonAttrs)

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-controls")(
          btnField
        )
      case _ =>
        btnField
    }
  }

  override def constructInputCheckbox(
      inputType: String,
      inputName: String,
      inputLabel: Option[String],
      inputId: Option[String],
      inputValue: Option[String],
      inputHelp: Option[String],
      inputAttrs: Seq[AttrPair]
  ): Frag = {
    val commonAttrs = Seq(tpe := inputType, name := inputName) ++
      inputId.map(id := _) ++ inputValue.map(value := _) ++ inputAttrs

    formType match {
      case Type.Horizontal =>
        div(cls := "pure-controls")(
          label(cls := "pure-checkbox", inputId.map(`for` := _))(
            input(commonAttrs),
            inputLabel
          )
        )
      case _ =>
        label(cls := "pure-checkbox", inputId.map(`for` := _))(
          input(commonAttrs),
          inputLabel
        )
    }
  }

}