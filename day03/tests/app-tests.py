import unittest
from day03 import app


class Part1(unittest.TestCase):
    def test_1(self):
        self.assertEqual(0, app.part1(1))

    def test_2(self):
        self.assertEqual(3, app.part1(12))

    def test_3(self):
        self.assertEqual(2, app.part1(23))

    def test_4(self):
        self.assertEqual(31, app.part1(1024))
