import scala.collection.immutable.ArraySeq

  private val winStatesPerMove: ArraySeq[ArraySeq[ArraySeq[(Int, Int)]]] = ArraySeq(
    ArraySeq(ArraySeq((0, 0), (0, 1), (0, 2), (0, 3)), ArraySeq((0, 0), (1, 0), (2, 0), (3, 0)), ArraySeq((0, 0), (1, 1), (2, 2), (3, 3))),
    ArraySeq(ArraySeq((0, 0), (0, 1), (0, 2), (0, 3)), ArraySeq((0, 1), (0, 2), (0, 3), (0, 4)), ArraySeq((0, 1), (1, 1), (2, 1), (3, 1)), ArraySeq((0, 1), (1, 2), (2, 3), (3, 4))),
    ArraySeq(ArraySeq((0, 0), (0, 1), (0, 2), (0, 3)), ArraySeq((0, 1), (0, 2), (0, 3), (0, 4)), ArraySeq((0, 2), (1, 2), (2, 2), (3, 2))),
    ArraySeq(ArraySeq((0, 0), (0, 1), (0, 2), (0, 3)), ArraySeq((0, 1), (0, 2), (0, 3), (0, 4)), ArraySeq((0, 3), (1, 3), (2, 3), (3, 3)), ArraySeq((0, 3), (1, 2), (2, 1), (3, 0))),
    ArraySeq(ArraySeq((0, 1), (0, 2), (0, 3), (0, 4)), ArraySeq((0, 4), (1, 4), (2, 4), (3, 4)), ArraySeq((0, 4), (1, 3), (2, 2), (3, 1))),
    ArraySeq(ArraySeq((1, 0), (1, 1), (1, 2), (1, 3)), ArraySeq((0, 0), (1, 0), (2, 0), (3, 0)), ArraySeq((1, 0), (2, 0), (3, 0), (4, 0)), ArraySeq((1, 0), (2, 1), (3, 2), (4, 3))),
    ArraySeq(ArraySeq((1, 0), (1, 1), (1, 2), (1, 3)), ArraySeq((1, 1), (1, 2), (1, 3), (1, 4)), ArraySeq((0, 1), (1, 1), (2, 1), (3, 1)), ArraySeq((1, 1), (2, 1), (3, 1), (4, 1)), ArraySeq((0, 0), (1, 1), (2, 2), (3, 3)), ArraySeq((1, 1), (2, 2), (3, 3), (4, 4))),
    ArraySeq(ArraySeq((1, 0), (1, 1), (1, 2), (1, 3)), ArraySeq((1, 1), (1, 2), (1, 3), (1, 4)), ArraySeq((0, 2), (1, 2), (2, 2), (3, 2)), ArraySeq((1, 2), (2, 2), (3, 2), (4, 2)), ArraySeq((0, 1), (1, 2), (2, 3), (3, 4)), ArraySeq((0, 3), (1, 2), (2, 1), (3, 0))),
    ArraySeq(ArraySeq((1, 0), (1, 1), (1, 2), (1, 3)), ArraySeq((1, 1), (1, 2), (1, 3), (1, 4)), ArraySeq((0, 3), (1, 3), (2, 3), (3, 3)), ArraySeq((1, 3), (2, 3), (3, 3), (4, 3)), ArraySeq((0, 4), (1, 3), (2, 2), (3, 1)), ArraySeq((1, 3), (2, 2), (3, 1), (4, 0))),
    ArraySeq(ArraySeq((1, 1), (1, 2), (1, 3), (1, 4)), ArraySeq((0, 4), (1, 4), (2, 4), (3, 4)), ArraySeq((1, 4), (2, 4), (3, 4), (4, 4)), ArraySeq((1, 4), (2, 3), (3, 2), (4, 1))),
    ArraySeq(ArraySeq((2, 0), (2, 1), (2, 2), (2, 3)), ArraySeq((0, 0), (1, 0), (2, 0), (3, 0)), ArraySeq((1, 0), (2, 0), (3, 0), (4, 0))),
    ArraySeq(ArraySeq((2, 0), (2, 1), (2, 2), (2, 3)), ArraySeq((2, 1), (2, 2), (2, 3), (2, 4)), ArraySeq((0, 1), (1, 1), (2, 1), (3, 1)), ArraySeq((1, 1), (2, 1), (3, 1), (4, 1)), ArraySeq((1, 0), (2, 1), (3, 2), (4, 3)), ArraySeq((0, 3), (1, 2), (2, 1), (3, 0))),
    ArraySeq(ArraySeq((2, 0), (2, 1), (2, 2), (2, 3)), ArraySeq((2, 1), (2, 2), (2, 3), (2, 4)), ArraySeq((0, 2), (1, 2), (2, 2), (3, 2)), ArraySeq((1, 2), (2, 2), (3, 2), (4, 2)), ArraySeq((0, 0), (1, 1), (2, 2), (3, 3)), ArraySeq((1, 1), (2, 2), (3, 3), (4, 4)), ArraySeq((0, 4), (1, 3), (2, 2), (3, 1)), ArraySeq((1, 3), (2, 2), (3, 1), (4, 0))),
    ArraySeq(ArraySeq((2, 0), (2, 1), (2, 2), (2, 3)), ArraySeq((2, 1), (2, 2), (2, 3), (2, 4)), ArraySeq((0, 3), (1, 3), (2, 3), (3, 3)), ArraySeq((1, 3), (2, 3), (3, 3), (4, 3)), ArraySeq((0, 1), (1, 2), (2, 3), (3, 4)), ArraySeq((1, 4), (2, 3), (3, 2), (4, 1))),
    ArraySeq(ArraySeq((2, 1), (2, 2), (2, 3), (2, 4)), ArraySeq((0, 4), (1, 4), (2, 4), (3, 4)), ArraySeq((1, 4), (2, 4), (3, 4), (4, 4))),
    ArraySeq(ArraySeq((3, 0), (3, 1), (3, 2), (3, 3)), ArraySeq((0, 0), (1, 0), (2, 0), (3, 0)), ArraySeq((1, 0), (2, 0), (3, 0), (4, 0)), ArraySeq((0, 3), (1, 2), (2, 1), (3, 0))),
    ArraySeq(ArraySeq((3, 0), (3, 1), (3, 2), (3, 3)), ArraySeq((3, 1), (3, 2), (3, 3), (3, 4)), ArraySeq((0, 1), (1, 1), (2, 1), (3, 1)), ArraySeq((1, 1), (2, 1), (3, 1), (4, 1)), ArraySeq((0, 4), (1, 3), (2, 2), (3, 1)), ArraySeq((1, 3), (2, 2), (3, 1), (4, 0))),
    ArraySeq(ArraySeq((3, 0), (3, 1), (3, 2), (3, 3)), ArraySeq((3, 1), (3, 2), (3, 3), (3, 4)), ArraySeq((0, 2), (1, 2), (2, 2), (3, 2)), ArraySeq((1, 2), (2, 2), (3, 2), (4, 2)), ArraySeq((1, 0), (2, 1), (3, 2), (4, 3)), ArraySeq((1, 4), (2, 3), (3, 2), (4, 1))),
    ArraySeq(ArraySeq((3, 0), (3, 1), (3, 2), (3, 3)), ArraySeq((3, 1), (3, 2), (3, 3), (3, 4)), ArraySeq((0, 3), (1, 3), (2, 3), (3, 3)), ArraySeq((1, 3), (2, 3), (3, 3), (4, 3)), ArraySeq((0, 0), (1, 1), (2, 2), (3, 3)), ArraySeq((1, 1), (2, 2), (3, 3), (4, 4))),
    ArraySeq(ArraySeq((3, 1), (3, 2), (3, 3), (3, 4)), ArraySeq((0, 4), (1, 4), (2, 4), (3, 4)), ArraySeq((1, 4), (2, 4), (3, 4), (4, 4)), ArraySeq((0, 1), (1, 2), (2, 3), (3, 4))),
    ArraySeq(ArraySeq((4, 0), (4, 1), (4, 2), (4, 3)), ArraySeq((1, 0), (2, 0), (3, 0), (4, 0)), ArraySeq((1, 3), (2, 2), (3, 1), (4, 0))),
    ArraySeq(ArraySeq((4, 0), (4, 1), (4, 2), (4, 3)), ArraySeq((4, 1), (4, 2), (4, 3), (4, 4)), ArraySeq((1, 1), (2, 1), (3, 1), (4, 1)), ArraySeq((1, 4), (2, 3), (3, 2), (4, 1))),
    ArraySeq(ArraySeq((4, 0), (4, 1), (4, 2), (4, 3)), ArraySeq((4, 1), (4, 2), (4, 3), (4, 4)), ArraySeq((1, 2), (2, 2), (3, 2), (4, 2))),
    ArraySeq(ArraySeq((4, 0), (4, 1), (4, 2), (4, 3)), ArraySeq((4, 1), (4, 2), (4, 3), (4, 4)), ArraySeq((1, 3), (2, 3), (3, 3), (4, 3)), ArraySeq((1, 0), (2, 1), (3, 2), (4, 3))),
    ArraySeq(ArraySeq((4, 1), (4, 2), (4, 3), (4, 4)), ArraySeq((1, 4), (2, 4), (3, 4), (4, 4)), ArraySeq((1, 1), (2, 2), (3, 3), (4, 4)))
  )