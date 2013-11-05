package code.snippet

import net.liftweb.util._
import Helpers._
import net.liftweb.http.{TransientRequestVar, SHtml}
import net.liftweb.http.js.JE._
import net.liftweb.http.js.JsCmds._
import code.model.{Pony, PonyService}
import net.liftweb.http.js.JsCmd

class PonySnippet {

  object pony extends TransientRequestVar[Pony](dflt = PonyService.getRandomPony.openOrThrowException("problem getting pony"))

  def renderButton = "button [onclick]" #> SHtml.ajaxInvoke(() => theBigReveal)

  def theBigReveal: JsCmd = {
    JsRaw(
      """
        |var div = $('#ponyDiv');
        |div.show();
        |div.find('p').text('%s is best pony!');
        |div.find('img').attr('src', '%s');
      """.stripMargin.format(pony.name, pony.imageUrl)
    )
  }
}
