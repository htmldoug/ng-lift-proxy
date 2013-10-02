package code

import net.liftweb.common.Box
import net.liftweb.http.Req
import net.liftweb.json.{DefaultFormats, JsonParser}
import java.lang.String

package object rest {

  def parse[T](request: Req)(implicit mf: scala.reflect.Manifest[T]): Box[T] = {
    implicit val formats = DefaultFormats
    for {
      bodyBytes <- request.body
      bodyJson <- JsonParser.parseOpt(new String(bodyBytes))
      extracted <- bodyJson.extractOpt[T]
    } yield extracted
  }
}
