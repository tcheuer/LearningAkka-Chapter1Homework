import akka.actor.ActorSystem
import akka.util.Timeout
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import com.stringActors.StringStore
import akka.testkit.TestActorRef
import com.stringActors.messages.StringMessage

import scala.concurrent.duration

/**
  * Tests if the StringStore actor is receiving and storing messages correctly.
  *
  * @author Tom Heuer
  */
class MessageActorTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()

  describe("stringActor") {
    describe ("given StringMessage") {
      it ("should store the string into its string buffer") {
        val actorRef = TestActorRef(new StringStore)
        actorRef ! StringMessage("Store this string please.")
        actorRef ! StringMessage("Store this second string please.")
        val stringStoreActor = actorRef.underlyingActor

        stringStoreActor.storedString.toString should equal ("Store this second string please.")
      }
    }
  }


}
