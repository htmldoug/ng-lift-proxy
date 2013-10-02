package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http._
import code.model.PonyService
import net.liftweb.json.Extraction

class PonyRest extends RestHelper {
  serve {
    case Req("rest" :: "pony" :: "best" :: Nil, _, GetRequest) =>
      PonyService.getRandomPony.map(pony => Extraction.decompose(pony))
  }
}
