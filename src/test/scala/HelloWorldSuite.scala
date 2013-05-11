import org.scalatest.FunSuite

class HelloWorldSuite extends FunSuite {
    val shared = List("a","b","c")
    test("that tail yields 'b' and 'c'") {
        assert(shared.tail === List("b","c"))
    }
}