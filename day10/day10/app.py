from operator import xor, mul

'''Begin with a list of numbers from 0 to 255, a current position which begins at 0 (the first element in the list), 
a skip size (which starts at 0), and a sequence of lengths (your puzzle input). Then, for each length:

* Reverse the order of that length of elements in the list, starting with the element at the current position.
* Move the current position forward by that length plus the skip size.
* Increase the skip size by one.

The list is circular; if the current position and the length try to reverse elements beyond the end of the list, 
the operation reverses using as many extra elements as it needs from the front of the list. If the current position 
moves past the end of the list, it wraps around to the front. Lengths larger than the size of the list are invalid.'''


def part1(n, lengths):
    return reduce(mul, solution(n, lengths)[:2])


'''Your input should be taken not as a list of numbers, but as a string of bytes instead which you must convert to ASCII code.
Once you have determined the sequence of lengths to use, add the following lengths to the end of the sequence: 17, 31, 73, 47, 23.
Instead of merely running one round like you did above, run a total of 64 rounds, using the same length sequence in each round. 
The current position and skip size should be preserved between rounds.
Use numeric bitwise XOR to combine each consecutive block of 16 numbers then convert
each digit to hex.
'''


def part2(s):
    numbers = solution(255, map(ord, list(s)) + [17, 31, 73, 47, 23], 64)
    return ''.join(map(lambda x: "%0.2x" % x,
                       map(lambda chunk: reduce(xor, chunk), [numbers[i:i + 16] for i in range(0, 255, 16)])))


def solution(n, lengths, repeat=1):
    numbers = range(0, n + 1)
    pos = 0
    skip_size = 0
    for _ in range(repeat):
        for length in lengths:
            rotate_by = pos % len(numbers)
            numbers = numbers[rotate_by:] + numbers[:rotate_by]  # move elements starting at pos to beginning
            numbers[0:length] = reversed(numbers[0:length])  # reverse the first length elements
            numbers = numbers[-rotate_by:] + numbers[:-rotate_by]  # move them back
            pos += length + skip_size
            skip_size += 1

    return numbers


def main():
    f = open("input.txt", "r")
    inputString = f.readline()
    print part1(255, map(int, inputString.split(",")))
    print part2(inputString)


if __name__ == "__main__": main()
