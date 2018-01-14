import unittest
from day10 import app


class Part1(unittest.TestCase):
    def test(self):
        self.assertEqual(12, app.part1(4, [3, 4, 1, 5]))


class Part2(unittest.TestCase):
    def test_emptyString(self):
        self.assertEqual("a2582a3a0e66e6e86e3812dcb672a272", app.part2(""))

    def test_string1(self):
        self.assertEqual("33efeb34ea91902bb2f59c9920caa6cd", app.part2("AoC 2017"))

    def test_string2(self):
        self.assertEqual("3efbe78a8d82f29979031a4aa0b16a9d", app.part2("1,2,3"))

    def test_string3(self):
        self.assertEqual("63960835bcdc130f0b66d7ff4f6a5a8e", app.part2("1,2,4"))
