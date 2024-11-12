import random
import time

# ------------------------------------------------------------------------------------


# GCD of two numbers a and b is the same as the GCD of b and a % b
def gcd_euclidean(a, b):
    # Continue the loop until b becomes 0
    while b != 0:
        # Swap 'a' with 'b' and 'b' with the remainder of a divided by b
        a, b = b, a % b
    # When b is 0, 'a' is the GCD
    return a

# ------------------------------------------------------------------------------------


# The idea is that the GCD of two even numbers is twice the GCD of their halves,
# and the GCD of an even and odd number is the same as the GCD of the even number
# divided by 2 and the odd number.
def gcd_binary(a, b):
    # GCD(0, b) == b; GCD(a, 0) == a,
    # GCD(0, 0) == 0
    if a == 0:
        return b
    if b == 0:
        return a

    # Finding the largest power of 2 that divides both a and b
    shift = 0
    while ((a | b) & 1) == 0:  # While both 'a' and 'b' are even
        a >>= 1  # Divide 'a' by 2
        b >>= 1  # Divide 'b' by 2
        shift += 1  # Keep track of how many times both numbers were divided by 2

    # Dividing a by 2 until it's odd
    while (a & 1) == 0:  # While 'a' is even
        a >>= 1  # Keep dividing 'a' by 2

    # Perform the loop until 'b' becomes 0
    while b != 0:
        # Remove all factors of 2 in 'b'
        while (b & 1) == 0:  # While 'b' is even
            b >>= 1  # Keep dividing 'b' by 2

        # Ensure 'a' <= 'b'. If not, swap 'a' and 'b'
        if a > b:
            a, b = b, a

        # Subtract the smaller number 'a' from the larger number 'b'
        b = b - a  # Here, b becomes 'b - a'

    # The GCD is 'a', shifted back by the number of divisions by 2 we tracked earlier
    return a << shift  # Multiply by 2^shift to restore the common factors of 2

# ------------------------------------------------------------------------------------


# Recursive version of the Euclidean algorithm
def gcd_recursive(a, b):
    # Base case: when 'b' is 0, 'a' is the GCD
    if b == 0:
        return a
    # Recursive case: call gcd_recursive with 'b' and 'a % b'
    return gcd_recursive(b, a % b)

# ------------------------------------------------------------------------------------


def calculate_time_for_inputs_with_gcd_euclidean(sample):
    print("Calculating time for gcd_euclidean...\n\n")
    start = time.time()
    for a, b in sample:
        print(f"Inputs: a = {a}, b = {b}")
        print(f"GCD using gcd_euclidean: {gcd_euclidean(a, b)}")
    end = time.time()
    return (end - start) * 1000  # milliseconds

# ------------------------------------------------------------------------------------


def calculate_time_for_inputs_with_gcd_binary(sample):
    print("Calculating time for gcd_binary...\n\n")
    start = time.time()
    for a, b in sample:
        print(f"Inputs: a = {a}, b = {b}")
        print(f"GCD using gcd_binary: {gcd_binary(a, b)}")
    end = time.time()
    return (end - start) * 1000  # milliseconds

# ------------------------------------------------------------------------------------

def calculate_time_for_inputs_with_gcd_recursive(sample):
    print("Calculating time for gcd_recursive...\n\n")
    start = time.time()
    for a, b in sample:
        print(f"Inputs: a = {a}, b = {b}")
        print(f"GCD using gcd_recursive: {gcd_recursive(a, b)}\n")
    end = time.time()
    return (end - start) * 1000  # milliseconds

# ------------------------------------------------------------------------------------


def get_sample_of_custom_size(size):
    sample = []
    while size:
        first_number = random.randint(0, 9999999999999)
        second_number = random.randint(0, 9999999999999)
        multiplier = random.randint(0, 1000)
        first_number = first_number * multiplier
        second_number = second_number * multiplier
        sample.append((first_number, second_number))
        size = size - 1
    return sample

# ------------------------------------------------------------------------------------


inputs = get_sample_of_custom_size(10000)
# inputs = [(0, 12)]

time_for_euclidean = calculate_time_for_inputs_with_gcd_euclidean(inputs)
print("-" * 40)
time_for_binary = calculate_time_for_inputs_with_gcd_binary(inputs)
print("-" * 40)
time_for_recursive = calculate_time_for_inputs_with_gcd_recursive(inputs)
print("-" * 40)
print("-" * 40)
print("-" * 40)
print(f"Time to calculate GCD with euclidean method for a sample of size {len(inputs)}: {time_for_euclidean}")
print("-" * 40)
print(f"Time to calculate GCD with binary method for a sample of size {len(inputs)}: {time_for_binary}")
print("-" * 40)
print(f"Time to calculate GCD with recursive method for a sample of size {len(inputs)}: {time_for_recursive}")
print("-" * 40)
