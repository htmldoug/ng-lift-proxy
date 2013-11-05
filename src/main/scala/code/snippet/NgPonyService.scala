package code.snippet

import Angular._
import code.model.PonyService

class NgPonyService {
  def render = renderIfNotAlreadyDefined(
    angular.module("lift.pony")
      .factory("ponyService", jsObjFactory()
      .jsonCall("getBestPony", PonyService.getRandomPony)
    )
  )
}
