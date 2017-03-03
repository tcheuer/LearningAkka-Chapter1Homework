package com.stringActors

import akka.actor.Actor
import akka.event.Logging
import com.stringActors.messages.StringMessage

/** The StringStore actor will store the most recent string passed to it
  *
  * @author Tom Heuer
  */
class StringStore extends Actor {
  /**
    * String buffer which stores the most recent string passed to the actor
    *
    * Must be a buffer, as normal scala strings are immutable
    */
  val storedString = new StringBuffer
  val log = Logging(context.system, this)
  override def receive = {
    case StringMessage (passedString) => {
      log.info("Received StringMessage - value: {}", passedString)
      //Clears previous string, then append passed string to empty stringbuffer
      storedString.delete(0,storedString.length())
      storedString.append(passedString)
    }

    case o => {
      log.info("received unknown message: {}", o)
    }


  }
}
