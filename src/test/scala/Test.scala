object test extends App {

  val chars = List(214,
    247,35, 210, 170, 190, 205, 202, 199, 207, 235, 202,
    212, 202, 212, 184, 248, 212, 219, 195, 199, 188,
    196, 208, 197, 202, 177, 181, 216, 214, 183, 191,
    201, 210, 212, 208, 180, 181, 195, 182, 224, 188,
    242, 194, 212, 161, 173, 161, 173,343)

  print(new String(chars.map(_.toByte).toArray, "GBK"))
    //.grouped(2).map(l2 => new String(Array(l2(0), l2(1)), "GBK")).foreach(print)
}