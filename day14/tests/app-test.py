import unittest
from day14 import app


class Part1(unittest.TestCase):
    def test(self):
        self.assertEqual(8108, app.part1("flqrgnkx"))

class Part2(unittest.TestCase):
    def test(self):
        self.assertEqual(1242, app.part2("flqrgnkx"))