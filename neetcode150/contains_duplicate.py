

def hasDuplicate(self, nums: list) -> bool:
    num_dict = {}

    for num in nums:
        if num in num_dict:
            return True
        num_dict[num] = True
    return False


test_payload = [1, 2, 3, 3]
print(hasDuplicate(0, test_payload))